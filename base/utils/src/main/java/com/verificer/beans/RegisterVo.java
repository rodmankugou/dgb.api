package com.verificer.beans;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 21.
 */
@ApiModel
public class RegisterVo implements Serializable{
	private static final long serialVersionUID = -1424471723048022583L;
	/**
	 * 手机号码或邮箱
	 */
	@ApiModelProperty(value = "账号",required = true)
	private String account;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码",required = true)
	private String passwd;
	/**
	 * 确认密码
	 */
	private String rePasswd;
	/**
	 * 交易密码
	 */
	@ApiModelProperty("交易密码")
	private String payPasswd;
	/**
	 * 确认交易密码
	 */
	private String rePayPasswd;
	/**
	 * 邀请码
	 */
	@ApiModelProperty(value = "邀请码",required = false)
	private String inviteCode;
	/**
	 * 手机或邮箱验证码
	 */
	@ApiModelProperty("手机或邮箱验证码")
	private String code;

	/**
	 * 图片ID
	 */
//	@ApiModelProperty("图片验证码id,是否必要：app-否 web-是")
	private String imageId;

	/**
	 * 图片验证码
	 */
//	@ApiModelProperty("图片验证码，是否必要：app-否 web-是")
	private String imageCode;

	private String channelId;

	private String walletAddress;



	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRePasswd() {
		return rePasswd;
	}

	public void setRePasswd(String rePasswd) {
		this.rePasswd = rePasswd;
	}

	public String getPayPasswd() {
		return payPasswd;
	}

	public void setPayPasswd(String payPasswd) {
		this.payPasswd = payPasswd;
	}

	public String getRePayPasswd() {
		return rePayPasswd;
	}

	public void setRePayPasswd(String rePayPasswd) {
		this.rePayPasswd = rePayPasswd;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}
}
