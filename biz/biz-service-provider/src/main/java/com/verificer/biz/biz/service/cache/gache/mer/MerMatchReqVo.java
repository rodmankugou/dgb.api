package com.verificer.biz.biz.service.cache.gache.mer;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class MerMatchReqVo {
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("库存阈值，值大于等于该阈值的仓库/门店的匹配优先级更高")
    private BigDecimal priorityThresholdCount;

    @ApiModelProperty("最大距离要求，单位米")
    private Long distanceLimit;

    @ApiModelProperty("如果值存在，表示查指定店铺的库存")
    private String shopId;

    public MerMatchReqVo() {
    }

    public MerMatchReqVo(String shopId,BigDecimal longitude, BigDecimal latitude, BigDecimal priorityThresholdCount, Long distanceLimit) {
        this.shopId = shopId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.priorityThresholdCount = priorityThresholdCount;
        this.distanceLimit = distanceLimit;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }



    public BigDecimal getPriorityThresholdCount() {
        return priorityThresholdCount;
    }

    public void setPriorityThresholdCount(BigDecimal priorityThresholdCount) {
        this.priorityThresholdCount = priorityThresholdCount;
    }

    public Long getDistanceLimit() {
        return distanceLimit;
    }

    public void setDistanceLimit(Long distanceLimit) {
        this.distanceLimit = distanceLimit;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
