package com.verificer.account.provider.entity;

import java.math.BigDecimal;

public class SysAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.available_amount
     *
     * @mbg.generated
     */
    private BigDecimal availableAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.sub_name
     *
     * @mbg.generated
     */
    private String subName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.frozen_amount
     *
     * @mbg.generated
     */
    private BigDecimal frozenAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_account.update_time
     *
     * @mbg.generated
     */
    private Long updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.id
     *
     * @return the value of sys_account.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.id
     *
     * @param id the value for sys_account.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.available_amount
     *
     * @return the value of sys_account.available_amount
     *
     * @mbg.generated
     */
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.available_amount
     *
     * @param availableAmount the value for sys_account.available_amount
     *
     * @mbg.generated
     */
    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.create_time
     *
     * @return the value of sys_account.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.create_time
     *
     * @param createTime the value for sys_account.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.sub_name
     *
     * @return the value of sys_account.sub_name
     *
     * @mbg.generated
     */
    public String getSubName() {
        return subName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.sub_name
     *
     * @param subName the value for sys_account.sub_name
     *
     * @mbg.generated
     */
    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.frozen_amount
     *
     * @return the value of sys_account.frozen_amount
     *
     * @mbg.generated
     */
    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.frozen_amount
     *
     * @param frozenAmount the value for sys_account.frozen_amount
     *
     * @mbg.generated
     */
    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_account.update_time
     *
     * @return the value of sys_account.update_time
     *
     * @mbg.generated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_account.update_time
     *
     * @param updateTime the value for sys_account.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
