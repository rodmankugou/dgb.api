package com.verificer.biz.beans.vo.sta.sta;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class RealTimeSta implements Serializable {
    @ApiModelProperty("总sku")
    private BigDecimal totalSku;
    @ApiModelProperty("商品浏览")
    private BigDecimal goodsView;
    @ApiModelProperty("商品收藏")
    private BigDecimal goodsCollect;
    @ApiModelProperty("订单商品")
    private BigDecimal salesGoods;
    @ApiModelProperty("店铺收藏")
    private BigDecimal shopCollect;
    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty("订单金额")
    private BigDecimal orderCount;


    public BigDecimal getTotalSku() {
        return totalSku;
    }

    public void setTotalSku(BigDecimal totalSku) {
        this.totalSku = totalSku;
    }

    public BigDecimal getGoodsView() {
        return goodsView;
    }

    public void setGoodsView(BigDecimal goodsView) {
        this.goodsView = goodsView;
    }

    public BigDecimal getGoodsCollect() {
        return goodsCollect;
    }

    public void setGoodsCollect(BigDecimal goodsCollect) {
        this.goodsCollect = goodsCollect;
    }

    public BigDecimal getSalesGoods() {
        return salesGoods;
    }

    public void setSalesGoods(BigDecimal salesGoods) {
        this.salesGoods = salesGoods;
    }

    public BigDecimal getShopCollect() {
        return shopCollect;
    }

    public void setShopCollect(BigDecimal shopCollect) {
        this.shopCollect = shopCollect;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(BigDecimal orderCount) {
        this.orderCount = orderCount;
    }
}
