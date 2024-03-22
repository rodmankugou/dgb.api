package com.verificer.biz.beans.vo.user.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class MemberRankVo implements Serializable {
    @ApiModelProperty("省份名")
    private String areaName;
    @ApiModelProperty("省份码")
        private String areaCode;
    @ApiModelProperty("会员素")
    private Integer count;
    @ApiModelProperty("会员占比")
    private String percent;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
