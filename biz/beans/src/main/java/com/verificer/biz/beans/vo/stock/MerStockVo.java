package com.verificer.biz.beans.vo.stock;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class MerStockVo implements Serializable {
    @ApiModelProperty("商品或者规格ID")
    private Long id;
    @ApiModelProperty(hidden = true)
    private String relId;
    @ApiModelProperty(hidden = true)
    private Long goodsId;
    @ApiModelProperty("商品或者规格ID")
    private String name;
    @ApiModelProperty(hidden = true)
    private String goodsName;
    @ApiModelProperty("价格")
    private String priceTxt;
    @PriceDecimal
    @ApiModelProperty(hidden = true)
    private BigDecimal price;
    @ApiModelProperty(hidden = true)
    private String goodsImg;
    @ApiModelProperty("图片")
    private String img;
    @ApiModelProperty("库存数量")
    private BigDecimal count;
    private List<MerStockVo> specList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public List<MerStockVo> getSpecList() {
        return specList;
    }

    public void setSpecList(List<MerStockVo> specList) {
        this.specList = specList;
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

    public String getPriceTxt() {
        return priceTxt;
    }

    public void setPriceTxt(String priceTxt) {
        this.priceTxt = priceTxt;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }
}
