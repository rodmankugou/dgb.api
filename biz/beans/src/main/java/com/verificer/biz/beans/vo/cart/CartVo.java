package com.verificer.biz.beans.vo.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class CartVo implements Serializable {
    @ApiModelProperty("商品ID")
    private Long goodsId;
    @ApiModelProperty("规格ID")
    private Long specId;
    @ApiModelProperty("图片")
    private String img;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("规格名称")
    private String specName;
    @ApiModelProperty("数量")
    private Integer count;
    @ApiModelProperty("当前库存数量")
    private BigDecimal stock;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
}
