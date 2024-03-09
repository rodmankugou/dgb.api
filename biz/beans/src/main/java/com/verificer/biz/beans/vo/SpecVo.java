package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class SpecVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("按重量计价价格")
    private BigDecimal wPrice;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("平台销售总量")
    private Integer plaSaleCount;
    @ApiModelProperty("平台库存总量")
    private Integer plaStageCount;
    @ApiModelProperty("店铺销售总量")
    private Integer shopSaleCount;
    @ApiModelProperty("店铺库存总量")
    private Integer shopStageCount;

    @ApiModelProperty("销售总量")
    private Integer sumSaleCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getwPrice() {
        return wPrice;
    }

    public void setwPrice(BigDecimal wPrice) {
        this.wPrice = wPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPlaSaleCount() {
        return plaSaleCount;
    }

    public void setPlaSaleCount(Integer plaSaleCount) {
        this.plaSaleCount = plaSaleCount;
    }

    public Integer getPlaStageCount() {
        return plaStageCount;
    }

    public void setPlaStageCount(Integer plaStageCount) {
        this.plaStageCount = plaStageCount;
    }

    public Integer getShopSaleCount() {
        return shopSaleCount;
    }

    public void setShopSaleCount(Integer shopSaleCount) {
        this.shopSaleCount = shopSaleCount;
    }

    public Integer getShopStageCount() {
        return shopStageCount;
    }

    public void setShopStageCount(Integer shopStageCount) {
        this.shopStageCount = shopStageCount;
    }

    public Integer getSumSaleCount() {
        return sumSaleCount;
    }

    public void setSumSaleCount(Integer sumSaleCount) {
        this.sumSaleCount = sumSaleCount;
    }


}
