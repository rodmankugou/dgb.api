package com.verificer.beans.suportVo;


import com.verificer.web.common.enums.ClientType;
import com.verificer.web.common.enums.NoticeType;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告Vo
 */

public class AfficheVo implements Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 排序参数
     */
    private Integer sortParameter;


    /**
     * 公告发布时间
     */
    private Date publishTime;

    /**
     * 公告最近更新时间
     */
    private Date updateTime;

    /**
     * 是否启用
     */
    private boolean enable;

    /**
     * 国际化标记
     */
    private String internationalType;

    /**
     * 封面图片地址
     */
    private String coverImg;// 封面图片地址

    /**
     * 落地页地址，如微信落地页地址
     */
    private String externalLinks;// 点击之后的落地页地址

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告类型
     */
    private NoticeType noticeType;

    /**
     * 客户端类型
     */
    private ClientType clientType;

    public AfficheVo() {
    }

    public AfficheVo(Integer id, String title, Integer sortParameter, Date publishTime, Date updateTime, boolean enable, String internationalType, String coverImg, String externalLinks, String content, NoticeType noticeType, ClientType clientType) {
        this.id = id;
        this.title = title;
        this.sortParameter = sortParameter;
        this.publishTime = publishTime;
        this.updateTime = updateTime;
        this.enable = enable;
        this.internationalType = internationalType;
        this.coverImg = coverImg;
        this.externalLinks = externalLinks;
        this.content = content;
        this.noticeType = noticeType;
        this.clientType = clientType;
    }

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeType noticeType) {
        this.noticeType = noticeType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getInternationalType() {
        return internationalType;
    }

    public void setInternationalType(String internationalType) {
        this.internationalType = internationalType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(String externalLinks) {
        this.externalLinks = externalLinks;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
