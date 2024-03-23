package com.verificer.biz.beans.vo.user.member;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class MemberStaVo implements Serializable {
    @ApiModelProperty("累计会员数")
    private Integer totalCount;
    @ApiModelProperty("本月会员数")
    private Integer monthCount;
    @ApiModelProperty("会员费总收入")
    @PriceDecimal
    private BigDecimal totalAmount;
    @ApiModelProperty("本月会员费收入")
    @PriceDecimal
    private BigDecimal monthAmount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(BigDecimal monthAmount) {
        this.monthAmount = monthAmount;
    }
}
