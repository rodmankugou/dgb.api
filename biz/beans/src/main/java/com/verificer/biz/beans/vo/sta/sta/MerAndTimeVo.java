package com.verificer.biz.beans.vo.sta.sta;

import com.verificer.beans.TimeRangeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MerAndTimeVo extends TimeRangeVo {
    @ApiModelProperty("店铺ID")
    private String merId;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }
}
