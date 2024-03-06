package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ShopLsVo implements Serializable {
    @ApiModelProperty("营业执照因袭-营业执照，图片URL。")
    private String shopLicense;

    @ApiModelProperty("营业执照信息-统一社会信息码")
    private String shopCode;

    @ApiModelProperty("营业执照-经营范围")
    private String shopOpRange;

    public String getShopLicense() {
        return shopLicense;
    }

    public void setShopLicense(String shopLicense) {
        this.shopLicense = shopLicense;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopOpRange() {
        return shopOpRange;
    }

    public void setShopOpRange(String shopOpRange) {
        this.shopOpRange = shopOpRange;
    }
}
