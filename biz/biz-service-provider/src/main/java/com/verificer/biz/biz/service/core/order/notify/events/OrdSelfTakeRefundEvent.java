package com.verificer.biz.biz.service.core.order.notify.events;

public class OrdSelfTakeRefundEvent extends OrdEvent{
    public OrdSelfTakeRefundEvent(Long ordId) {
        super(ordId);
    }
}
