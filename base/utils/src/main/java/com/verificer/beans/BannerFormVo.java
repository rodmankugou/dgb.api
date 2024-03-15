package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class BannerFormVo implements Serializable {
    @ApiModelProperty(value = "ID,新增时不需要该参数",required = false)
    Long id;

    @ApiModelProperty("名称")
    String name;
    String content;
    @ApiModelProperty("图片URL")
    String imageUri;
    @ApiModelProperty("小程序页面Short Path")
    String htmlUrl;
    String htmlTitle;
    Boolean enable;
    Long endTime;
    Integer sortParameter;
    String internationalType;
    Integer terminalType;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getHtmlTitle() {
        return htmlTitle;
    }

    public void setHtmlTitle(String htmlTitle) {
        this.htmlTitle = htmlTitle;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getSortParameter() {
        return sortParameter;
    }

    public void setSortParameter(Integer sortParameter) {
        this.sortParameter = sortParameter;
    }

    public String getInternationalType() {
        return internationalType;
    }

    public void setInternationalType(String internationalType) {
        this.internationalType = internationalType;
    }

    public Integer getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Integer terminalType) {
        this.terminalType = terminalType;
    }
}
