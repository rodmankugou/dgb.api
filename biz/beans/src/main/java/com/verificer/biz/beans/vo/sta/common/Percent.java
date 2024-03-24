package com.verificer.biz.beans.vo.sta.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class Percent implements Serializable {
    @ApiModelProperty("主体/标题")
    private String title;
    @ApiModelProperty("数量")
    private BigDecimal count;
    @ApiModelProperty("占比")
    private BigDecimal rate;


    public Percent(String title, BigDecimal count, BigDecimal rate) {
        this.title = title;
        this.count = count;
        this.rate = rate;
    }

    public Percent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
