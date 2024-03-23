package com.verificer.biz.beans.vo.user.member;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("UID")
    private String uid;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("总订单数，退款的不含在内")
    private Integer totalOrderCount;

    @ApiModelProperty("总订单金额，退款的不含在内")
    @PriceDecimal
    private BigDecimal totalOrderAmount;

    @ApiModelProperty("总积分")
    @PriceDecimal
    private BigDecimal totalIntegral;

    @ApiModelProperty("绑定门店")
    private String memberShopName;

    @ApiModelProperty("注册时间")
    private Long regTime;


    @ApiModelProperty("会员有效时间-开始时间")
    private Long memberSTime;

    @ApiModelProperty("会员有效时间-结束时间")
    private Long memberETime;


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

    public BigDecimal getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(BigDecimal totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getMemberShopName() {
        return memberShopName;
    }

    public void setMemberShopName(String memberShopName) {
        this.memberShopName = memberShopName;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    public Long getMemberSTime() {
        return memberSTime;
    }

    public void setMemberSTime(Long memberSTime) {
        this.memberSTime = memberSTime;
    }

    public Long getMemberETime() {
        return memberETime;
    }

    public void setMemberETime(Long memberETime) {
        this.memberETime = memberETime;
    }
}
