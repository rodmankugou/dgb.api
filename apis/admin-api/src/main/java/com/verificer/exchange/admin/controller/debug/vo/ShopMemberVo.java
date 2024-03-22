package com.verificer.exchange.admin.controller.debug.vo;

import java.io.Serializable;
import java.net.Inet4Address;

public class ShopMemberVo implements Serializable {
    private Integer year;
    private Integer month;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
