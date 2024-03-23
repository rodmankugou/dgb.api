package com.verificer.biz.beans.vo.user.withdraw;

import com.verificer.beans.IdVo;
import com.verificer.utils.decimal.PrcLimit;
import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class ReferrerFormVo implements Serializable {

    private Long userId;

    @ApiModelProperty("金额")
    @PrcLimit(2)
    private BigDecimal amount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
