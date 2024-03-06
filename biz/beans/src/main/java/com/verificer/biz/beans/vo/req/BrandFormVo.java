package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BrandFormVo implements Serializable {
    @ApiModelProperty("id,新增时不需要传递该参数")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(value = "logo,图片URL",required = false)
    private String logoImg;

    @ApiModelProperty("首字母")
    private String firstChar;

    @ApiModelProperty("排序优先级，值越小优先级越高")
    private Integer priority;


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

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
