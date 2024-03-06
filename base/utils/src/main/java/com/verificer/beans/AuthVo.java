package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/1/30.
 */
@ApiModel
public class AuthVo implements Serializable{
    @ApiModelProperty("权限代码")
    private String code;
    @ApiModelProperty("父权限代码")
    private String parentCode;
    @ApiModelProperty("权限名称")
    private String name;
    @ApiModelProperty("描述")
    private String remark;
    /**
     * 当前角色是否拥有该权限
     */
    @ApiModelProperty("当前角色是否拥有该权限")
    private Boolean permission;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
