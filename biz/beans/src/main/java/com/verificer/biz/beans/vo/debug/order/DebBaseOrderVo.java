package com.verificer.biz.beans.vo.debug.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DebBaseOrderVo {
    @ApiModelProperty("订单是否多个商品")
    private Boolean multiGoodsFlag;

    public Boolean getMultiGoodsFlag() {
        return multiGoodsFlag;
    }

    public void setMultiGoodsFlag(Boolean multiGoodsFlag) {
        this.multiGoodsFlag = multiGoodsFlag;
    }
}
