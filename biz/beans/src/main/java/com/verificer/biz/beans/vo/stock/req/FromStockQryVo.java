package com.verificer.biz.beans.vo.stock.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class FromStockQryVo implements Serializable {
    @ApiModelProperty(value = "发货方ID",required = true)
    private String relId;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }
}
