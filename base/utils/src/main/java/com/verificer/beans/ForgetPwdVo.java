package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 21.
 */
@ApiModel
public class ForgetPwdVo implements Serializable{
	private static final long serialVersionUID = -8008692576251970623L;

	@ApiModelProperty(value = "手机号码或邮箱",required = true)
	private String account;

	@ApiModelProperty(value = "密码",required = true)
	private String password;

	@ApiModelProperty(value = "确认密码",required = true)
	private String rePassword;

	@ApiModelProperty(value = "手机或邮箱验证码",required = true)
	private String code;

	@ApiModelProperty(value = "图片Id",required = true)
	private String imageCode;

	@ApiModelProperty(value = "图片验证码",required = true)
	private String imageId;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
