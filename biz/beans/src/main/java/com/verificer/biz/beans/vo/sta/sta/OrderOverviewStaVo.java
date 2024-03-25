package com.verificer.biz.beans.vo.sta.sta;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class OrderOverviewStaVo implements Serializable {
    @ApiModelProperty("订单总金额")
    private BigDecimal total;
    @ApiModelProperty("订单数")
    private BigDecimal count;
    @ApiModelProperty("订单商品")
    private BigDecimal goods;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getGoods() {
        return goods;
    }

    public void setGoods(BigDecimal goods) {
        this.goods = goods;
    }
}
