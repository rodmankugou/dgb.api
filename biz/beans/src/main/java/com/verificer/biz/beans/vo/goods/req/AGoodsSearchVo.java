package com.verificer.biz.beans.vo.goods.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AGoodsSearchVo extends ABaseGoodQryVo {
    @ApiModelProperty("类型 1-商品 2-分类")
    private Integer type;
    @ApiModelProperty("搜索关键子")
    private String sKey;



    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }
}
