package com.verificer.biz.beans.vo.user.member;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class MemberStaBaseVo implements Serializable {
    @ApiModelProperty("总人数")
    private Integer totalCount;

    @ApiModelProperty("总金额")
    @PriceDecimal
    private BigDecimal totalAmount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
