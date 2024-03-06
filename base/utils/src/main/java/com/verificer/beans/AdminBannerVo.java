package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
@ApiModel
public class AdminBannerVo implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUri;

    @ApiModelProperty("落地页地址")
    private String htmlUrl;

    @ApiModelProperty("落地页标题")
    private String htmlTitle;

    @ApiModelProperty("是否启用，true or false")
    private Boolean enable;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("排序参数，值越小优先级越高")
    private Integer sortParameter;

    @ApiModelProperty("语言环境，用于国际化")
    private String internationalType;

    @ApiModelProperty("终端类型,1-PC网页端 2-app端 3-app活动使用的banner 4-活动Banner")
    private Integer terminalType;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
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
