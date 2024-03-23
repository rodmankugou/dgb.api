package com.verificer.biz.beans.vo.adjust.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class AdjOrdConfirmVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("确认明细")
    private List<AdjOrdConfirmItemVo> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AdjOrdConfirmItemVo> getItems() {
        return items;
    }

    public void setItems(List<AdjOrdConfirmItemVo> items) {
        this.items = items;
    }
}
