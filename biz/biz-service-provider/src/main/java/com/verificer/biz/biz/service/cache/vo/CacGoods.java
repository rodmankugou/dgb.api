package com.verificer.biz.biz.service.cache.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verificer.biz.beans.vo.goods.AGoodsVo;
import com.verificer.biz.beans.vo.goods.ASpecVo;
import com.verificer.biz.biz.entity.Spec;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CacGoods {
    private Long id;
    private Long brandId;
    private String brandName;
    private String name;
    private String img0;
    private String subTitle;
    private Boolean freeShippingFlag;
    private BigDecimal maxLimitCount;
    private BigDecimal minLimitCount;
    private Boolean nonMemberBuyFlag;
    private Integer sumSaleCount;
    private Integer stageCount;
    private BigDecimal price;
    private BigDecimal  oriPrice;
    private List<CacSpec> oriSpecList;
    private Long marketTime;


    /**
     * 用于搜索和排序的附加属性
     */
    private String sKey;
    private String catSKey;
    private Long catId;
    private BigDecimal minPrice;
    private BigDecimal minOrigPrice;
    private Long saleCount;


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

    public List<CacSpec> getOriSpecList() {
        return oriSpecList;
    }

    public void setOriSpecList(List<CacSpec> oriSpecList) {
        this.oriSpecList = oriSpecList;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public String getCatSKey() {
        return catSKey;
    }

    public void setCatSKey(String catSKey) {
        this.catSKey = catSKey;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinOrigPrice() {
        return minOrigPrice;
    }

    public void setMinOrigPrice(BigDecimal minOrigPrice) {
        this.minOrigPrice = minOrigPrice;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public Long getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(Long marketTime) {
        this.marketTime = marketTime;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
