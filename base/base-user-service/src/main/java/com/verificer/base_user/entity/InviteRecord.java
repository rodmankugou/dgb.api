package com.verificer.base_user.entity;

public class InviteRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invite_record.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invite_record.customer_id
     *
     * @mbg.generated
     */
    private Long customerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invite_record.enable
     *
     * @mbg.generated
     */
    private Boolean enable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invite_record.has_trade
     *
     * @mbg.generated
     */
    private Boolean hasTrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invite_record.invitee_account
     *
     * @mbg.generated
     */
    private String inviteeAccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invite_record.invitee_id
     *
     * @mbg.generated
     */
    private Long inviteeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invite_record.reg_time
     *
     * @mbg.generated
     */
    private Long regTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invite_record.id
     *
     * @return the value of invite_record.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invite_record.id
     *
     * @param id the value for invite_record.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invite_record.customer_id
     *
     * @return the value of invite_record.customer_id
     *
     * @mbg.generated
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invite_record.customer_id
     *
     * @param customerId the value for invite_record.customer_id
     *
     * @mbg.generated
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invite_record.enable
     *
     * @return the value of invite_record.enable
     *
     * @mbg.generated
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invite_record.enable
     *
     * @param enable the value for invite_record.enable
     *
     * @mbg.generated
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invite_record.has_trade
     *
     * @return the value of invite_record.has_trade
     *
     * @mbg.generated
     */
    public Boolean getHasTrade() {
        return hasTrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invite_record.has_trade
     *
     * @param hasTrade the value for invite_record.has_trade
     *
     * @mbg.generated
     */
    public void setHasTrade(Boolean hasTrade) {
        this.hasTrade = hasTrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invite_record.invitee_account
     *
     * @return the value of invite_record.invitee_account
     *
     * @mbg.generated
     */
    public String getInviteeAccount() {
        return inviteeAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invite_record.invitee_account
     *
     * @param inviteeAccount the value for invite_record.invitee_account
     *
     * @mbg.generated
     */
    public void setInviteeAccount(String inviteeAccount) {
        this.inviteeAccount = inviteeAccount == null ? null : inviteeAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invite_record.invitee_id
     *
     * @return the value of invite_record.invitee_id
     *
     * @mbg.generated
     */
    public Long getInviteeId() {
        return inviteeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invite_record.invitee_id
     *
     * @param inviteeId the value for invite_record.invitee_id
     *
     * @mbg.generated
     */
    public void setInviteeId(Long inviteeId) {
        this.inviteeId = inviteeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invite_record.reg_time
     *
     * @return the value of invite_record.reg_time
     *
     * @mbg.generated
     */
    public Long getRegTime() {
        return regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invite_record.reg_time
     *
     * @param regTime the value for invite_record.reg_time
     *
     * @mbg.generated
     */
    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }
}