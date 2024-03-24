package com.verificer.biz.beans.vo.adjust.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AdjDropListReqVo implements Serializable {
    @ApiModelProperty("是否发送方")
    private Boolean isFrom;

    @ApiModelProperty("收货方类型 1-仓库 2-店铺")
    private Integer type;



}
