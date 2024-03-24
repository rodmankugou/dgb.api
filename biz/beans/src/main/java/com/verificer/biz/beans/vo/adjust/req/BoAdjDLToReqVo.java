package com.verificer.biz.beans.vo.adjust.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class BoAdjDLToReqVo implements Serializable {
    @ApiModelProperty("收货方类型 1-仓库 2-店铺")
    private Integer toType;

    public Integer getToType() {
        return toType;
    }

    public void setToType(Integer toType) {
        this.toType = toType;
    }
}
