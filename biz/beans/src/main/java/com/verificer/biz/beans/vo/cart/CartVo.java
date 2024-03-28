package com.verificer.biz.beans.vo.cart;

import com.verificer.utils.decimal.CountDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class CartVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;
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
    @CountDecimal
    private BigDecimal stock;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("原价")
    private BigDecimal origPrice;

    @ApiModelProperty("当前用户是否会员")
    private Boolean userMemberFlag;


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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(BigDecimal origPrice) {
        this.origPrice = origPrice;
    }

    public Boolean getUserMemberFlag() {
        return userMemberFlag;
    }

    public void setUserMemberFlag(Boolean userMemberFlag) {
        this.userMemberFlag = userMemberFlag;
    }
}
