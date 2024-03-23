package com.verificer.biz.beans.vo.goods;

import com.verificer.biz.beans.vo.shop.AShopBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品详情
 */
@ApiModel
public class AGoodsDtlVo extends AGoodsVo {
    @ApiModelProperty("详情")
    private String detail;

    @ApiModelProperty("图片url列表，多个url以符号”@“隔开")
    private String imgList;

    @ApiModelProperty("最近的门店，如果没有在服务距离内的门店，会返回null")
    private AShopBaseVo nearestShop;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public AShopBaseVo getNearestShop() {
        return nearestShop;
    }

    public void setNearestShop(AShopBaseVo nearestShop) {
        this.nearestShop = nearestShop;
    }
}
