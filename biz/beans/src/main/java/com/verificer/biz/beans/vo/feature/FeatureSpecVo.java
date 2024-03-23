package com.verificer.biz.beans.vo.feature;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class FeatureSpecVo implements Serializable {
    @ApiModelProperty("商品ID")
    private Long goodsId;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("规格ID")
    private Long specId;
    @ApiModelProperty("规格名称")
    private String specName;
    @ApiModelProperty("规格图")
    private String specImg;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecImg() {
        return specImg;
    }

    public void setSpecImg(String specImg) {
        this.specImg = specImg;
    }
}
