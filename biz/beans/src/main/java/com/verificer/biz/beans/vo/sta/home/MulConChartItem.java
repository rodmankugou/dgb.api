package com.verificer.biz.beans.vo.sta.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class MulConChartItem implements Serializable {
    @ApiModelProperty("交易量")
    private BigDecimal trade;
    @ApiModelProperty("访问量")
    private BigDecimal visit;

    public BigDecimal getTrade() {
        return trade;
    }

    public void setTrade(BigDecimal trade) {
        this.trade = trade;
    }

    public BigDecimal getVisit() {
        return visit;
    }

    public void setVisit(BigDecimal visit) {
        this.visit = visit;
    }
}
