package com.verificer.biz.biz.pospay.entity;

import java.io.Serializable;

public class YbMember implements Serializable {
    private Long customerId;
    private String number;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

