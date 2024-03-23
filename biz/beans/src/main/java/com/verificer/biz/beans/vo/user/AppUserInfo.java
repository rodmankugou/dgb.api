package com.verificer.biz.beans.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AppUserInfo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("是否会员 true-是 false-否")
    private Boolean memberFlag;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty(value = "手机号码",required = false)
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(Boolean memberFlag) {
        this.memberFlag = memberFlag;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
