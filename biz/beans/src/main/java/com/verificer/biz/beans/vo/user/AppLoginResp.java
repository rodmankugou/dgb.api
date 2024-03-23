package com.verificer.biz.beans.vo.user;

import com.verificer.beans.WxTokenVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AppLoginResp {
    @ApiModelProperty("token信息")
    WxTokenVo token;
    @ApiModelProperty("用户信息")
    AppUserInfo userInfo;

    public WxTokenVo getToken() {
        return token;
    }

    public void setToken(WxTokenVo token) {
        this.token = token;
    }

    public AppUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(AppUserInfo userInfo) {
        this.userInfo = userInfo;
    }


}
