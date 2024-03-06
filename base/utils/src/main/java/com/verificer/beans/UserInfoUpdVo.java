package com.verificer.beans;

import java.io.Serializable;
import java.util.Map;

public class UserInfoUpdVo implements Serializable {
    private String avatar;

    private String realName;

    private String nickName;

    private String email;

    private String remark;

    private String twiLink;

    private String fbLink;

    private String webLink;

    private String insLink;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTwiLink() {
        return twiLink;
    }

    public void setTwiLink(String twiLink) {
        this.twiLink = twiLink;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getInsLink() {
        return insLink;
    }

    public void setInsLink(String insLink) {
        this.insLink = insLink;
    }
}
