package com.verificer.biz.beans.vo.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class SetAvatarVo implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long userId;
    @ApiModelProperty("头像图片URL")
    private String avatar;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
