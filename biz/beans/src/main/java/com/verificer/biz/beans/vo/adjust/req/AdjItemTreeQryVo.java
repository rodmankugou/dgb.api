package com.verificer.biz.beans.vo.adjust.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AdjItemTreeQryVo implements Serializable {
    @ApiModelProperty("调货单ID")
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
