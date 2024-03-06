package com.verificer.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;


import com.verificer.enums.CfdNodeRole;
import com.verificer.enums.IdInfoStatus;
import com.verificer.enums.RegisterType;
import com.verificer.enums.VolunteerStatus;
import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

/**
 * CustomerVo
 */
public class CustomerVo implements Serializable {

	/**
	 * id
	 */
	private Long id;


	private Long roleId;

	private String roleName;

	private String username;

	private String crmAccount;

	private String nickname;

	private Integer userType;

	/**
	 * id
	 */
	private String hpayAccountId;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 渠道号
	 */
	private String channelId;

	/**
	 * 手机是否认证
	 */
	@ApiModelProperty("手机是否认证")
	private Boolean mobileAuth;

	/**
	 * 邮箱地址
	 */
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
	private Long lastLoginTime;

	/**
	 * 用户最近登录IP
	 */
	@ApiModelProperty("用户最近登录IP")
	private String lastLoginIP;

	/**
	 * 注册时间
	 */
	@ApiModelProperty("注册时间")
	private Long regTime;

	/**
	 * 是否启用
	 */
	private Boolean enable;

	/**
	 * 是否是白名单
	 */
	private Boolean whiteList;

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
	private Integer inviter;


	/**
	 * 是否启用googleSecret
	 */
	@ApiModelProperty("是否启用googleSecret")
	private Boolean googleSecretAuth;
	/**
	 * 用户真实姓名
	 */
	@ApiModelProperty("用户真实姓名")
	private String realName;
	/**
	 * 用户证件号码
	 */
	@ApiModelProperty("用户证件号码")
	private String idCardNo;
	/**
	 * 国籍Id 关联nation表
	 */
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

	@JsonIgnore
	private String googleSecret;

	/**
	 * 密码
	 */
	//jackson 不序列化
	@JsonIgnore
	private transient String password;

	/**
	 * 交易密码
	 */
	//jackson 不序列化
	@ApiModelProperty("交易密码")
	@JsonIgnore
	private transient String payPassword;

	/**
	 * 是否项目方
	 */
	private Boolean isProject;

	/**
	 * 用户登录token
	 */
	private String token;

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

	private Boolean hasC2CTrade;

	/**
	 * 是否是合伙人
	 */
	private Boolean isPartner;

    /**
     * 义工状态
     */
    private VolunteerStatus volunteerStatus;

	/**
	 * 义工状态 String
	 */
	private String volunteerStatusString;

	/**
	 * 义工号
	 */
	private String volunteerNumber;

	/**
	 * 返佣比例
	 */
	private Double volunteerRate;

    /**
     * 社区ID
     */
    private Integer communityId;

	/**
	 * 存放邀请关系
	 */
	private String relation;

	/**
	 * 节点信息
	 */
	private Integer nodeId;

	/**
	 * 合约返佣
	 */
	private Double cfdRate;



	private String nodeName;

	private String avatar;


	private String nickName;

	private String remark;

	private String links;

	private String walletAddress;





	/**
	 * 用户在节点中的角色
	 * 0：最高级管理员，每个节点只有一个
	 * 1：员工级
	 * 2：普通用户
	 */
	private CfdNodeRole nodeRole;

	/**
	 * 手势密码是否开启
	 */
	private Boolean gestureEnable;

	@ApiModelProperty("是否已经激活")
	private Boolean isActivation;


	public String getCrmAccount() {
		return crmAccount;
	}

	public void setCrmAccount(String crmAccount) {
		this.crmAccount = crmAccount;
	}

	public Boolean getWithdrawGoogleAuthIsOpen() {
		return withdrawGoogleAuthIsOpen;
	}

	public Boolean getGestureEnable() {
		return gestureEnable;
	}

	public void setGestureEnable(Boolean gestureEnable) {
		this.gestureEnable = gestureEnable;
	}

	public void setWithdrawGoogleAuthIsOpen(Boolean withdrawGoogleAuthIsOpen) {
		this.withdrawGoogleAuthIsOpen = withdrawGoogleAuthIsOpen;
	}

