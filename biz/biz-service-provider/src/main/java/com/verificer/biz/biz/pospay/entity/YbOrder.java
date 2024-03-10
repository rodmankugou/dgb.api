package com.verificer.biz.biz.pospay.entity;

import java.io.Serializable;
import java.util.List;

public class YbOrder implements Serializable {
    private Long uid;
    private String remark;
    private String totalAmount;
    private Long customerUid;
    private String datetime;

    private List<YbOrderItem> items;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(Long customerUid) {
        this.customerUid = customerUid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public List<YbOrderItem> getItems() {
        return items;
    }

    public void setItems(List<YbOrderItem> items) {
        this.items = items;
    }
}
