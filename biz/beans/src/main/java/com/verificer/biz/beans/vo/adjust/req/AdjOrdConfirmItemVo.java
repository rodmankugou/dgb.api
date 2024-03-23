package com.verificer.biz.beans.vo.adjust.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class AdjOrdConfirmItemVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("实际配货数量")
    private BigDecimal realCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRealCount() {
        return realCount;
    }

    public void setRealCount(BigDecimal realCount) {
        this.realCount = realCount;
    }
}
