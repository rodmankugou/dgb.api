package com.verificer.biz.beans.vo.req;

import com.verificer.utils.decimal.PrcLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class SpecReqVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("价格")
    @PrcLimit(2)
    private BigDecimal price;

    @ApiModelProperty("原价")
    @PrcLimit(2)
    private BigDecimal oriPrice;

    @ApiModelProperty("按重量计价价格")
    @PrcLimit(2)
    private BigDecimal wPrice;

    @ApiModelProperty("按重量计价价格原价")
    @PrcLimit(2)
    private BigDecimal wOriPrice;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public BigDecimal getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(BigDecimal oriPrice) {
        this.oriPrice = oriPrice;
    }

    public BigDecimal getwOriPrice() {
        return wOriPrice;
    }

    public void setwOriPrice(BigDecimal wOriPrice) {
        this.wOriPrice = wOriPrice;
    }
}
