package com.verificer.biz.beans.vo.goods.req;

import io.swagger.annotations.ApiModelProperty;

public class AShopGoodsQryVo extends ABaseGoodQryVo{
    @ApiModelProperty(value = "商品分类ID",required = true)
    private Long catId;

    @ApiModelProperty(value = "店铺ID",required = true)
    private String shopId;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }
}
