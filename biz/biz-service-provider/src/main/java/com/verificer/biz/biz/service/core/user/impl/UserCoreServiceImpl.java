package com.verificer.biz.biz.service.core.user.impl;

import com.verificer.ErrCode;
import com.verificer.account.itf.AccOpType;
import com.verificer.account.itf.AccTools;
import com.verificer.account.itf.BaseAccountService;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.beans.AreaVo;
import com.verificer.beans.WxLoginReqVo;
import com.verificer.beans.account.AccountVo;
import com.verificer.beans.num.NumGenerator;
import com.verificer.biz.beans.constants.BizConst;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.enums.PosSyncTaskType;
import com.verificer.biz.beans.vo.req.UserSetRefVo;
import com.verificer.biz.beans.vo.user.req.BindMobileVo;
import com.verificer.biz.biz.entity.MemberOrder;
import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.UserMapper;
import com.verificer.biz.biz.pospay.entity.req.AddMemberReq;
import com.verificer.biz.biz.pospay.entity.req.UpdMemberReq;
import com.verificer.biz.biz.service.PosSyncTaskService;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.user.MemberOrderService;
import com.verificer.biz.biz.service.core.user.UserCoreService;
import com.verificer.biz.biz.service.core.order.OrdCoreService;
import com.verificer.biz.biz.service.core.order.notify.IOrdListener;
import com.verificer.biz.biz.service.core.order.notify.events.OrdEvent;
import com.verificer.biz.biz.service.core.order.notify.events.OrdSucFinishEvent;
import com.verificer.biz.biz.service.core.user.notify.IMemberListener;
import com.verificer.biz.biz.service.core.user.notify.event.MemberEvent;
import com.verificer.biz.biz.service.core.user.notify.event.MemberOrdSucEvent;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.FastJson;
import com.verificer.utils.IpUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.UuidUtils;
import com.verificer.utils.check.SCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
@Transactional
public class UserCoreServiceImpl implements UserCoreService {
    private static final Logger logger = LoggerFactory.getLogger(UserCoreService.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrdCoreService ordCoreService;

    @Autowired
    MemberOrderService memberOrderService;

    @Autowired
    PosSyncTaskService posSyncTaskService;

    @Autowired
    BaseAccountService baseAccountService;

    @Autowired
    UserCommon userCommon;

    @Autowired
    BaseSupService baseSupService;

    private NumGenerator uIdGenerator = new NumGenerator() {
        @Override
        public boolean isNumExist(String num) {
            return userMapper.selectByUid(num) != null;
        }
    };

    @PostConstruct
    void init(){
        ordCoreService.addListener(new IOrdListener() {
            @Override
            public void onOrdEvent(OrdEvent e) {
                handleOrdEvent(e);
            }
        });

        memberOrderService.addListener(new IMemberListener() {
            @Override
            public void onMemberEvent(MemberEvent e) {
                handleMemberEvent(e);
            }
        });
    }

    private void handleMemberEvent(MemberEvent event){
        if(event instanceof MemberOrdSucEvent){
            MemberOrder mo = event.getOrd();
            User member = userMapper.getAndLock(mo.getUserId());
            if(member == null)
                throw new BaseException(ErrCode.RECORD_NOT_EXIST);
            member.setMemberFlag(true);
            member.setMemberSTime(mo.getsTime());
            member.setMemberETime(mo.geteTime());
            member.setMemberIp(mo.getIp());
            member.setMemberRefType(mo.getReferrerType());
            member.setMemberRefId(mo.getReferrerId());
//            member.setMemberShopName();
            String memberProvinceCode = null;
            try {
                memberProvinceCode = IpUtils.getIpProvinceCode(
                        mo.getIp(),
                        baseSupService.getCfg(CfgCodes.BAIDU_MAP_AK),
                        baseSupService.getCfg(CfgCodes.BAIDU_MAP_SK)
                );
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
            member.setMemberProvinceCode(memberProvinceCode);
            if(!SStringUtils.isEmpty(memberProvinceCode)){
                AreaVo areaVo = baseSupService.areaGetByCode(memberProvinceCode);
                if(areaVo != null)
                    member.setMemberProvinceName(areaVo.getName());
            }

            userMapper.updateByPrimaryKeySelective(member);

            if(mo.getReferrerType() == MemberRefType.USER.getValue()){
                //分润给引荐人
                referrerOnInviteSuc(mo);
            }

            //同步pos机会员
            synPosMember(member,mo);
        }
    }

    private void referrerOnInviteSuc(MemberOrder mo){
        User referrer = userMapper.selectByUid(mo.getReferrerId());
        if(referrer == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        referrer = userMapper.getAndLock(referrer.getId());
        referrer.setTotalInviteCount(referrer.getTotalInviteCount()+1);
        referrer.setTotalInviteCommission(referrer.getTotalInviteCommission().add(mo.getCommission()));
        userMapper.updateByPrimaryKeySelective(referrer);

        baseAccountService.createAccountIfNeed(referrer.getUid(), AccTools.USER_REFERRER,false);
        AccountVo accountVo = baseAccountService.getAccount(referrer.getUid(), AccTools.USER_REFERRER);
        baseAccountService.addAvailable(AccOpType.USER_REF_COMMISSION.getValue(),
                mo.getId(),
                accountVo.getId(),
                mo.getCommission(),
                "引荐人分润");
    }

    private void synPosMember(User member, MemberOrder mo){
        if(mo.getFirstChargeFlag()){
            AddMemberReq req = new AddMemberReq();
            req.setName(member.getNickname());
            req.setExpireTime(mo.geteTime());
            req.setPhone(member.getMobile());
            req.setNumber(member.getUid());
            posSyncTaskService.addTask(null,mo.getId(), PosSyncTaskType.MEMBER_ADD.getValue(), FastJson.toJson(req));
        }else {
            UpdMemberReq req = new UpdMemberReq();
            if(req.getPosMemberId() == null)
                throw new RuntimeException("会员续费的情况下，用户的PosMemberId不应为空");
            req.setPosMemberId(member.getPosMemberId());
            req.setExpiryTime(mo.geteTime());
            posSyncTaskService.addTask(null,mo.getId(), PosSyncTaskType.MEMBER_UPD.getValue(),  FastJson.toJson(req));
        }
    }

    private void handleOrdEvent(OrdEvent event){
        if(event instanceof OrdSucFinishEvent){
            OrdSucFinishEvent e = (OrdSucFinishEvent) event;
            if(e.getUserId() == null)
                return;

            User u = userMapper.getAndLock(e.getUserId());
            if(u == null)
                return;

            u.setTotalOrderCount(u.getTotalOrderCount()+1);
            u.setTotalOrderAmount(u.getTotalOrderAmount().add(e.getAmount()));
            userMapper.updateByPrimaryKeySelective(u);
        }
    }

    @Override
    public Long wxLogin(WxLoginReqVo reqVo) {

        //TODO 登录 ,在此实现真正的校验逻辑
        String openId = UuidUtils.newUuid();

        User user = userMapper.selectByWxOpenId(openId);
        if(user == null)
            return  registerUser(openId).getId();
        return user.getId();
    }

    private User registerUser(String openId){
        User u = new User();
        u.setUid(UuidUtils.newUuid());
        u.setWxOpenId(openId);
        u.setMemberFlag(false);
        u.setAvatar(baseSupService.getCfg(CfgCodes.DEFAULT_USER_AVATAR));
        u.setRegTime(System.currentTimeMillis());
        u.setReferrerFlag(false);
        u.setReferrerEnableFlag(false);
        u.setTotalInviteCommission(BigDecimal.ZERO);
        u.setTotalInviteCount(0);
        u.setTotalOrderCount(0);
        u.setTotalOrderAmount(BigDecimal.ZERO);

        userMapper.insertSelective(u);

        String uid = uIdGenerator.genNum(BizConst.ID_PREFIX_USER,13,u.getId());
        u.setNickname(uid);
        u.setUid(uid);
        userMapper.updateByPrimaryKeySelective(u);

        //创建积分账号
        userCommon.createIntegralAccIfNeed(u);

        return u;
    }



    /**
     * 根跟Pos机端的会员id查询用户
     * @param posMemberId
     * @return
     */
    @Override
    public User selectByPosMemberId(Long posMemberId) {
        User user = userMapper.selectByPosMemberId(posMemberId);
        return user;
    }




    @Override
    public void userSetReferrer(UserSetRefVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getId(),"ID");
        SCheckUtil.notEmpty(reqVo.getEnableFlag(),"Enable Flag");
        SCheckUtil.lgThanAndNotNull(reqVo.getCommission(),true, BigDecimal.ZERO,"Commission");


        User user = userMapper.getAndLock(reqVo.getId());
        if(user == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        if(!user.getReferrerFlag()){
            baseAccountService.createAccountIfNeed(user.getUid(), AccTools.USER_REFERRER,false);
        }

        user.setReferrerFlag(true);
        user.setReferrerEnableFlag(reqVo.getEnableFlag());
        user.setInviteCommission(reqVo.getCommission());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void bindPosMemberId(Long memberOrdId, Long posMemberId) {
        MemberOrder mo = memberOrderService.getById(memberOrdId);
        User member = userMapper.getAndLock(mo.getUserId());
        member.setPosMemberId(posMemberId);
        userMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public void userBindMobile(BindMobileVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getUserId(),"User Id");
        SCheckUtil.notEmpty(reqVo.getMobile(),"Mobile");

        User user = userMapper.getAndLock(reqVo.getUserId());
        if(user == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        if(!SStringUtils.isEmpty(user.getMobile()) && !user.getMobile().equals(reqVo.getMobile()))
            throw new BaseException(ErrCode.USER_HAS_BIND_MOBILE);
        user.setMobile(reqVo.getMobile());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int clearExpireMember() {
        Long now = System.currentTimeMillis();
        User user = userMapper.getExpireMemberLimit1(now);
        if(user == null)
            return 0;
        user = userMapper.getAndLock(user.getId());
        user.setMemberFlag(false);
        userMapper.updateByPrimaryKeySelective(user);
        return 1;
    }
}
