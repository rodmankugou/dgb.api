package com.verificer.biz.biz.service.adj.notify.events;

import com.verificer.biz.biz.entity.AdjustItem;
import com.verificer.biz.biz.entity.AdjustOrder;

import java.util.List;

public class AdjSucEvent extends AdjEvent{

    public AdjSucEvent(AdjustOrder order, List<AdjustItem> items) {
        super(order, items);
    }
}
