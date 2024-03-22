package com.verificer.biz.biz.service.core.order.notify.events;

import java.math.BigDecimal;

/**
 * 订单有效成交并终结
 */
public class OrdSucFinishEvent extends OrdEvent{
    private BigDecimal amount;

    private Long userId;
    public OrdSucFinishEvent(Long ordId, BigDecimal amount,Long userId) {
        super(ordId);
        this.amount = amount;
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getUserId() {
        return userId;
    }
}
