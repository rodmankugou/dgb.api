package com.verificer.biz.beans.vo.stock.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class StockMerStaQryVo extends PageQueryVo {
    @ApiModelProperty(hidden = true)
    private Boolean stageFlag;



    @ApiModelProperty(value = "品牌ID",required = false)
    private Long brandId;
    @ApiModelProperty(value = "分类ID",required = false)
    private Long categoryId;

    @ApiModelProperty(value = "门店/店铺ID",required = false)
    private String relId;

    @ApiModelProperty(value = "goodsName",required = false)
    private String goodsName;

    public Boolean getStageFlag() {
        return stageFlag;
    }

    public void setStageFlag(Boolean stageFlag) {
        this.stageFlag = stageFlag;
    }

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

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
