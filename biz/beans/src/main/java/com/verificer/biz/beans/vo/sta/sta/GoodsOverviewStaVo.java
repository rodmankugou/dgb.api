package com.verificer.biz.beans.vo.sta.sta;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class GoodsOverviewStaVo implements Serializable {
    @ApiModelProperty("总数")
    private BigDecimal total;
    @ApiModelProperty("总浏览数")
    private BigDecimal visit;
    @ApiModelProperty("商品加入购物车")
    private BigDecimal cart;
    @ApiModelProperty("订单商品")
    private BigDecimal salesGoods;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getVisit() {
        return visit;
    }

    public void setVisit(BigDecimal visit) {
        this.visit = visit;
    }

    public BigDecimal getCart() {
        return cart;
    }

    public void setCart(BigDecimal cart) {
        this.cart = cart;
    }

    public BigDecimal getSalesGoods() {
        return salesGoods;
    }

    public void setSalesGoods(BigDecimal salesGoods) {
        this.salesGoods = salesGoods;
    }
}
