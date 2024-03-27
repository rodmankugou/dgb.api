package com.verificer.biz.biz.service.cache.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class SpecStock {
    @ApiModelProperty("是否店铺，如果不是店铺，则表示平台的库存量")
    private Boolean shopFlag;
    @ApiModelProperty("商家ID")
    private String merId;
    @ApiModelProperty("库存")
    private BigDecimal stock;

    @ApiModelProperty(hidden = true)
    private Long distance;


    public SpecStock() {
    }
    public SpecStock(Boolean shopFlag, String merId, BigDecimal stock, Long distance) {
        this.shopFlag = shopFlag;
        this.merId = merId;
        this.stock = stock;
        this.distance = distance;

    }


    public Boolean getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(Boolean shopFlag) {
        this.shopFlag = shopFlag;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }
}
