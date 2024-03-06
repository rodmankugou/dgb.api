package com.verificer.exchange.web.vo;



import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * CustomerVo
 */
public class UserInforVo implements Serializable {
	private Long id;

	@ApiModelProperty("实名认证状态： 0-未实名 1-审核中 2-已实名 3-审核驳回")
	private Integer realNameState;

	@ApiModelProperty("kyc拒绝原因")
	private String kycRejectReason;


	@ApiModelProperty("是否设置交易密码： 0-未设置 1-已设置")
	private Integer transPassState;


	@ApiModelProperty("手机是否绑定")
	private Boolean mobileAuth;


	@ApiModelProperty("邮箱是否绑定")
	private Boolean mailAuth;

	@ApiModelProperty("谷歌验证器是否绑定")
	private Boolean googleSecretAuth;


	@ApiModelProperty("登录谷歌验证是否开启")
	private Boolean googleAuthIsOpen;

	@ApiModelProperty("提币谷歌验证码是否开启")
	private Boolean withdrawGoogleAuthIsOpen;

	@ApiModelProperty("手机号")
	private String mobile;

	@ApiModelProperty("邮箱号")
	private String email;

	@ApiModelProperty("是否允许充币")
	private boolean allowedRecharge = false;

	@ApiModelProperty("是否允许提币")
	private boolean allowedWithdraw = false;

	@ApiModelProperty("上次登陆的ip")
	private String lastLoginIp;

	@ApiModelProperty("上次登陆时间")
	private Long lastLoginTime;

	private String avatar;

	private String realName;

	private String nickName;

	private String remark;

	private Map<String,String> links;

	private String walletAddress;

	private Long regTime;



	public Integer getRealNameState() {
		return realNameState;
	}

	public void setRealNameState(Integer realNameState) {
		this.realNameState = realNameState;
	}

	public Integer getTransPassState() {
		return transPassState;
	}

	public void setTransPassState(Integer transPassState) {
		this.transPassState = transPassState;
	}

	public Boolean getMobileAuth() {
		return mobileAuth;
	}

	public void setMobileAuth(Boolean mobileAuth) {
		this.mobileAuth = mobileAuth;
	}

	public Boolean getMailAuth() {
		return mailAuth;
	}

	public void setMailAuth(Boolean mailAuth) {
		this.mailAuth = mailAuth;
	}

	public Boolean getGoogleSecretAuth() {
		return googleSecretAuth;
	}

	public void setGoogleSecretAuth(Boolean googleSecretAuth) {
		this.googleSecretAuth = googleSecretAuth;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isAllowedRecharge() {
		return allowedRecharge;
	}

	public void setAllowedRecharge(boolean allowedRecharge) {
		this.allowedRecharge = allowedRecharge;
	}

	public boolean isAllowedWithdraw() {
		return allowedWithdraw;
	}

	public void setAllowedWithdraw(boolean allowedWithdraw) {
		this.allowedWithdraw = allowedWithdraw;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Map<String, String> getLinks() {
		return links;
	}

	public void setLinks(Map<String, String> links) {
		this.links = links;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKycRejectReason() {
		return kycRejectReason;
	}

	public void setKycRejectReason(String kycRejectReason) {
		this.kycRejectReason = kycRejectReason;
	}

	public Long getRegTime() {
		return regTime;
	}

	public void setRegTime(Long regTime) {
		this.regTime = regTime;
	}
}
