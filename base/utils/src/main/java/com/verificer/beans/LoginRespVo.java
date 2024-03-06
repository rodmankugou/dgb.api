package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/25.
 */
@ApiModel
public class LoginRespVo implements Serializable{
    private String username;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("邮件")
    private String email;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("sign,用于后续的谷歌身份验证器校验")
    private String sign;
    private Boolean googleAuthIsOpen;
    private Boolean googleSecretAuth;
    @ApiModelProperty("token生成时间")
    private Long tokenGenerateTime;

    /**
     * 是否需要进一步的谷歌认证
     */
    @ApiModelProperty("是否需要进一步的谷歌认证")
    private boolean needGoogleAuth = true;

    /**
     * 用户的地址
     */
    private String walletAddress ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getGoogleAuthIsOpen() {
        return googleAuthIsOpen;
    }

    public void setGoogleAuthIsOpen(Boolean googleAuthIsOpen) {
        this.googleAuthIsOpen = googleAuthIsOpen;
    }

    public Boolean getGoogleSecretAuth() {
        return googleSecretAuth;
    }

    public void setGoogleSecretAuth(Boolean googleSecretAuth) {
        this.googleSecretAuth = googleSecretAuth;
    }


    public Long getTokenGenerateTime() {
        return tokenGenerateTime;
    }

    public void setTokenGenerateTime(Long tokenGenerateTime) {
        this.tokenGenerateTime = tokenGenerateTime;
    }

    public boolean isNeedGoogleAuth() {
        return needGoogleAuth;
    }

    public void setNeedGoogleAuth(boolean needGoogleAuth) {
        this.needGoogleAuth = needGoogleAuth;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
