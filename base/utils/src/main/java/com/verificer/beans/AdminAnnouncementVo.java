package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
@ApiModel
public class AdminAnnouncementVo implements Serializable {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("排序参数，值越小优先级越高")
    private Integer sortParameter;

    @ApiModelProperty("发布时间")
    private Long publishTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("是否启用")
    private Boolean enable;

    @ApiModelProperty("封面图片地址")
    private String coverImg;

    @ApiModelProperty("语言环境，用于国际化")
    private String internationalType;

    @ApiModelProperty("公告类型,1-公告 2-多媒体")
    private Integer noticeType;

    @ApiModelProperty("客户端类型,1-PC端 2-APP端")
    private Integer clientType;

    @ApiModelProperty("封面图片地址")
    private String externalLinks;

    @ApiModelProperty("公告内容")
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSortParameter() {
        return sortParameter;
    }

    public void setSortParameter(Integer sortParameter) {
        this.sortParameter = sortParameter;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getInternationalType() {
        return internationalType;
    }

    public void setInternationalType(String internationalType) {
        this.internationalType = internationalType;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(String externalLinks) {
        this.externalLinks = externalLinks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
