package com.verificer.biz.beans.vo.sta.sta;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class VisitOverviewStaVo implements Serializable {
    @ApiModelProperty("商品收藏")
    private BigDecimal shopCollect;
    @ApiModelProperty("商品浏览")
    private BigDecimal goodsVisit;
    @ApiModelProperty("商品加入购物车")
    private BigDecimal goodsCart;

    public BigDecimal getShopCollect() {
        return shopCollect;
    }

    public void setShopCollect(BigDecimal shopCollect) {
        this.shopCollect = shopCollect;
    }

    public BigDecimal getGoodsVisit() {
        return goodsVisit;
    }

    public void setGoodsVisit(BigDecimal goodsVisit) {
        this.goodsVisit = goodsVisit;
    }

    public BigDecimal getGoodsCart() {
        return goodsCart;
    }

    public void setGoodsCart(BigDecimal goodsCart) {
        this.goodsCart = goodsCart;
    }
}
