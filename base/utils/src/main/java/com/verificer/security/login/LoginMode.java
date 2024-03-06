package com.verificer.security.login;

/**
 * Created by 35336 on 2020/12/29.
 */
public enum LoginMode {
    ACCOUNT_SINGLE("单账号唯一登录"),CLIENT_SINGLE("每种客户端唯一登录"),NO_LIMIT("不限制账号的登录次数");
    private String desc;

    LoginMode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
