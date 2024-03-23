package com.verificer.biz.beans.vo.goods.req;

import com.verificer.beans.PageQueryVo;
import com.verificer.utils.decimal.PrcLimit;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ABaseGoodQryVo extends PageQueryVo {
    @ApiModelProperty("经度")
    @PrcLimit( 6)
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    @PrcLimit( 6)
    private BigDecimal latitude;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
