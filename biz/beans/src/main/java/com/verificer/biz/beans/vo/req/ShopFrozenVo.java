package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ShopFrozenVo implements Serializable {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("是否冻结。true-解冻 false-冻结")
    private Boolean frozenFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFrozenFlag() {
        return frozenFlag;
    }

    public void setFrozenFlag(Boolean frozenFlag) {
        this.frozenFlag = frozenFlag;
    }
}
