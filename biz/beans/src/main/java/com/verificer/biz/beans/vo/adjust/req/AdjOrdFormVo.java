package com.verificer.biz.beans.vo.adjust.req;

import com.verificer.biz.beans.vo.req.adjust.AdjShopBatchItemVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class AdjOrdFormVo implements Serializable {
    @ApiModelProperty("仓库ID")
    private String fromId;
    @ApiModelProperty("店铺ID")
    private String toId;

    @ApiModelProperty("收货方类型 1-仓库 2-门店")
    private Integer fromType;

    @ApiModelProperty("收货方类型 1-仓库 2-门店")
    private Integer toType;

    @ApiModelProperty("进货条目")
    private List<AdjItemFormVo> items;


    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public List<AdjItemFormVo> getItems() {
        return items;
    }

    public void setItems(List<AdjItemFormVo> items) {
        this.items = items;
    }

    public Integer getToType() {
        return toType;
    }

    public void setToType(Integer toType) {
        this.toType = toType;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }
}
