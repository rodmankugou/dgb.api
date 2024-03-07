package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class AdjustBatchVo implements Serializable {
    @ApiModelProperty("仓库ID")
    private String stageId;
    @ApiModelProperty("店铺ID")
    private String shopId;

    private List<AdjustBatchItemVo> items;

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

    public List<AdjustBatchItemVo> getItems() {
        return items;
    }

    public void setItems(List<AdjustBatchItemVo> items) {
        this.items = items;
    }
}
