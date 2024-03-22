package com.verificer.biz.biz.service.settle.impl;

import com.verificer.ErrCode;
import com.verificer.GlobalConfig;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.enums.SettleType;
import com.verificer.biz.beans.vo.settle.req.SettleStaQryVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
import com.verificer.biz.biz.entity.*;
import com.verificer.biz.biz.mapper.SettleMapper;
import com.verificer.biz.biz.service.common.ShopCommon;
import com.verificer.biz.biz.service.common.UserCommon;
import com.verificer.biz.biz.service.core.user.MemberOrderService;
import com.verificer.biz.biz.service.core.user.notify.IMemberListener;
import com.verificer.biz.biz.service.core.user.notify.event.MemberEvent;
import com.verificer.biz.biz.service.core.user.notify.event.MemberOrdSucEvent;
import com.verificer.biz.biz.service.settle.SettleService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SettleServiceImpl implements SettleService {

    @Autowired
    MemberOrderService memberOrderService;


    @Autowired
    SettleMapper mapper;

    @Autowired
    ShopCommon shopCommon;

    @Autowired
    UserCommon userCommon;

    @PostConstruct
    void init(){
        memberOrderService.addListener(new IMemberListener() {
            @Override
            public void onMemberEvent(MemberEvent e) {
                handleMemberEvent(e);
            }
        });
    }

    private void handleMemberEvent(MemberEvent event){
        if(event instanceof MemberOrdSucEvent){
            Long now = System.currentTimeMillis();

            MemberOrdSucEvent e = (MemberOrdSucEvent) event;
            MemberOrder mo = e.getOrd();
            if(MemberRefType.SHOP.getValue() != mo.getReferrerType())
                return;

            Shop shop = shopCommon.getById(mo.getReferrerId());
            Shop parentShop = null;
            if(!SStringUtils.isEmpty(shop.getParentId())){
                parentShop = shopCommon.getById(shop.getParentId());
                if(parentShop == null)
                    throw new BaseException(ErrCode.RECORD_NOT_EXIST);
            }

            createSettle(shop,mo,now);
            if(parentShop != null )
                createSettle(parentShop,mo,now);
        }
    }

    private void createSettle(Shop shop,MemberOrder mo,Long now){
        if(shop == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);

        Settle s = new Settle();
        s.setType(SettleType.SHOP_MEMBER.getValue());
        s.setShopId(mo.getReferrerId());
        s.setRelId(mo.getId());
        s.setCommissionRate(shop.getCommissionRate());
        s.setTotalPhase(12);
        s.setCalPahse(0);
        s.setSettlePhase(0);
        s.setTotalAmount(shop.getCommissionRate().multiply(mo.getPrice()).setScale(GlobalConfig.PREC, RoundingMode.HALF_UP));
        s.setPhaseAmount(s.getTotalAmount().divide(new BigDecimal(s.getTotalPhase()),GlobalConfig.PREC,RoundingMode.HALF_UP));
        s.setTitle(genTitle(mo.getUserId()));
        s.setNextCalTime(SDateUtil.getNextMonthSTime(now));
        s.setFinishCalFlag(false);
        s.setCreateTime(now);
        mapper.insertSelective(s);
    }

    private  String genTitle(Long userId){
        User u = userCommon.getById(userId);
        if(u == null)
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        return u.getNickname()+", "+u.getMobile()+", "+u.getUid()+", ";

    }

    @Override
    public MemberStaVo settleSta(SettleStaQryVo reqVo) {
        MemberStaVo sta = mapper.sta(reqVo);
        return sta;

    }

    @Override
    public Settle getReadySettle(Long now ,String shopId) {
        Settle settle = mapper.getReadySettle(now,shopId);
        if(settle == null)
            return settle;
        settle = mapper.getAndLock(settle.getId());
        return settle;
    }

    @Override
    public void afterCalSettle(Settle settle) {
        settle.setNextCalTime(SDateUtil.getNextMonthSTime(settle.getNextCalTime()));
        settle.setCalPahse(settle.getCalPahse()+1);
        if(settle.getCalPahse().equals(settle.getTotalPhase()))
            settle.setFinishCalFlag(true);
        mapper.updateByPrimaryKeySelective(settle);
    }

    @Override
    public void afterSettle(List<SettleItem> items) {
        if(items.size() == 0)
            return;
        List<Long> ids = new LinkedList<>();
        for(SettleItem item : items){
            ids.add(item.getSettleId());
        }

        mapper.addSettlePhase(ids);
    }
}
