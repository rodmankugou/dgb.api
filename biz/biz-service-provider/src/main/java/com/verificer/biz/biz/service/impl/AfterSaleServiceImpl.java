package com.verificer.biz.biz.service.impl;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.biz.biz.entity.AfterSale;
import com.verificer.biz.biz.mapper.AfterSaleMapper;
import com.verificer.biz.biz.service.AfterSaleService;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional(rollbackFor = Exception.class)
public class AfterSaleServiceImpl implements AfterSaleService {
    @Autowired
    AfterSaleMapper mapper;

    @Autowired
    BaseSupService baseSupService;

    private void mCheck(AfterSale e){
        SCheckUtil.notEmpty(e.getRootOrderId(),"afterSale.rootOrderId");
        SCheckUtil.notEmpty(e.getStatus(),"afterSale.status");
        SCheckUtil.notEmpty(e.getOrderId(),"afterSale.orderId");
        SCheckUtil.notEmpty(e.getGoodsId(),"afterSale.goodsId");
        SCheckUtil.notEmpty(e.getGoodsName(),"afterSale.goodsName");
        SCheckUtil.notEmpty(e.getSpecId(),"afterSale.specId");
        SCheckUtil.notEmpty(e.getSpecName(),"afterSale.specName");
        SCheckUtil.lgThanAndNotNull(e.getCount(),false, BigDecimal.ZERO,"afterSale.count");
        SCheckUtil.notEmpty(e.getReason(),"afterSale.reason");
        SCheckUtil.notEmpty(e.getCreateTime(),"afterSale.createTime");
        SCheckUtil.notEmpty(e.getExpireTime(),"afterSale.expireTime");
    }

    public void add(AfterSale afterSale){
        afterSale.setCreateTime(System.currentTimeMillis());
        Integer expireDays = Integer.parseInt(baseSupService.getCfg(CfgCodes.AFTER_SALE_EXPIRE_DAYS));
        afterSale.setExpireTime(afterSale.getCreateTime() + (expireDays* SDateUtil.MS_PER_DAY));
        mCheck(afterSale);
        mapper.insertSelective(afterSale);
    }

}
