package com.verificer.biz.beans.vo.req.adjust;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class AdjShopInFormVo implements Serializable {
    @ApiModelProperty("仓库ID")
    private String stageId;
    @ApiModelProperty("店铺ID")
    private String shopId;
    @ApiModelProperty("商品ID")
    private Long goodsId;
    @ApiModelProperty("规格ID")
    private Long specId;
    @ApiModelProperty("数量")
    private BigDecimal count;


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

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
