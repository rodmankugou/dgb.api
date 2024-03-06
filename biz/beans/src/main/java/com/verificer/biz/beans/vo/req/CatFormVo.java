package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CatFormVo implements Serializable {
    @ApiModelProperty("id,新增时不需要传递该参数")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(value = "logo,图片URL",required = false)
    private String logoImg;

    @ApiModelProperty("简称")
    private String shortName;



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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
