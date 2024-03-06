package com.verificer.beans;

import java.io.Serializable;

/**
 * Created by 35336 on 2020/12/26.
 */
public class LogoutReqVo implements Serializable {
    private Long userId;
    private String token;

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
}
