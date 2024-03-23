package com.verificer.biz.beans.vo.req;

import com.verificer.beans.IdVo;
import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@ApiModel
public class UserSetRefVo extends IdVo {
    @ApiModelProperty("开关，true-开 false-关")
    private Boolean enableFlag;

    @ApiModelProperty("佣金金额")
    @PriceDecimal
    private BigDecimal commission;

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
    }
}
