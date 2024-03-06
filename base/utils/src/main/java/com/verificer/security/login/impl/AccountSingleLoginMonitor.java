package com.verificer.security.login.impl;

import com.verificer.security.login.LoginMode;

/**
 * Created by 35336 on 2020/12/29.
 */
public class AccountSingleLoginMonitor extends LoginMonitorBase{
    //单位为分钟
    private long keepTime;
    //单位为分钟
    private long tokenAliveTime;
    private boolean isEnableForceLogOut;
    private String appName;

    public AccountSingleLoginMonitor() {
        super.mode = LoginMode.ACCOUNT_SINGLE;
    }

    public synchronized void setEnableForceLogOut(boolean enableForceLogOut) {
        isEnableForceLogOut = enableForceLogOut;
    }

    public synchronized void setTokenAliveTime(long tokenAliveTime) {
        this.tokenAliveTime = tokenAliveTime;
    }

    public synchronized void setKeepTime(long keepTime) {
        this.keepTime = keepTime;
    }

    @Override
    synchronized long  getLoginKeepTime() {
        return keepTime * 60;
    }

    @Override
    synchronized long getTokenAliveTime() {
        return tokenAliveTime * 60;
    }

    @Override
    synchronized boolean isEnableForceLogOut() {
        return isEnableForceLogOut;
    }

    @Override
    synchronized String getAppName() {
        return appName;
    }

    public synchronized void setAppName(String appName) {
        this.appName = appName;
    }
}
