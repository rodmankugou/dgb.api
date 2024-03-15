package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AdvertFormVo implements Serializable {
    @ApiModelProperty(value = "ID,新增时不需要该参数",required = false)
    Long id;

    @ApiModelProperty("名称")
    String name;
    @ApiModelProperty("图片URL")
    String imageUri;
    @ApiModelProperty("小程序页面Short Path")
    String htmlUrl;

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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

}
