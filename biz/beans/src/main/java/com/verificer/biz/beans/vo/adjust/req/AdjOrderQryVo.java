package com.verificer.biz.beans.vo.adjust.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AdjOrderQryVo extends PageQueryVo {
    @ApiModelProperty("仓库ID/仓库ID")
    private String merId;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }
}
