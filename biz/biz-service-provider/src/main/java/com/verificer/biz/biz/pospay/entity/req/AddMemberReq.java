package com.verificer.biz.biz.pospay.entity.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddMemberReq implements Serializable {
    private String number;
    private String name;
    private String phone;
    private Long expireTime;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
