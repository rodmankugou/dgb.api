package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class GoodsVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("品牌ID")
    private Long brandId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片url列表，多个url以符号”@“隔开")
    private String imgList;

    @ApiModelProperty("搜索关键字")
    private String keyWord;

    @ApiModelProperty("是否免邮。true-是 false-否")
    private Boolean freeShippingFlag;

    @ApiModelProperty("重量")
    private BigDecimal weight;

    @ApiModelProperty("体积")
    private BigDecimal volume;

    @ApiModelProperty("限购数")
    private Integer maxLimitCount;

    @ApiModelProperty("起购数")
    private Integer minLimitCount;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("是否上架")
    private Boolean saleFlag;

    @ApiModelProperty("是否定时下架。true-是；false-否")
    private Boolean saleTimeOutFlag;

    @ApiModelProperty("定时下架时间")
    private Long stopSaleTime;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("是否在回收站")
    private Boolean rubbishFlag;

    @ApiModelProperty("放入回收站的时间")
    private Long rubbishTime;


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

    @ApiModelProperty("库存量，Web端和商家端使用")
    private Integer stageCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Boolean getFreeShippingFlag() {
        return freeShippingFlag;
    }

    public void setFreeShippingFlag(Boolean freeShippingFlag) {
        this.freeShippingFlag = freeShippingFlag;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Integer getMaxLimitCount() {
        return maxLimitCount;
    }

    public void setMaxLimitCount(Integer maxLimitCount) {
        this.maxLimitCount = maxLimitCount;
    }

    public Integer getMinLimitCount() {
        return minLimitCount;
    }

    public void setMinLimitCount(Integer minLimitCount) {
        this.minLimitCount = minLimitCount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(Boolean saleFlag) {
        this.saleFlag = saleFlag;
    }

    public Boolean getSaleTimeOutFlag() {
        return saleTimeOutFlag;
    }

    public void setSaleTimeOutFlag(Boolean saleTimeOutFlag) {
        this.saleTimeOutFlag = saleTimeOutFlag;
    }

    public Long getStopSaleTime() {
        return stopSaleTime;
    }

    public void setStopSaleTime(Long stopSaleTime) {
        this.stopSaleTime = stopSaleTime;
    }

    public Boolean getRubbishFlag() {
        return rubbishFlag;
    }

    public void setRubbishFlag(Boolean rubbishFlag) {
        this.rubbishFlag = rubbishFlag;
    }

    public Long getRubbishTime() {
        return rubbishTime;
    }

    public void setRubbishTime(Long rubbishTime) {
        this.rubbishTime = rubbishTime;
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

    public Integer getStageCount() {
        return stageCount;
    }

    public void setStageCount(Integer stageCount) {
        this.stageCount = stageCount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
