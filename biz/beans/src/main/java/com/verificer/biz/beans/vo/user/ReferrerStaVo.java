package com.verificer.biz.beans.vo.user;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class ReferrerStaVo implements Serializable {
    @ApiModelProperty("总下线人数")
    private Integer refereeCount;
    @ApiModelProperty("总佣金金额")
    @PriceDecimal
    private BigDecimal totalCommission;
    @ApiModelProperty("总提现金额")
    @PriceDecimal
    private BigDecimal totalWithdraw;

    public Integer getRefereeCount() {
        return refereeCount;
    }

    public void setRefereeCount(Integer refereeCount) {
        this.refereeCount = refereeCount;
    }

    public BigDecimal getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
    }

    public BigDecimal getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(BigDecimal totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }
}
