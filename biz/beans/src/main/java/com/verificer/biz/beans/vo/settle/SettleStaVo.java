package com.verificer.biz.beans.vo.settle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class SettleStaVo implements Serializable {
    @ApiModelProperty("剩余未结算期数")
    private Integer restCount;
    @ApiModelProperty("已结算期数")
    private Integer settledCount;
    @ApiModelProperty("剩余未结算金额")
    private BigDecimal restAmount;
    @ApiModelProperty("已结算金额")
    private BigDecimal settledAmount;

    public Integer getRestCount() {
        return restCount;
    }

    public void setRestCount(Integer restCount) {
        this.restCount = restCount;
    }

    public Integer getSettledCount() {
        return settledCount;
    }

    public void setSettledCount(Integer settledCount) {
        this.settledCount = settledCount;
    }

    public BigDecimal getRestAmount() {
        return restAmount;
    }

    public void setRestAmount(BigDecimal restAmount) {
        this.restAmount = restAmount;
    }

    public BigDecimal getSettledAmount() {
        return settledAmount;
    }

    public void setSettledAmount(BigDecimal settledAmount) {
        this.settledAmount = settledAmount;
    }
}
