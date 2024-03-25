package com.verificer.biz.biz.service.settle.impl;

import com.verificer.ErrCode;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.enums.PlaIncomeLogType;
import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.PlaIncomeLogMapper;
import com.verificer.biz.biz.service.common.ShopCommon;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.user.MemberOrderService;
import com.verificer.biz.biz.service.core.user.notify.IMemberListener;
import com.verificer.biz.biz.service.core.user.notify.event.MemberEvent;
import com.verificer.biz.biz.service.core.user.notify.event.MemberOrdSucEvent;
import com.verificer.biz.biz.service.settle.PlaIncomeLogService;
import com.verificer.biz.biz.service.settle.SettleItemService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PlaIncomeLogServiceImpl implements PlaIncomeLogService {
    @Autowired
    PlaIncomeLogMapper mapper;

    @Autowired
    SettleItemService settleItemService;

    @Autowired
    MemberOrderService memberOrderService;

    @Autowired
    UserCommon userCommon;

    @Autowired
    ShopCommon shopCommon;

    @PostConstruct
    void init() {
        memberOrderService.addListener(new IMemberListener() {
            @Override
            public void onMemberEvent(MemberEvent e) {
                handleMemberEvent(e);
            }
        });
    }

    private void handleMemberEvent(MemberEvent event){
        if(event instanceof MemberOrdSucEvent){
            String shopId = null;
            MemberOrdSucEvent e = (MemberOrdSucEvent) event;
            MemberOrder mo = e.getOrd();
            if(MemberRefType.SHOP.getValue() == mo.getReferrerType())
                shopId = mo.getReferrerId();

            User user = userCommon.getById(mo.getUserId());
            if(user == null)
                throw new BaseException(ErrCode.RECORD_NOT_EXIST);
            createLog(PlaIncomeLogType.MEMBER_REGISTER,
                    shopId,
                    mo.getId(),
                    mo.getOrdNum(),
                    mo.getPrice(),
                    true,
                    "会员注册，"+user.getNickname()+", "+user.getMobile()+", "+user.getUid());
        }
    }


    @Override
    public List<PlaIncomeLogVo> plaIncomeLogPage(PlaIncomeLogQryVo reqVo) {
        return mapper.page(reqVo);
    }

    @Override
    public int plaIncomeLogCount(PlaIncomeLogQryVo reqVo) {
        return mapper.count(reqVo);
    }

    @Override
    public void afterSettle(SettleOrder so) {
        PlaIncomeLog log = new PlaIncomeLog();
        String shopName = "";
        if(!SStringUtils.isEmpty(so.getShopId())){
            Shop shop = shopCommon.getById(so.getShopId());
            shopName = shop.getName();
        }
        List<SettleItem> items = settleItemService.getBySettleOrdId(so.getId());
        for(SettleItem item : items){
            createLog(
                    PlaIncomeLogType.SHOP_SETTLE,
                    so.getShopId(),
                    so.getId(),
                    so.getOrdNum(),
                    item.getAmount(),
                    false,
                    "会员佣金, "+shopName+", 账单编号"+so.getOrdNum()
            );
        }
    }

    @Override
    public void createLog(PlaIncomeLogType type, String shopId, Long ordId, String ordNum, BigDecimal amount,Boolean incomeFlag,String remark){
        SCheckUtil.notEmpty(type,"Type");
        SCheckUtil.notEmpty(ordId,"Order Id");
        SCheckUtil.notEmpty(amount,"Amount");
        SCheckUtil.notEmpty(incomeFlag,"Income Flag");

        PlaIncomeLog log = new PlaIncomeLog();
        log.setType(type.getValue());
        log.setShopId(shopId);
        log.setOrdId(ordId);
        log.setOrdNum(ordNum);
        log.setAmount(amount);
        log.setIncomeFlag(incomeFlag);
        log.setRemark(remark);
        log.setCreateTime(System.currentTimeMillis());
        mapper.insertSelective(log);
    }
}
