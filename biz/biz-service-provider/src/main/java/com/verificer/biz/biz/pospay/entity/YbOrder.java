package com.verificer.biz.biz.pospay.entity;

import java.io.Serializable;

public class YbOrder implements Serializable {
    private String cashierUid;
    private String remark;
    private String totalAmount;

    public String getCashierUid() {
        return cashierUid;
    }

    public void setCashierUid(String cashierUid) {
        this.cashierUid = cashierUid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
