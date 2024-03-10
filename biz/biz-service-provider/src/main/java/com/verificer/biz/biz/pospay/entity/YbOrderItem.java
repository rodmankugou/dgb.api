package com.verificer.biz.biz.pospay.entity;

import java.math.BigDecimal;

public class YbOrderItem {
    private BigDecimal totalAmount;
    private Long productUid;
    private BigDecimal quantity;
    private BigDecimal sellPrice;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getProductUid() {
        return productUid;
    }

    public void setProductUid(Long productUid) {
        this.productUid = productUid;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }


}
