package com.verificer.biz.beans.vo.goods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.utils.decimal.CountDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class AGoodsVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("商品的第一张图片")
    private String img0;

    @ApiModelProperty("子标题")
    private String subTitle;

    @ApiModelProperty("是否免邮。true-是 false-否")
    private Boolean freeShippingFlag;

    @ApiModelProperty("限购数")
    @CountDecimal
    private BigDecimal maxLimitCount;

    @ApiModelProperty("起购数")
    @CountDecimal
    private BigDecimal minLimitCount;

    @ApiModelProperty("非会员是否能购买")
    private Boolean nonMemberBuyFlag;

    @ApiModelProperty("销售总量")
    private Integer sumSaleCount;

    @ApiModelProperty("库存量")
    private Integer stageCount;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("原价")
    private BigDecimal  oriPrice;

    @ApiModelProperty("规格列表")
    private List<ASpecVo> specList;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg0() {
        return img0;
    }

    public void setImg0(String img0) {
        this.img0 = img0;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Boolean getFreeShippingFlag() {
        return freeShippingFlag;
    }

    public void setFreeShippingFlag(Boolean freeShippingFlag) {
        this.freeShippingFlag = freeShippingFlag;
    }

    public BigDecimal getMaxLimitCount() {
        return maxLimitCount;
    }

    public void setMaxLimitCount(BigDecimal maxLimitCount) {
        this.maxLimitCount = maxLimitCount;
    }

    public BigDecimal getMinLimitCount() {
        return minLimitCount;
    }

    public void setMinLimitCount(BigDecimal minLimitCount) {
        this.minLimitCount = minLimitCount;
    }

    public Boolean getNonMemberBuyFlag() {
        return nonMemberBuyFlag;
    }

    public void setNonMemberBuyFlag(Boolean nonMemberBuyFlag) {
        this.nonMemberBuyFlag = nonMemberBuyFlag;
    }

    public Integer getSumSaleCount() {
        return sumSaleCount;
    }

    public void setSumSaleCount(Integer sumSaleCount) {
        this.sumSaleCount = sumSaleCount;
    }

    public Integer getStageCount() {
        return stageCount;
    }

    public void setStageCount(Integer stageCount) {
        this.stageCount = stageCount;
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


    public List<ASpecVo> getSpecList() {
        return specList;
    }

    public void setSpecList(List<ASpecVo> specList) {
        this.specList = specList;
    }



}
