package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SysAccountQueryVo extends PageQueryVo {
    @ApiModelProperty(hidden = true)
    private String subNamePrefix;

    public String getSubNamePrefix() {
        return subNamePrefix;
    }

    public void setSubNamePrefix(String subNamePrefix) {
        this.subNamePrefix = subNamePrefix;
    }
}
