package com.verificer.biz.beans.vo.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class ASpecStockVo implements Serializable {
    @ApiModelProperty("是否店铺，如果不是店铺，则表示平台的库存量")
    private Boolean shopFlag;
    @ApiModelProperty("店铺ID")
    private String shopId;
    @ApiModelProperty("库存")
    private BigDecimal stock;

    @ApiModelProperty(hidden = true)
    private Long distance;

    public Boolean getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(Boolean shopFlag) {
        this.shopFlag = shopFlag;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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
