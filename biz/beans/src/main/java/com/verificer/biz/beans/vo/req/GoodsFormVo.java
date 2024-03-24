package com.verificer.biz.beans.vo.req;

import com.verificer.utils.decimal.PrcLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class GoodsFormVo implements Serializable {
    @ApiModelProperty(value = "ID，新增时不需要该参数",required = false)
    private Long id;

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("品牌ID")
    private Long brandId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("推荐词")
    private String subTitle;

    @ApiModelProperty("图片url列表，多个url以符号”@“隔开")
    private String imgList;

    @ApiModelProperty("搜索关键字")
    private String keyWord;

    @ApiModelProperty("是否免邮。true-是 false-否")
    private Boolean freeShippingFlag;

    @ApiModelProperty("重量")
    @PrcLimit(2)
    private BigDecimal weight;

    @ApiModelProperty("体积")
    @PrcLimit(6)
    private BigDecimal volume;

    @ApiModelProperty("限购数")
    @PrcLimit(2)
    private BigDecimal maxLimitCount;

    @ApiModelProperty("起购数")
    @PrcLimit(2)
    private BigDecimal minLimitCount;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("是否上架")
    private Boolean saleFlag;

    @ApiModelProperty("是否定时下架。true-是；false-否")
    private Boolean saleTimeOutFlag;

    @ApiModelProperty("定时下架时间")
    private Long stopSaleTime;

    @ApiModelProperty("详情")
    private String detail;

    @ApiModelProperty("门店是否按重量计价，true-是 false-否")
    private Boolean posByWeightFlag;

    @ApiModelProperty("销售渠道-小程序销售")
    private Boolean appSaleFlag;

    @ApiModelProperty("销售渠道-线下门店销售")
    private Boolean shopSaleFlag;

    @ApiModelProperty("非会员是否能购买")
    private Boolean nonMemberBuyFlag;


    @ApiModelProperty("规格列表")
    private List<SpecReqVo> specList;


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

    public List<SpecReqVo> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecReqVo> specList) {
        this.specList = specList;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getPosByWeightFlag() {
        return posByWeightFlag;
    }

    public void setPosByWeightFlag(Boolean posByWeightFlag) {
        this.posByWeightFlag = posByWeightFlag;
    }

    public Boolean getAppSaleFlag() {
        return appSaleFlag;
    }

    public void setAppSaleFlag(Boolean appSaleFlag) {
        this.appSaleFlag = appSaleFlag;
    }

    public Boolean getShopSaleFlag() {
        return shopSaleFlag;
    }

    public void setShopSaleFlag(Boolean shopSaleFlag) {
        this.shopSaleFlag = shopSaleFlag;
    }

    public Boolean getNonMemberBuyFlag() {
        return nonMemberBuyFlag;
    }

    public void setNonMemberBuyFlag(Boolean nonMemberBuyFlag) {
        this.nonMemberBuyFlag = nonMemberBuyFlag;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
