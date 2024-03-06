package com.verificer.exchange.admin.vo;

import io.swagger.annotations.ApiModelProperty;

public class ApiLoginRespVo {
    @ApiModelProperty("用户token")
    private String token;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("是否已激活")
    private Boolean active;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
