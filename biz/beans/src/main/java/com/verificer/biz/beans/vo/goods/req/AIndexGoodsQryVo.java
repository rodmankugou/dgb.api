package com.verificer.biz.beans.vo.goods.req;

import io.swagger.annotations.ApiModelProperty;

public class AIndexGoodsQryVo extends ABaseGoodQryVo{
    @ApiModelProperty("1-人气爆品 2-每日疯抢 3-非会员专区 4-新品推荐")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
