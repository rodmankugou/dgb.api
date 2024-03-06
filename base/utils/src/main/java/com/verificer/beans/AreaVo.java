package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AreaVo implements Serializable {
    @ApiModelProperty("地区编码")
    private String code;

    @ApiModelProperty("上级地区编码")
    private String parentCode;

    @ApiModelProperty("行政等级，1-省 2-地级市 3-县/区")
    private Integer tier;

    @ApiModelProperty("名称")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
