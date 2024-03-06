package com.verificer.biz.biz.entity;

public class DbgOrderLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbg_order_log.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbg_order_log.order_id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbg_order_log.op_name
     *
     * @mbg.generated
     */
    private String opName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbg_order_log.op_id
     *
     * @mbg.generated
     */
    private String opId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbg_order_log.op_entry
     *
     * @mbg.generated
     */
    private String opEntry;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbg_order_log.order_status
     *
     * @mbg.generated
     */
    private Integer orderStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbg_order_log.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbg_order_log.id
     *
     * @return the value of dbg_order_log.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbg_order_log.id
     *
     * @param id the value for dbg_order_log.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbg_order_log.order_id
     *
     * @return the value of dbg_order_log.order_id
     *
     * @mbg.generated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbg_order_log.order_id
     *
     * @param orderId the value for dbg_order_log.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbg_order_log.op_name
     *
     * @return the value of dbg_order_log.op_name
     *
     * @mbg.generated
     */
    public String getOpName() {
        return opName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbg_order_log.op_name
     *
     * @param opName the value for dbg_order_log.op_name
     *
     * @mbg.generated
     */
    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbg_order_log.op_id
     *
     * @return the value of dbg_order_log.op_id
     *
     * @mbg.generated
     */
    public String getOpId() {
        return opId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbg_order_log.op_id
     *
     * @param opId the value for dbg_order_log.op_id
     *
     * @mbg.generated
     */
    public void setOpId(String opId) {
        this.opId = opId == null ? null : opId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbg_order_log.op_entry
     *
     * @return the value of dbg_order_log.op_entry
     *
     * @mbg.generated
     */
    public String getOpEntry() {
        return opEntry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbg_order_log.op_entry
     *
     * @param opEntry the value for dbg_order_log.op_entry
     *
     * @mbg.generated
     */
    public void setOpEntry(String opEntry) {
        this.opEntry = opEntry == null ? null : opEntry.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbg_order_log.order_status
     *
     * @return the value of dbg_order_log.order_status
     *
     * @mbg.generated
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbg_order_log.order_status
     *
     * @param orderStatus the value for dbg_order_log.order_status
     *
     * @mbg.generated
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbg_order_log.create_time
     *
     * @return the value of dbg_order_log.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbg_order_log.create_time
     *
     * @param createTime the value for dbg_order_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
