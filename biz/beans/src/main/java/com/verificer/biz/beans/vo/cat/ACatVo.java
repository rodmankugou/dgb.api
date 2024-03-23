package com.verificer.biz.beans.vo.cat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ACatVo {
    @ApiModelProperty("id,新增时不需要传递该参数")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(value = "logo,图片URL",required = false)
    private String logoImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }
}
