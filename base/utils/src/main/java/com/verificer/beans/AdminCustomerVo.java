package com.verificer.beans;

import com.verificer.enums.IdInfoStatus;
import com.verificer.enums.RegisterType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 35336 on 2021/2/24.
 */
@ApiModel
public class AdminCustomerVo implements Serializable {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    private Long roleId;

    private String roleName;

    private String username;

    private String nickname;

    private String crmAccount;

    private Boolean isDepartmentPrincipal;

    //所属组织架构的id
    private Long orgId;

    private String orgName;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
     * 渠道号
     */
    @ApiModelProperty("渠道号")
    private String channelId;

    /**
     * 手机是否认证
     */
    @ApiModelProperty("手机是否认证")
    private Boolean mobileAuth;

    /**
     * 邮箱地址
     */
    @ApiModelProperty("邮箱地址")
    private String email;

    /**
     * 邮箱是否认证
     */
    @ApiModelProperty("邮箱是否认证")
    private Boolean mailAuth;

    /**
     * 是否设置交易密码
     */
    @ApiModelProperty("是否设置交易密码")
    private Boolean hasPayPassword;
    /**
     * 是否设置交易密码
     */
    @ApiModelProperty("是否已取消KYC认证")
    private Boolean submissionStoped;

    /**
     * 用户最近登录时间
     */
    @ApiModelProperty("用户最近登录时间")
    private Date lastLoginTime;

    /**
     * 用户最近登录IP
     */
    @ApiModelProperty("用户最近登录IP")
    private String lastLoginIP;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private Date regTime;

    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用, true or false")
    private Boolean enable;

    /**
     * 邀请码
     */
    @ApiModelProperty("邀请码")
    private String inviteCode;

    /**
     * 邀请链接
     */
    @ApiModelProperty("邀请链接")
    private String inviteLink;

    /**
     * 邀请人ID
     */
    @ApiModelProperty("邀请人ID")
    private Integer inviter;


    /**
     * 是否启用googleSecret
     */
    @ApiModelProperty("是否启用googleSecret")
    private Boolean googleSecretAuth;

    @ApiModelProperty("用户真实姓名-名字")
    private String firstName;

    @ApiModelProperty("用户真实姓名-姓氏")
    private String lastName;
    /**
     * 用户证件号码
     */
    @ApiModelProperty("用户证件号码")
    private String idCardNo;
    /**
     * 国籍Id 关联nation表
     */
    @ApiModelProperty("国籍Id 关联nation表")
    private Long nationalId;
    /**
     * 是否身份认证成功
     */
    @ApiModelProperty("是否身份认证成功")
    private IdInfoStatus idInfoStatus;

    /**是否身份认证成功 String
     *
     */
    @ApiModelProperty("是否身份认证成功")
    private String idInfoStatusString;

    /**
     * 注册类型
     */
    @ApiModelProperty("注册类型")
    private RegisterType regType;

    /**
     * 是否允许用平台币抵扣
     */
    @ApiModelProperty("是否允许用平台币抵扣")
    private Boolean deduct;



    /**
     * 是否开启登录谷歌认证
     */
    @ApiModelProperty("是否开启登录谷歌认证")
    private Boolean googleAuthIsOpen;
    /**
     * 是否开启提币谷歌认证
     */
    @ApiModelProperty("是否开启提币谷歌认证")
    private Boolean withdrawGoogleAuthIsOpen;

    @ApiModelProperty("国家/地区")
    private String nationalName;

    @ApiModelProperty("证件首页照片")
    private String idCardFrontPhoto;

    @ApiModelProperty("证件声明页照片")
    private String idCardBackPhoto;

    @ApiModelProperty("手持证件照")
    private String idCardRealPhoto;

    @ApiModelProperty("资产")
    private String totalAmount;

    @ApiModelProperty("实名认证信息提交时间")
    private Long verifiedInfoSubmitTime;

