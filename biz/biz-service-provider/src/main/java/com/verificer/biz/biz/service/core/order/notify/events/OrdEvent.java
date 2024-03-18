package com.verificer.biz.biz.service.core.order.notify.events;

public  class OrdEvent {
    private Long ordId;

    public OrdEvent(Long ordId) {
        this.ordId = ordId;
    }

    public Long getOrdId() {
        return ordId;
    }

    public void setOrdId(Long ordId) {
        this.ordId = ordId;
    }
}
