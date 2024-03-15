package com.verificer.beans.suportVo;

import com.verificer.web.common.enums.BannerTerminalType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class AdvertVo implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;// 名称

    /**
     * 图片地址
     */
    @ApiModelProperty("图片")
    private String imageUri;// 图片地址

    /**
     * 落地页地址，如微信落地页地址
     */
    @ApiModelProperty("小程序 Short Path")
    private String htmlUrl;// 点击之后的落地页地址

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
