package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class SpecVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片")
    private String img;



}
