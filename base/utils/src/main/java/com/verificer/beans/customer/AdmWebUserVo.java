package com.verificer.beans.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verificer.enums.CfdNodeRole;
import com.verificer.enums.IdInfoStatus;
import com.verificer.enums.RegisterType;
import com.verificer.enums.VolunteerStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class AdmWebUserVo implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("所属公司的名称")
    private String belongCompanyName;

    @ApiModelProperty("角色")
    private Integer role;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否已激活")
    private Boolean isActivation;

    @ApiModelProperty("注册时间")
    private Long regTime;

    @ApiModelProperty("用户最后登录时间")
    private Long lastLoginTime;

    @ApiModelProperty("是否启用")
    private Boolean enable;

    @ApiModelProperty("是否已经激活")
    private Boolean hasActived;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivation() {
        return isActivation;
    }

    public void setActivation(Boolean activation) {
        isActivation = activation;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getHasActived() {
        return hasActived;
    }

    public void setHasActived(Boolean hasActived) {
        this.hasActived = hasActived;
    }

    public String getBelongCompanyName() {
        return belongCompanyName;
    }

    public void setBelongCompanyName(String belongCompanyName) {
        this.belongCompanyName = belongCompanyName;
    }
}
