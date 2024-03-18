package com.verificer.biz.biz.service.core.order.flow.impl;

import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.service.core.order.flow.IOrderFlow;

public abstract class BaseOrdFlow implements IOrderFlow {

    public boolean canUserIdNull(){
        return false;
    }

    public boolean isCheckBuyCountLimit(){
        return true;
    }

    @Override
    public boolean isAfterSale() {
        return false;
    }

    @Override
    public Long getOrdTime(DbgOrder o) {
        return o.getCreateTime();
    }

    @Override
    public boolean isCheckAmount() {
        return true;
    }
}
