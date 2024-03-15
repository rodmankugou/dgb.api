package com.verificer.biz.biz.pospay.entity;

import java.io.Serializable;
import java.util.List;

public class YbOrder implements Serializable {
    /**
     * 订单唯一ID
     */
    private Long uid;
    /**
     * 收银员唯一ID
     */
    private Long cashierUid;
    private String remark;
    /**
     * 订单总金额
     */
    private String totalAmount;
    /**
     * 会员唯一ID
     */
    private Long customerUid;
    /**
     * 订单时间
     */
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

    public Long getCashierUid() {
        return cashierUid;
    }

    public void setCashierUid(Long cashierUid) {
        this.cashierUid = cashierUid;
    }
}
