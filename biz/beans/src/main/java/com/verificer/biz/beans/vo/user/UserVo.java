package com.verificer.biz.beans.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class UserVo implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("UID")
    private String uid;

    @ApiModelProperty("昵称")
    private String nickname;


    @ApiModelProperty("是否会员 true-是 false-否")
    private Boolean memberFlag;

    @ApiModelProperty("会员有效时间-开始时间")
    private Long memberSTime;

    @ApiModelProperty("会员昵称")
    private String memberNickname;

    @ApiModelProperty("会员有效时间-结束时间")
    private Long memberETime;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("注册时间")
    private Long regTime;

    @ApiModelProperty("是否曾为引荐人 true-是 false-否")
    private Boolean referrerFlag;

    @ApiModelProperty("是否为引荐人 true-是 false-否")
    private Boolean referrerEnableFlag;


    @ApiModelProperty("引荐佣金")
    private BigDecimal inviteCommission;

    @ApiModelProperty("引荐人数")
    private Integer totalInviteCount;


    @ApiModelProperty("总佣金")
    private BigDecimal totalInviteCommission;

    @ApiModelProperty("总订单数，退款的不含在内")
    private Integer totalOrderCount;

    @ApiModelProperty("总订单金额，退款的不含在内")
    private BigDecimal totalOrderAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(Boolean memberFlag) {
        this.memberFlag = memberFlag;
    }

    public Long getMemberSTime() {
        return memberSTime;
    }

    public void setMemberSTime(Long memberSTime) {
        this.memberSTime = memberSTime;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public Long getMemberETime() {
        return memberETime;
    }

    public void setMemberETime(Long memberETime) {
        this.memberETime = memberETime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    public Boolean getReferrerFlag() {
        return referrerFlag;
    }

    public void setReferrerFlag(Boolean referrerFlag) {
        this.referrerFlag = referrerFlag;
    }

    public Boolean getReferrerEnableFlag() {
        return referrerEnableFlag;
    }

    public void setReferrerEnableFlag(Boolean referrerEnableFlag) {
        this.referrerEnableFlag = referrerEnableFlag;
    }

    public BigDecimal getInviteCommission() {
        return inviteCommission;
    }

    public void setInviteCommission(BigDecimal inviteCommission) {
        this.inviteCommission = inviteCommission;
    }

    public Integer getTotalInviteCount() {
        return totalInviteCount;
    }

    public void setTotalInviteCount(Integer totalInviteCount) {
        this.totalInviteCount = totalInviteCount;
    }

    public BigDecimal getTotalInviteCommission() {
        return totalInviteCommission;
    }

    public void setTotalInviteCommission(BigDecimal totalInviteCommission) {
        this.totalInviteCommission = totalInviteCommission;
    }

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
}
