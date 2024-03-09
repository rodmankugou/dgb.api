package com.verificer.biz.beans.vo.req.adjust;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class AdjShopBatchInVo implements Serializable {
    @ApiModelProperty("仓库ID")
    private String stageId;
    @ApiModelProperty("店铺ID")
    private String shopId;

    @ApiModelProperty("进货条目")
    private List<AdjShopBatchItemVo> items;

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

    public List<AdjShopBatchItemVo> getItems() {
        return items;
    }

    public void setItems(List<AdjShopBatchItemVo> items) {
        this.items = items;
    }
}
