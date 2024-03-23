package com.verificer.biz.beans.vo.order;

import com.verificer.utils.decimal.PriceDecimal;

import java.io.Serializable;
import java.math.BigDecimal;


public class PayVo implements Serializable {
    private Integer payType;

    /**
     * 支付ID
     */
    private Long payId;

    /**
     * 支付金额
     */
    @PriceDecimal
    private BigDecimal payAmount;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
