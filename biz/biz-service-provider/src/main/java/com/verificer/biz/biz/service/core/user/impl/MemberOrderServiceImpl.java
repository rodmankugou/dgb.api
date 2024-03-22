package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.ErrCode;
import com.verificer.GlobalConfig;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.biz.beans.enums.MemberOrdSta;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.enums.MemberTypeTimeUnit;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.biz.entity.MemberOrder;
import com.verificer.biz.biz.entity.MemberType;
import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.MemberOrderMapper;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.user.MemberOrderService;
import com.verificer.biz.biz.service.core.user.MemberTypeService;
import com.verificer.biz.biz.service.core.user.notify.IMemberListener;
import com.verificer.biz.biz.service.core.user.notify.event.MemberEvent;
import com.verificer.biz.biz.service.core.user.notify.event.MemberOrdSucEvent;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.designpatterns.listener.ConcurrentNotifier;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberOrderServiceImpl implements MemberOrderService {
    private ConcurrentNotifier<IMemberListener, MemberEvent> notifier = new ConcurrentNotifier<IMemberListener, MemberEvent>() {
        @Override
        protected void trigger(IMemberListener listener, MemberEvent event) {
            listener.onMemberEvent(event);
        }
    };

    @Autowired
    MemberOrderMapper mapper;

    @Autowired
    MemberTypeService memberTypeService;

    @Autowired
    UserCommon userCommon;

    public void addListener(IMemberListener listener){
        this.notifier.addListener(listener);
    }

    @Override
    public PayReqVo memberCharge(MemberChargeVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(), "User ID");
        SCheckUtil.notEmpty(reqVo.getMemberTypeId(), "Member Type ID");
        SCheckUtil.notEmpty(reqVo.getIp(),"IP");

        User user = userCommon.getById(reqVo.getUserId());
        if(SStringUtils.isEmpty(user.getMobile()))
            throw new BaseException(ErrCode.NEED_BIND_MOBILE);

        Long now = System.currentTimeMillis();
        MemberType type = memberTypeService.getById(reqVo.getMemberTypeId());
        if (type == null || type.getDelFlag())
            throw new BaseException(ErrCode.MEMBER_TYPE_NOT_EXIST);
        MemberOrder mo = new MemberOrder();
        mo.setUserId(reqVo.getUserId());
        mo.setMemberTypeId(reqVo.getMemberTypeId());
        mo.setStatus(MemberOrdSta.WAI_PAY.getValue());
        mo.setsTime(now);
        mo.seteTime(getEndType(now, type));
        mo.setReferrerId(reqVo.getReferrerId());
        mo.setReferrerType(reqVo.getReferrerType());
        mo.setPrice(type.getPrice());
        mo.setIp(reqVo.getIp());
        if(reqVo.getReferrerType() != null && MemberRefType.USER.getValue() == reqVo.getReferrerType()){
            User referrer = userCommon.getUserByUid(mo.getReferrerId());
            if(referrer == null || !referrer.getReferrerEnableFlag() || !referrer.getReferrerFlag())
                throw new BaseException(ErrCode.MEMBER_REFERRER_QR_CODE_INVALID);
            mo.setCommission(referrer.getInviteCommission());

        }


        mo.setCreateTime(now);
        mo.setDelFlag(false);

        mapper.insertSelective(mo);

        PayReqVo payReqVo = new PayReqVo();
        payReqVo.setOrderId(mo.getId());
        payReqVo.setAmount(mo.getPrice().setScale(GlobalConfig.PREC).toPlainString());
        //TODO 支付
        return payReqVo;
    }

    @Override
    public void onPaySuc(PaySucVo paySucVo) {
        SCheckUtil.notEmpty(paySucVo.getOrderId(),"Order Id");
        SCheckUtil.notEmpty(paySucVo.getPayId(),"Pay Id");
        SCheckUtil.notEmpty(paySucVo.getAmount(),"Amount");

        //TODO 支付
        MemberOrder mo = mapper.getAndLock(paySucVo.getOrderId());
        if(mo == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(MemberOrdSta.SUC.getValue() == mo.getStatus())
            return;

        if(paySucVo.getAmount().compareTo(mo.getPrice()) != 0)
            throw new RuntimeException("支付金额不对");

        boolean firstFlag = mapper.selectByUserIdAndStatusLimit1(mo.getUserId(), MemberOrdSta.SUC.getValue()) == null;
        mo.setFirstChargeFlag(firstFlag);
        mo.setStatus(MemberOrdSta.SUC.getValue());
        mo.setPayId(paySucVo.getPayId());
        mo.setPayTime(System.currentTimeMillis());
        mapper.updateByPrimaryKeySelective(mo);

        MemberOrder temp = new MemberOrder();
        SBeanUtils.copyProperties2(mo,temp);
        notifier.triggerAll(new MemberOrdSucEvent(temp));
    }

    @Override
    public MemberOrder getById(Long memberOrdId) {
        return mapper.selectByPrimaryKey(memberOrdId);
    }

    private Long getEndType(Long startTime, MemberType type) {
        int dayOffset = 0;
        int monthOffset = 0;
        int yearOffset = 0;
        if (MemberTypeTimeUnit.YEAR.getValue() == type.getTimeUnit()) {
            yearOffset = type.getTimeCount();
        } else if (MemberTypeTimeUnit.MONTH.getValue() == type.getTimeUnit()) {
            monthOffset = type.getTimeCount();
        } else if (MemberTypeTimeUnit.DAY.getValue() == type.getTimeUnit()) {
            dayOffset = type.getTimeCount();
        } else {
            throw new BizErrMsgException("Illegal Member type time unit");
        }
        Long eTime = SDateUtil.offsetDate(startTime, dayOffset, monthOffset, yearOffset);
        eTime = SDateUtil.getDayEndTime(eTime);

        return eTime;
    }
}
