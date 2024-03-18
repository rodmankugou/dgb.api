package com.verificer.biz.beans.vo.order;

import com.verificer.biz.beans.vo.req.OrdItemFormVo;

import java.math.BigDecimal;

public class YbOrdItemVo extends OrdItemFormVo {
    private BigDecimal realPrice;
    private BigDecimal amount;

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
