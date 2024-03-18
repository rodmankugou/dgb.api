package com.verificer.biz.biz.service.core.order.notify.events;

public class OrdReceivedEvent extends OrdEvent{

    public OrdReceivedEvent(Long ordId) {
        super(ordId);
    }
}
