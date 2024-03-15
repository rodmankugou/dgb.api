package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.AfterSale;
import com.verificer.biz.biz.mapper.AfterSaleMapper;
import com.verificer.biz.biz.service.AfterSaleService;
import com.verificer.utils.check.SCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class AfterSaleServiceImpl implements AfterSaleService {
    @Autowired
    AfterSaleMapper mapper;

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
        mCheck(afterSale);
        mapper.insertSelective(afterSale);
    }

}
