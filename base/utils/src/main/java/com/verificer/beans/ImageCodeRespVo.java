package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/27.
 */
@ApiModel
public class ImageCodeRespVo implements Serializable {
    @ApiModelProperty(value = "图片验证码id，调用需要验证图片验证码的接口时需要带上该参数",required = true)
    private String imageId;
    @ApiModelProperty(value = "图片访问路径",required = true)
    private String url;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
