package com.verificer.base_user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by 35336 on 2020/12/26.
 */
@Component
@PropertySource(value = {"classpath:properties/config.properties"})
@ConfigurationProperties(prefix = "config")
public class UsrSrvConfig {
    private boolean isDebug;
    /**
     * 找回密码场景的验证码错误次数限制，{period}分钟内最多允许{count}次错误
     */
    private long forgetPwdLimitPeriod;
    private int forgetPwdLimitCount;

    /**
     * 登录场景的密码错误次数限制，{period}分钟内最多允许{count}次错误
     */
    private long loginPwdErrLmtPeriod;
    private int loginPwdErrLmtCount;

    private String jumioSecret;
    private String jumioToken;

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void setForgetPwdLimitPeriod(long forgetPwdLimitPeriod) {
        this.forgetPwdLimitPeriod = forgetPwdLimitPeriod;
    }

    public void setForgetPwdLimitCount(int forgetPwdLimitCount) {
        this.forgetPwdLimitCount = forgetPwdLimitCount;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public long getForgetPwdLimitPeriod() {
        return forgetPwdLimitPeriod;
    }

    public int getForgetPwdLimitCount() {
        return forgetPwdLimitCount;
    }

    public long getLoginPwdErrLmtPeriod() {
        return loginPwdErrLmtPeriod;
    }

    public void setLoginPwdErrLmtPeriod(long loginPwdErrLmtPeriod) {
        this.loginPwdErrLmtPeriod = loginPwdErrLmtPeriod;
    }

    public int getLoginPwdErrLmtCount() {
        return loginPwdErrLmtCount;
    }

    public void setLoginPwdErrLmtCount(int loginPwdErrLmtCount) {
        this.loginPwdErrLmtCount = loginPwdErrLmtCount;
    }

    public LimitedConfig getForgetPwdLimitCfg(){
        return new LimitedConfig(forgetPwdLimitPeriod,forgetPwdLimitCount);
    }

    public LimitedConfig getLoginPwdErrLmtCfg(){
        return new LimitedConfig(loginPwdErrLmtPeriod,loginPwdErrLmtCount);
    }

    public String getJumioSecret() {
        return jumioSecret;
    }

    public void setJumioSecret(String jumioSecret) {
        this.jumioSecret = jumioSecret;
    }

    public String getJumioToken() {
        return jumioToken;
    }

    public void setJumioToken(String jumioToken) {
        this.jumioToken = jumioToken;
    }
}
