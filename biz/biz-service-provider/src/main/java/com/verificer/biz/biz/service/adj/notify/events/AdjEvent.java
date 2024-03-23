package com.verificer.biz.biz.service.adj.notify.events;

import com.verificer.biz.biz.entity.AdjustItem;
import com.verificer.biz.biz.entity.AdjustOrder;

import java.util.List;

public class AdjEvent {
    private AdjustOrder order;
    private List<AdjustItem> items;

    public AdjEvent(AdjustOrder order, List<AdjustItem> items) {
        this.order = order;
        this.items = items;
    }

    public AdjustOrder getOrder() {
        return order;
    }


    public List<AdjustItem> getItems() {
        return items;
    }


}
