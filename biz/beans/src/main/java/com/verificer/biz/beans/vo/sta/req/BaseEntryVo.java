package com.verificer.biz.beans.vo.sta.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class BaseEntryVo implements Serializable {

    @ApiModelProperty("渠道 1-全部 2-线上 3-门店")
    private Integer entryType;

    public Integer getEntryType() {
        return entryType;
    }

    public void setEntryType(Integer entryType) {
        this.entryType = entryType;
    }
}
