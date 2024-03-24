package com.verificer.biz.beans.vo.sta.home;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class HomeSalesVo implements Serializable {
    @PriceDecimal
    @ApiModelProperty("总金额")
    private BigDecimal total;

    @ApiModelProperty("周环比")
    private BigDecimal weekRat;

    @ApiModelProperty("日环比")
    private BigDecimal dayRat;


    @ApiModelProperty("日均销售额")
    @PriceDecimal
    private BigDecimal dayAvg;


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getWeekRat() {
        return weekRat;
    }

    public void setWeekRat(BigDecimal weekRat) {
        this.weekRat = weekRat;
    }

    public BigDecimal getDayRat() {
        return dayRat;
    }

    public void setDayRat(BigDecimal dayRat) {
        this.dayRat = dayRat;
    }

    public BigDecimal getDayAvg() {
        return dayAvg;
    }

    public void setDayAvg(BigDecimal dayAvg) {
        this.dayAvg = dayAvg;
    }
}
