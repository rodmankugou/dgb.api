package com.verificer.biz.beans.vo.goods;

import com.verificer.utils.decimal.CountDecimal;
import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class    ASpecVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("价格")
    @PriceDecimal
    private BigDecimal price;

    @ApiModelProperty("原价")
    @PriceDecimal
    private BigDecimal oriPrice;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("库存量，目前会返回两个库存量，一个是平台仓库的库存量（云上商城），一个是最近店铺的库存量（门店）。如果商品没有在任何门店上架，则只会返回平台的库存量")
    private List<ASpecStockVo> stocks;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(BigDecimal oriPrice) {
        this.oriPrice = oriPrice;
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

    public List<ASpecStockVo> getStocks() {
        return stocks;
    }

    public void setStocks(List<ASpecStockVo> stocks) {
        this.stocks = stocks;
    }




}
