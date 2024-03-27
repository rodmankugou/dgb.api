package com.verificer.biz.biz.service.cache.gache.mer;

import com.verificer.biz.biz.service.cache.vo.CacStock;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class MerMatchReqVo {
    @ApiModelProperty("经度")
    private BigDecimal longitude;
    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("最小库存要求")
    private BigDecimal minStock;

    @ApiModelProperty("最大距离要求，单位米")
    private Long distanceLimit;



    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }



    public BigDecimal getMinStock() {
        return minStock;
    }

    public void setMinStock(BigDecimal minStock) {
        this.minStock = minStock;
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
}
