package com.verificer.biz.beans.vo.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AdjustPageVo extends PageQueryVo {
    @ApiModelProperty("仓库ID")
    private String stageId;
    @ApiModelProperty("店铺ID")
    private String shopId;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("仓库名称")
    private String stageName;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("商品ID")
    private Long goodsId;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
