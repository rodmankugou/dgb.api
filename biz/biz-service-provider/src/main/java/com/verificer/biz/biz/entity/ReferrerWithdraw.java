package com.verificer.biz.biz.entity;

import java.math.BigDecimal;

public class ReferrerWithdraw {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.ord_num
     *
     * @mbg.generated
     */
    private String ordNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.amount
     *
     * @mbg.generated
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.review_time
     *
     * @mbg.generated
     */
    private Long reviewTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.review_staff_id
     *
     * @mbg.generated
     */
    private Long reviewStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.review_staff_name
     *
     * @mbg.generated
     */
    private String reviewStaffName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.certificate_img
     *
     * @mbg.generated
     */
    private String certificateImg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.transfer_staff_id
     *
     * @mbg.generated
     */
    private Long transferStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.transfer_staff_name
     *
     * @mbg.generated
     */
    private String transferStaffName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column referrer_withdraw.transfer_time
     *
     * @mbg.generated
     */
    private Long transferTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.id
     *
     * @return the value of referrer_withdraw.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.id
     *
     * @param id the value for referrer_withdraw.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.ord_num
     *
     * @return the value of referrer_withdraw.ord_num
     *
     * @mbg.generated
     */
    public String getOrdNum() {
        return ordNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.ord_num
     *
     * @param ordNum the value for referrer_withdraw.ord_num
     *
     * @mbg.generated
     */
    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum == null ? null : ordNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.user_id
     *
     * @return the value of referrer_withdraw.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.user_id
     *
     * @param userId the value for referrer_withdraw.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.amount
     *
     * @return the value of referrer_withdraw.amount
     *
     * @mbg.generated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.amount
     *
     * @param amount the value for referrer_withdraw.amount
     *
     * @mbg.generated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.status
     *
     * @return the value of referrer_withdraw.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.status
     *
     * @param status the value for referrer_withdraw.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.remark
     *
     * @return the value of referrer_withdraw.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.remark
     *
     * @param remark the value for referrer_withdraw.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.create_time
     *
     * @return the value of referrer_withdraw.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.create_time
     *
     * @param createTime the value for referrer_withdraw.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.review_time
     *
     * @return the value of referrer_withdraw.review_time
     *
     * @mbg.generated
     */
    public Long getReviewTime() {
        return reviewTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.review_time
     *
     * @param reviewTime the value for referrer_withdraw.review_time
     *
     * @mbg.generated
     */
    public void setReviewTime(Long reviewTime) {
        this.reviewTime = reviewTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.review_staff_id
     *
     * @return the value of referrer_withdraw.review_staff_id
     *
     * @mbg.generated
     */
    public Long getReviewStaffId() {
        return reviewStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.review_staff_id
     *
     * @param reviewStaffId the value for referrer_withdraw.review_staff_id
     *
     * @mbg.generated
     */
    public void setReviewStaffId(Long reviewStaffId) {
        this.reviewStaffId = reviewStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.review_staff_name
     *
     * @return the value of referrer_withdraw.review_staff_name
     *
     * @mbg.generated
     */
    public String getReviewStaffName() {
        return reviewStaffName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.review_staff_name
     *
     * @param reviewStaffName the value for referrer_withdraw.review_staff_name
     *
     * @mbg.generated
     */
    public void setReviewStaffName(String reviewStaffName) {
        this.reviewStaffName = reviewStaffName == null ? null : reviewStaffName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.certificate_img
     *
     * @return the value of referrer_withdraw.certificate_img
     *
     * @mbg.generated
     */
    public String getCertificateImg() {
        return certificateImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.certificate_img
     *
     * @param certificateImg the value for referrer_withdraw.certificate_img
     *
     * @mbg.generated
     */
    public void setCertificateImg(String certificateImg) {
        this.certificateImg = certificateImg == null ? null : certificateImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.transfer_staff_id
     *
     * @return the value of referrer_withdraw.transfer_staff_id
     *
     * @mbg.generated
     */
    public Long getTransferStaffId() {
        return transferStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.transfer_staff_id
     *
     * @param transferStaffId the value for referrer_withdraw.transfer_staff_id
     *
     * @mbg.generated
     */
    public void setTransferStaffId(Long transferStaffId) {
        this.transferStaffId = transferStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.transfer_staff_name
     *
     * @return the value of referrer_withdraw.transfer_staff_name
     *
     * @mbg.generated
     */
    public String getTransferStaffName() {
        return transferStaffName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.transfer_staff_name
     *
     * @param transferStaffName the value for referrer_withdraw.transfer_staff_name
     *
     * @mbg.generated
     */
    public void setTransferStaffName(String transferStaffName) {
        this.transferStaffName = transferStaffName == null ? null : transferStaffName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column referrer_withdraw.transfer_time
     *
     * @return the value of referrer_withdraw.transfer_time
     *
     * @mbg.generated
     */
    public Long getTransferTime() {
        return transferTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column referrer_withdraw.transfer_time
     *
     * @param transferTime the value for referrer_withdraw.transfer_time
     *
     * @mbg.generated
     */
    public void setTransferTime(Long transferTime) {
        this.transferTime = transferTime;
    }
}