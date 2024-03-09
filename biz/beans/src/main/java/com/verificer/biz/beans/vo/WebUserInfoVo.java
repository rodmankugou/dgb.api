package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class WebUserInfoVo implements Serializable {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("角色 1-发展商 2-建筑商 3-供应商")
    private Integer role;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("认证状态 1-未提交审核 2-审核中 3-审核通过 4-审核不通过")
    private Integer certStatus;
    @ApiModelProperty("拒绝原因")
    private String rejectReason;
    @ApiModelProperty("是否已设计PIN")
    private Boolean hasSetPIN;
    @ApiModelProperty("是否已经激活")
    private Boolean isActive;
    @ApiModelProperty("是否已经设置银行卡")
    private Boolean hasSetupBankCard;
    @ApiModelProperty("是否已经设置aboutUs")
    private Boolean hasSetupAboutUs;
    @ApiModelProperty("所属企业的企业名称")
    private String belongCompanyName;

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

    public Integer getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(Integer certStatus) {
        this.certStatus = certStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Boolean getHasSetPIN() {
        return hasSetPIN;
    }

    public void setHasSetPIN(Boolean hasSetPIN) {
        this.hasSetPIN = hasSetPIN;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getHasSetupBankCard() {
        return hasSetupBankCard;
    }

    public void setHasSetupBankCard(Boolean hasSetupBankCard) {
        this.hasSetupBankCard = hasSetupBankCard;
    }

    public Boolean getHasSetupAboutUs() {
        return hasSetupAboutUs;
    }

    public void setHasSetupAboutUs(Boolean hasSetupAboutUs) {
        this.hasSetupAboutUs = hasSetupAboutUs;
    }

    public String getBelongCompanyName() {
        return belongCompanyName;
    }

    public void setBelongCompanyName(String belongCompanyName) {
        this.belongCompanyName = belongCompanyName;
    }
}
