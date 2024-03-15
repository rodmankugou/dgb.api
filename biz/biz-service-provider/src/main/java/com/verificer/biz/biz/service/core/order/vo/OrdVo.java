package com.verificer.biz.biz.service.core.order.vo;

import com.verificer.biz.biz.entity.DbgOrder;
import com.verificer.biz.biz.entity.OrderDetail;

import java.io.Serializable;
import java.util.List;

public class OrdVo implements Serializable {
    DbgOrder ord;
    List<OrderDetail> items;

    public OrdVo(DbgOrder ord, List<OrderDetail> items) {
        this.ord = ord;
        this.items = items;
    }

    public DbgOrder getOrd() {
        return ord;
    }



    public List<OrderDetail> getItems() {
        return items;
    }

}
