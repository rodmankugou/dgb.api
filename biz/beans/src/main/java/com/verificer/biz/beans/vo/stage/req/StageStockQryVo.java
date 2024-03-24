package com.verificer.biz.beans.vo.stage.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class StageStockQryVo extends PageQueryVo {

    @ApiModelProperty(value = "品牌ID",required = false)
    private Long brandId;
    @ApiModelProperty(value = "分类ID",required = false)
    private Long categoryId;

    @ApiModelProperty(value = "店铺",required = false)
    private String id;

    @ApiModelProperty(value = "goodsName",required = false)
    private String goodsName;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
