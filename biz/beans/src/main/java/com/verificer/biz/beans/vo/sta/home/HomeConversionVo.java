package com.verificer.biz.beans.vo.sta.home;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class HomeConversionVo implements Serializable {

    @ApiModelProperty("周环比")
    private BigDecimal weekRise;

    @ApiModelProperty("日环比")
    private BigDecimal dayRise;

    @ApiModelProperty("转化率")
    private BigDecimal avg;

    public BigDecimal getWeekRise() {
        return weekRise;
    }

    public void setWeekRise(BigDecimal weekRise) {
        this.weekRise = weekRise;
    }

    public BigDecimal getDayRise() {
        return dayRise;
    }

    public void setDayRise(BigDecimal dayRise) {
        this.dayRise = dayRise;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }
}
