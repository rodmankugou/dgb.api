package com.verificer.account.provider.entity;

import java.math.BigDecimal;

public class SysAccountLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.account_id
     *
     * @mbg.generated
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.sub_name
     *
     * @mbg.generated
     */
    private String subName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.op_type
     *
     * @mbg.generated
     */
    private Integer opType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.op_amount
     *
     * @mbg.generated
     */
    private BigDecimal opAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.attachId
     *
     * @mbg.generated
     */
    private Long attachid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.before_available
     *
     * @mbg.generated
     */
    private BigDecimal beforeAvailable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.before_frozen
     *
     * @mbg.generated
     */
    private BigDecimal beforeFrozen;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.after_available
     *
     * @mbg.generated
     */
    private BigDecimal afterAvailable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.after_frozen
     *
     * @mbg.generated
     */
    private BigDecimal afterFrozen;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account_log.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.id
     *
     * @return the value of sys_account_log.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.id
     *
     * @param id the value for sys_account_log.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.account_id
     *
     * @return the value of sys_account_log.account_id
     *
     * @mbg.generated
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.account_id
     *
     * @param accountId the value for sys_account_log.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.sub_name
     *
     * @return the value of sys_account_log.sub_name
     *
     * @mbg.generated
     */
    public String getSubName() {
        return subName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.sub_name
     *
     * @param subName the value for sys_account_log.sub_name
     *
     * @mbg.generated
     */
    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.op_type
     *
     * @return the value of sys_account_log.op_type
     *
     * @mbg.generated
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.op_type
     *
     * @param opType the value for sys_account_log.op_type
     *
     * @mbg.generated
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.op_amount
     *
     * @return the value of sys_account_log.op_amount
     *
     * @mbg.generated
     */
    public BigDecimal getOpAmount() {
        return opAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.op_amount
     *
     * @param opAmount the value for sys_account_log.op_amount
     *
     * @mbg.generated
     */
    public void setOpAmount(BigDecimal opAmount) {
        this.opAmount = opAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.attachId
     *
     * @return the value of sys_account_log.attachId
     *
     * @mbg.generated
     */
    public Long getAttachid() {
        return attachid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.attachId
     *
     * @param attachid the value for sys_account_log.attachId
     *
     * @mbg.generated
     */
    public void setAttachid(Long attachid) {
        this.attachid = attachid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.before_available
     *
     * @return the value of sys_account_log.before_available
     *
     * @mbg.generated
     */
    public BigDecimal getBeforeAvailable() {
        return beforeAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.before_available
     *
     * @param beforeAvailable the value for sys_account_log.before_available
     *
     * @mbg.generated
     */
    public void setBeforeAvailable(BigDecimal beforeAvailable) {
        this.beforeAvailable = beforeAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.before_frozen
     *
     * @return the value of sys_account_log.before_frozen
     *
     * @mbg.generated
     */
    public BigDecimal getBeforeFrozen() {
        return beforeFrozen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.before_frozen
     *
     * @param beforeFrozen the value for sys_account_log.before_frozen
     *
     * @mbg.generated
     */
    public void setBeforeFrozen(BigDecimal beforeFrozen) {
        this.beforeFrozen = beforeFrozen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.after_available
     *
     * @return the value of sys_account_log.after_available
     *
     * @mbg.generated
     */
    public BigDecimal getAfterAvailable() {
        return afterAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.after_available
     *
     * @param afterAvailable the value for sys_account_log.after_available
     *
     * @mbg.generated
     */
    public void setAfterAvailable(BigDecimal afterAvailable) {
        this.afterAvailable = afterAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.after_frozen
     *
     * @return the value of sys_account_log.after_frozen
     *
     * @mbg.generated
     */
    public BigDecimal getAfterFrozen() {
        return afterFrozen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.after_frozen
     *
     * @param afterFrozen the value for sys_account_log.after_frozen
     *
     * @mbg.generated
     */
    public void setAfterFrozen(BigDecimal afterFrozen) {
        this.afterFrozen = afterFrozen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.remark
     *
     * @return the value of sys_account_log.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.remark
     *
     * @param remark the value for sys_account_log.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account_log.create_time
     *
     * @return the value of sys_account_log.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account_log.create_time
     *
     * @param createTime the value for sys_account_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
