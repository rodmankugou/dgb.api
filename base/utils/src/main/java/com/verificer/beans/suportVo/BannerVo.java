package com.verificer.beans.suportVo;


import com.verificer.web.common.enums.BannerTerminalType;

import java.io.Serializable;
import java.util.Date;

/**
 * BannerVo
 */

public class BannerVo implements Serializable{

    /**
     * ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;// 名称

    /**
     * 图片地址
     */
    private String imageURI;// 图片地址

    /**
     * 落地页地址，如微信落地页地址
     */
    private String htmlURL;// 点击之后的落地页地址

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

    public BannerVo(String name, String imageURI, String htmlURL, String htmlTitle, boolean enable, Date createTime, Date endTime, Date updateTime, Integer sortParameter, String internationalType) {
        this.name = name;
        this.imageURI = imageURI;
        this.htmlURL = htmlURL;
        this.htmlTitle = htmlTitle;
        this.enable = enable;
        this.createTime = createTime;
        this.endTime = endTime;
        this.updateTime = updateTime;
        this.sortParameter = sortParameter;
        this.internationalType = internationalType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
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
