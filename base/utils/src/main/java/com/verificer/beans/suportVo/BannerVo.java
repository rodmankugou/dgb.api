package com.verificer.beans.suportVo;


import com.amazonaws.services.dynamodbv2.xspec.L;
import com.verificer.web.common.enums.BannerTerminalType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * BannerVo
 */

public class BannerVo implements Serializable{

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

    /**
     * 落地页标题
     */
    private String htmlTitle;// 网页标题

    /**
     * 是否可用
     */
    private boolean enable = true;// 是否可用

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date endTime;


    /**
     * 最近更新时间
     */
    private Date updateTime;


    /**
     * 排序参数
     */
    private Integer sortParameter;

    /**
     * 国际化标记
     */
    private String internationalType;
    /**
     * 终端类型
     */
    private BannerTerminalType terminalType;

    public BannerVo() {
    }


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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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

    public BannerTerminalType getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(BannerTerminalType terminalType) {
        this.terminalType = terminalType;
    }
}
