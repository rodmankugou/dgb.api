package com.verificer.exchange.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 35336 on 2021/1/9.
 */
@ApiModel
public class ApiLoginRespVo {
    @ApiModelProperty("用户token")
    private String token;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("角色，1-发展商 2-建筑商 3-供应商")
    private Long roleId;
    @ApiModelProperty("是否已经激活 true-是 false-否")
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
