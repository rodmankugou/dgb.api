package com.verificer.biz.beans.vo.goods.req;

import io.swagger.annotations.ApiModelProperty;

public class ARankGoodsQryVo extends ABaseGoodQryVo{
    @ApiModelProperty(value = "排序类型，1-综合 2-销量 3-价格",required = true)
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