    @ApiModelProperty("实名认证审核备注")
    private String realVerifiedRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Boolean getMobileAuth() {
        return mobileAuth;
    }

    public void setMobileAuth(Boolean mobileAuth) {
        this.mobileAuth = mobileAuth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getMailAuth() {
        return mailAuth;
    }

    public void setMailAuth(Boolean mailAuth) {
        this.mailAuth = mailAuth;
    }

    public Boolean getHasPayPassword() {
        return hasPayPassword;
    }

    public void setHasPayPassword(Boolean hasPayPassword) {
        this.hasPayPassword = hasPayPassword;
    }

    public Boolean getSubmissionStoped() {
        return submissionStoped;
    }

    public void setSubmissionStoped(Boolean submissionStoped) {
        this.submissionStoped = submissionStoped;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

    public Integer getInviter() {
        return inviter;
    }

    public void setInviter(Integer inviter) {
        this.inviter = inviter;
    }

    public Boolean getGoogleSecretAuth() {
        return googleSecretAuth;
    }

    public void setGoogleSecretAuth(Boolean googleSecretAuth) {
        this.googleSecretAuth = googleSecretAuth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public IdInfoStatus getIdInfoStatus() {
        return idInfoStatus;
    }

    public void setIdInfoStatus(IdInfoStatus idInfoStatus) {
        this.idInfoStatus = idInfoStatus;
    }

    public String getIdInfoStatusString() {
        return idInfoStatusString;
    }

    public void setIdInfoStatusString(String idInfoStatusString) {
        this.idInfoStatusString = idInfoStatusString;
    }

    public RegisterType getRegType() {
        return regType;
    }

    public void setRegType(RegisterType regType) {
        this.regType = regType;
    }

    public Boolean getDeduct() {
        return deduct;
    }

    public void setDeduct(Boolean deduct) {
        this.deduct = deduct;
    }

    public Boolean getGoogleAuthIsOpen() {
        return googleAuthIsOpen;
    }

    public void setGoogleAuthIsOpen(Boolean googleAuthIsOpen) {
        this.googleAuthIsOpen = googleAuthIsOpen;
    }

    public Boolean getWithdrawGoogleAuthIsOpen() {
        return withdrawGoogleAuthIsOpen;
    }

    public void setWithdrawGoogleAuthIsOpen(Boolean withdrawGoogleAuthIsOpen) {
        this.withdrawGoogleAuthIsOpen = withdrawGoogleAuthIsOpen;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public String getIdCardFrontPhoto() {
        return idCardFrontPhoto;
    }

    public void setIdCardFrontPhoto(String idCardFrontPhoto) {
        this.idCardFrontPhoto = idCardFrontPhoto;
    }

    public String getIdCardBackPhoto() {
        return idCardBackPhoto;
    }

    public void setIdCardBackPhoto(String idCardBackPhoto) {
        this.idCardBackPhoto = idCardBackPhoto;
    }

    public String getIdCardRealPhoto() {
        return idCardRealPhoto;
    }

    public void setIdCardRealPhoto(String idCardRealPhoto) {
        this.idCardRealPhoto = idCardRealPhoto;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getVerifiedInfoSubmitTime() {
        return verifiedInfoSubmitTime;
    }

    public void setVerifiedInfoSubmitTime(Long verifiedInfoSubmitTime) {
        this.verifiedInfoSubmitTime = verifiedInfoSubmitTime;
    }

    public String getRealVerifiedRemark() {
        return realVerifiedRemark;
    }

    public void setRealVerifiedRemark(String realVerifiedRemark) {
        this.realVerifiedRemark = realVerifiedRemark;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getCrmAccount() {
        return crmAccount;
    }

    public void setCrmAccount(String crmAccount) {
        this.crmAccount = crmAccount;
    }

    public Boolean getDepartmentPrincipal() {
        return isDepartmentPrincipal;
    }

    public void setDepartmentPrincipal(Boolean departmentPrincipal) {
        isDepartmentPrincipal = departmentPrincipal;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
