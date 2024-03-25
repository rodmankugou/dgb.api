package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class TimeRangeVo implements Serializable {
    @ApiModelProperty("开始时间")
    private Long sTime;
    @ApiModelProperty("开始时间")
    private Long eTime;

    public TimeRangeVo() {
    }

    public TimeRangeVo(Long sTime, Long eTime) {
        this.sTime = sTime;
        this.eTime = eTime;
    }

    public Long getsTime() {
        return sTime;
    }

    public void setsTime(Long sTime) {
        this.sTime = sTime;
    }

    public Long geteTime() {
        return eTime;
    }

    public void seteTime(Long eTime) {
        this.eTime = eTime;
    }
}
