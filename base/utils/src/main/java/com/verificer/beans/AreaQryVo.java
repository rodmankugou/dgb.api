package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AreaQryVo implements Serializable {
    @ApiModelProperty(value = "行政等级，1-省 2-地级市 3-县/区",required = false)
    private Integer tier;
    @ApiModelProperty(value = "上级地区编码",required = false)
    private String parentCode;

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
