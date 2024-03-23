package com.verificer.biz.beans.vo.goods.req;

import io.swagger.annotations.ApiModelProperty;

public class APlaGoodsQryVo extends ABaseGoodQryVo{
    @ApiModelProperty(value = "商品分类ID",required = true)
    private Long catId;

    @ApiModelProperty(value = "非会员专区 true-否 false-是",required = false)
    private Boolean nonMemberFlag;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Boolean getNonMemberFlag() {
        return nonMemberFlag;
    }

    public void setNonMemberFlag(Boolean nonMemberFlag) {
        this.nonMemberFlag = nonMemberFlag;
    }
}
