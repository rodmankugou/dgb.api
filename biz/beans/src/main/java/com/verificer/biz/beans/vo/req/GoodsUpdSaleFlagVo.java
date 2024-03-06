package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class GoodsUpdSaleFlagVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("是否上架。true-是；false-否")
    private Boolean saleFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(Boolean saleFlag) {
        this.saleFlag = saleFlag;
    }
}
