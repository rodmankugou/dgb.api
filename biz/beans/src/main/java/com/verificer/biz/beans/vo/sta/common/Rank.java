package com.verificer.biz.beans.vo.sta.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class Rank implements Serializable {
    @ApiModelProperty(value = "主体",required = true)
    private String title;
    @ApiModelProperty(value = "量",required = true)
    private BigDecimal vol;
    @ApiModelProperty(value = "涨幅",required = false)
    private BigDecimal rise;

    public Rank() {
    }

    public Rank(String title, BigDecimal vol) {
        this.title = title;
        this.vol = vol;
    }

    public Rank(String title, BigDecimal vol, BigDecimal rise) {
        this.title = title;
        this.vol = vol;
        this.rise = rise;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }
}