	public CfdNodeRole getNodeRole() {
		return nodeRole;
	}

	public void setNodeRole(CfdNodeRole nodeRole) {
		this.nodeRole = nodeRole;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Double getCfdRate() {
		return cfdRate;
	}

	public void setCfdRate(Double cfdRate) {
		this.cfdRate = cfdRate;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getProject() {
		return isProject;
	}

	public void setProject(Boolean project) {
		isProject = project;
	}


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

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public Long getRegTime() {
		return regTime;
	}

	public void setRegTime(Long regTime) {
		this.regTime = regTime;
	}

	public Boolean getActivation() {
		return isActivation;
	}

	public void setActivation(Boolean activation) {
		isActivation = activation;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(Boolean whiteList) {
		this.whiteList = whiteList;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Boolean getGoogleSecretAuth() {
		return googleSecretAuth;
	}

	public void setGoogleSecretAuth(Boolean googleSecretAuth) {
		this.googleSecretAuth = googleSecretAuth;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public RegisterType getRegType() {
		return regType;
	}

	public void setRegType(RegisterType regType) {
		this.regType = regType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getGoogleSecret() {
		return googleSecret;
	}

	public void setGoogleSecret(String googleSecret) {
		this.googleSecret = googleSecret;
	}

	public Boolean getDeduct() {
		return deduct;
	}

	public void setDeduct(Boolean deduct) {
		this.deduct = deduct;
	}

	public IdInfoStatus getIdInfoStatus() {
		return idInfoStatus;
	}

	public void setIdInfoStatus(IdInfoStatus idInfoStatus) {
		this.idInfoStatus = idInfoStatus;
	}

	public Boolean getHasC2CTrade() {
		return hasC2CTrade;
	}

	public void setHasC2CTrade(Boolean hasC2CTrade) {
		this.hasC2CTrade = hasC2CTrade;
	}

	public String getInviteLink() {
		return inviteLink;
	}

	public void setInviteLink(String inviteLink) {
		this.inviteLink = inviteLink;
	}

	public Boolean getPartner() {
		return isPartner;
	}

	public void setPartner(Boolean partner) {
		isPartner = partner;
	}

    public VolunteerStatus getVolunteerStatus() {
        return volunteerStatus;
    }

    public void setVolunteerStatus(VolunteerStatus volunteerStatus) {
        this.volunteerStatus = volunteerStatus;
    }

    public String getVolunteerNumber() {
		return volunteerNumber;
	}

	public void setVolunteerNumber(String volunteerNumber) {
		this.volunteerNumber = volunteerNumber;
	}

	public Double getVolunteerRate() {
		return volunteerRate;
	}

	public void setVolunteerRate(Double volunteerRate) {
		this.volunteerRate = volunteerRate;
	}

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getInviter() {
        return inviter;
    }

    public void setInviter(Integer inviter) {
        this.inviter = inviter;
    }

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Boolean getGoogleAuthIsOpen() {
		return googleAuthIsOpen;
	}

	public void setGoogleAuthIsOpen(Boolean googleAuthIsOpen) {
		this.googleAuthIsOpen = googleAuthIsOpen;
	}

	public String getIdInfoStatusString() {
		return idInfoStatusString;
	}

	public void setIdInfoStatusString(String idInfoStatusString) {
		this.idInfoStatusString = idInfoStatusString;
	}

	public String getVolunteerStatusString() {
		return volunteerStatusString;
	}

	public void setVolunteerStatusString(String volunteerStatusString) {
		this.volunteerStatusString = volunteerStatusString;
	}

	public String getHpayAccountId() {
		return hpayAccountId;
	}

	public void setHpayAccountId(String hpayAccountId) {
		this.hpayAccountId = hpayAccountId;
	}

	public Boolean getSubmissionStoped() {
		return submissionStoped;
	}

	public void setSubmissionStoped(Boolean submissionStoped) {
		this.submissionStoped = submissionStoped;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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


	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Boolean getIsActivation() {
		return isActivation;
	}

	public void setIsActivation(Boolean activation) {
		isActivation = activation;
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

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
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
}
