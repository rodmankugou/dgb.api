package com.verificer.biz.beans.vo.cart.req;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

public class CartQryVo implements Serializable {
    private Long userId;
    private Boolean shopFlag;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(Boolean shopFlag) {
        this.shopFlag = shopFlag;
    }
}
