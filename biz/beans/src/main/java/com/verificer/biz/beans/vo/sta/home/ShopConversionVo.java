package com.verificer.biz.beans.vo.sta.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class ShopConversionVo implements Serializable {
    @ApiModelProperty("门店")
    private String shopName;

    @ApiModelProperty("转化率")
    private BigDecimal rate;

    public ShopConversionVo() {
    }

    public ShopConversionVo(String shopName, BigDecimal rate) {
        this.shopName = shopName;
        this.rate = rate;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
