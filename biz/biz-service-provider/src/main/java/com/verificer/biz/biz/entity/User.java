package com.verificer.biz.biz.entity;

import java.math.BigDecimal;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.uid
     *
     * @mbg.generated
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.wx_open_id
     *
     * @mbg.generated
     */
    private String wxOpenId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.nickname
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_flag
     *
     * @mbg.generated
     */
    private Boolean memberFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_s_time
     *
     * @mbg.generated
     */
    private Long memberSTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_e_time
     *
     * @mbg.generated
     */
    private Long memberETime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_ip
     *
     * @mbg.generated
     */
    private String memberIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_province_code
     *
     * @mbg.generated
     */
    private String memberProvinceCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_province_name
     *
     * @mbg.generated
     */
    private String memberProvinceName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_ref_type
     *
     * @mbg.generated
     */
    private Integer memberRefType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_ref_id
     *
     * @mbg.generated
     */
    private String memberRefId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.member_shop_name
     *
     * @mbg.generated
     */
    private String memberShopName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.avatar
     *
     * @mbg.generated
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.reg_time
     *
     * @mbg.generated
     */
    private Long regTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.referrer_flag
     *
     * @mbg.generated
     */
    private Boolean referrerFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.referrer_enable_flag
     *
     * @mbg.generated
     */
    private Boolean referrerEnableFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.pos_member_id
     *
     * @mbg.generated
     */
    private Long posMemberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.invite_commission
     *
     * @mbg.generated
     */
    private BigDecimal inviteCommission;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.total_invite_count
     *
     * @mbg.generated
     */
    private Integer totalInviteCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.total_invite_commission
     *
     * @mbg.generated
     */
    private BigDecimal totalInviteCommission;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.total_order_count
     *
     * @mbg.generated
     */
    private Integer totalOrderCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.total_order_amount
     *
     * @mbg.generated
     */
    private BigDecimal totalOrderAmount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.uid
     *
     * @return the value of user.uid
     *
     * @mbg.generated
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.uid
     *
     * @param uid the value for user.uid
     *
     * @mbg.generated
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.wx_open_id
     *
     * @return the value of user.wx_open_id
     *
     * @mbg.generated
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.wx_open_id
     *
     * @param wxOpenId the value for user.wx_open_id
     *
     * @mbg.generated
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId == null ? null : wxOpenId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.nickname
     *
     * @return the value of user.nickname
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.nickname
     *
     * @param nickname the value for user.nickname
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_flag
     *
     * @return the value of user.member_flag
     *
     * @mbg.generated
     */
    public Boolean getMemberFlag() {
        return memberFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_flag
     *
     * @param memberFlag the value for user.member_flag
     *
     * @mbg.generated
     */
    public void setMemberFlag(Boolean memberFlag) {
        this.memberFlag = memberFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_s_time
     *
     * @return the value of user.member_s_time
     *
     * @mbg.generated
     */
    public Long getMemberSTime() {
        return memberSTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_s_time
     *
     * @param memberSTime the value for user.member_s_time
     *
     * @mbg.generated
     */
    public void setMemberSTime(Long memberSTime) {
        this.memberSTime = memberSTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_e_time
     *
     * @return the value of user.member_e_time
     *
     * @mbg.generated
     */
    public Long getMemberETime() {
        return memberETime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_e_time
     *
     * @param memberETime the value for user.member_e_time
     *
     * @mbg.generated
     */
    public void setMemberETime(Long memberETime) {
        this.memberETime = memberETime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_ip
     *
     * @return the value of user.member_ip
     *
     * @mbg.generated
     */
    public String getMemberIp() {
        return memberIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_ip
     *
     * @param memberIp the value for user.member_ip
     *
     * @mbg.generated
     */
    public void setMemberIp(String memberIp) {
        this.memberIp = memberIp == null ? null : memberIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_province_code
     *
     * @return the value of user.member_province_code
     *
     * @mbg.generated
     */
    public String getMemberProvinceCode() {
        return memberProvinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_province_code
     *
     * @param memberProvinceCode the value for user.member_province_code
     *
     * @mbg.generated
     */
    public void setMemberProvinceCode(String memberProvinceCode) {
        this.memberProvinceCode = memberProvinceCode == null ? null : memberProvinceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_province_name
     *
     * @return the value of user.member_province_name
     *
     * @mbg.generated
     */
    public String getMemberProvinceName() {
        return memberProvinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_province_name
     *
     * @param memberProvinceName the value for user.member_province_name
     *
     * @mbg.generated
     */
    public void setMemberProvinceName(String memberProvinceName) {
        this.memberProvinceName = memberProvinceName == null ? null : memberProvinceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_ref_type
     *
     * @return the value of user.member_ref_type
     *
     * @mbg.generated
     */
    public Integer getMemberRefType() {
        return memberRefType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_ref_type
     *
     * @param memberRefType the value for user.member_ref_type
     *
     * @mbg.generated
     */
    public void setMemberRefType(Integer memberRefType) {
        this.memberRefType = memberRefType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_ref_id
     *
     * @return the value of user.member_ref_id
     *
     * @mbg.generated
     */
    public String getMemberRefId() {
        return memberRefId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_ref_id
     *
     * @param memberRefId the value for user.member_ref_id
     *
     * @mbg.generated
     */
    public void setMemberRefId(String memberRefId) {
        this.memberRefId = memberRefId == null ? null : memberRefId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.member_shop_name
     *
     * @return the value of user.member_shop_name
     *
     * @mbg.generated
     */
    public String getMemberShopName() {
        return memberShopName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.member_shop_name
     *
     * @param memberShopName the value for user.member_shop_name
     *
     * @mbg.generated
     */
    public void setMemberShopName(String memberShopName) {
        this.memberShopName = memberShopName == null ? null : memberShopName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.avatar
     *
     * @return the value of user.avatar
     *
     * @mbg.generated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.avatar
     *
     * @param avatar the value for user.avatar
     *
     * @mbg.generated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.mobile
     *
     * @return the value of user.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.mobile
     *
     * @param mobile the value for user.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.reg_time
     *
     * @return the value of user.reg_time
     *
     * @mbg.generated
     */
    public Long getRegTime() {
        return regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.reg_time
     *
     * @param regTime the value for user.reg_time
     *
     * @mbg.generated
     */
    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.referrer_flag
     *
     * @return the value of user.referrer_flag
     *
     * @mbg.generated
     */
    public Boolean getReferrerFlag() {
        return referrerFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.referrer_flag
     *
     * @param referrerFlag the value for user.referrer_flag
     *
     * @mbg.generated
     */
    public void setReferrerFlag(Boolean referrerFlag) {
        this.referrerFlag = referrerFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.referrer_enable_flag
     *
     * @return the value of user.referrer_enable_flag
     *
     * @mbg.generated
     */
    public Boolean getReferrerEnableFlag() {
        return referrerEnableFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.referrer_enable_flag
     *
     * @param referrerEnableFlag the value for user.referrer_enable_flag
     *
     * @mbg.generated
     */
    public void setReferrerEnableFlag(Boolean referrerEnableFlag) {
        this.referrerEnableFlag = referrerEnableFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.pos_member_id
     *
     * @return the value of user.pos_member_id
     *
     * @mbg.generated
     */
    public Long getPosMemberId() {
        return posMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.pos_member_id
     *
     * @param posMemberId the value for user.pos_member_id
     *
     * @mbg.generated
     */
    public void setPosMemberId(Long posMemberId) {
        this.posMemberId = posMemberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.invite_commission
     *
     * @return the value of user.invite_commission
     *
     * @mbg.generated
     */
    public BigDecimal getInviteCommission() {
        return inviteCommission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.invite_commission
     *
     * @param inviteCommission the value for user.invite_commission
     *
     * @mbg.generated
     */
    public void setInviteCommission(BigDecimal inviteCommission) {
        this.inviteCommission = inviteCommission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.total_invite_count
     *
     * @return the value of user.total_invite_count
     *
     * @mbg.generated
     */
    public Integer getTotalInviteCount() {
        return totalInviteCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.total_invite_count
     *
     * @param totalInviteCount the value for user.total_invite_count
     *
     * @mbg.generated
     */
    public void setTotalInviteCount(Integer totalInviteCount) {
        this.totalInviteCount = totalInviteCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.total_invite_commission
     *
     * @return the value of user.total_invite_commission
     *
     * @mbg.generated
     */
    public BigDecimal getTotalInviteCommission() {
        return totalInviteCommission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.total_invite_commission
     *
     * @param totalInviteCommission the value for user.total_invite_commission
     *
     * @mbg.generated
     */
    public void setTotalInviteCommission(BigDecimal totalInviteCommission) {
        this.totalInviteCommission = totalInviteCommission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.total_order_count
     *
     * @return the value of user.total_order_count
     *
     * @mbg.generated
     */
    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.total_order_count
     *
     * @param totalOrderCount the value for user.total_order_count
     *
     * @mbg.generated
     */
    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.total_order_amount
     *
     * @return the value of user.total_order_amount
     *
     * @mbg.generated
     */
    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.total_order_amount
     *
     * @param totalOrderAmount the value for user.total_order_amount
     *
     * @mbg.generated
     */
    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }
}
