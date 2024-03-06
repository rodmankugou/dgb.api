package com.verificer.security.login;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/29.
 */
public class LoginStat<T> implements Serializable{
    private boolean isLogin;
    private Integer logoutType;
    private T userInfo;

    public LoginStat(T userInfo) {
        this.isLogin = true;
        this.userInfo = userInfo;
    }

    public LoginStat(Integer logoutType) {
        this.isLogin = false;
        this.logoutType = logoutType;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public Integer getLogoutType() {
        return logoutType;
    }

    public void setLogoutType(Integer logoutType) {
        this.logoutType = logoutType;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }
}
