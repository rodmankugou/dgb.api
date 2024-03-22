package com.verificer.biz.biz.entity;

import java.math.BigDecimal;

public class SettleItem {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.order_id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.settle_id
     *
     * @mbg.generated
     */
    private Long settleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.settle_phase
     *
     * @mbg.generated
     */
    private Integer settlePhase;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.shop_id
     *
     * @mbg.generated
     */
    private String shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.child_shop_id
     *
     * @mbg.generated
     */
    private String childShopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.year
     *
     * @mbg.generated
     */
    private Integer year;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.month
     *
     * @mbg.generated
     */
    private Integer month;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.settle_flag
     *
     * @mbg.generated
     */
    private Boolean settleFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.amount
     *
     * @mbg.generated
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.commission_rate
     *
     * @mbg.generated
     */
    private BigDecimal commissionRate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column settle_item.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.id
     *
     * @return the value of settle_item.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.id
     *
     * @param id the value for settle_item.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.order_id
     *
     * @return the value of settle_item.order_id
     *
     * @mbg.generated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.order_id
     *
     * @param orderId the value for settle_item.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.settle_id
     *
     * @return the value of settle_item.settle_id
     *
     * @mbg.generated
     */
    public Long getSettleId() {
        return settleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.settle_id
     *
     * @param settleId the value for settle_item.settle_id
     *
     * @mbg.generated
     */
    public void setSettleId(Long settleId) {
        this.settleId = settleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.settle_phase
     *
     * @return the value of settle_item.settle_phase
     *
     * @mbg.generated
     */
    public Integer getSettlePhase() {
        return settlePhase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.settle_phase
     *
     * @param settlePhase the value for settle_item.settle_phase
     *
     * @mbg.generated
     */
    public void setSettlePhase(Integer settlePhase) {
        this.settlePhase = settlePhase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.shop_id
     *
     * @return the value of settle_item.shop_id
     *
     * @mbg.generated
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.shop_id
     *
     * @param shopId the value for settle_item.shop_id
     *
     * @mbg.generated
     */
    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.child_shop_id
     *
     * @return the value of settle_item.child_shop_id
     *
     * @mbg.generated
     */
    public String getChildShopId() {
        return childShopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.child_shop_id
     *
     * @param childShopId the value for settle_item.child_shop_id
     *
     * @mbg.generated
     */
    public void setChildShopId(String childShopId) {
        this.childShopId = childShopId == null ? null : childShopId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.title
     *
     * @return the value of settle_item.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.title
     *
     * @param title the value for settle_item.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.remark
     *
     * @return the value of settle_item.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.remark
     *
     * @param remark the value for settle_item.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.year
     *
     * @return the value of settle_item.year
     *
     * @mbg.generated
     */
    public Integer getYear() {
        return year;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.year
     *
     * @param year the value for settle_item.year
     *
     * @mbg.generated
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.month
     *
     * @return the value of settle_item.month
     *
     * @mbg.generated
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.month
     *
     * @param month the value for settle_item.month
     *
     * @mbg.generated
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.settle_flag
     *
     * @return the value of settle_item.settle_flag
     *
     * @mbg.generated
     */
    public Boolean getSettleFlag() {
        return settleFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.settle_flag
     *
     * @param settleFlag the value for settle_item.settle_flag
     *
     * @mbg.generated
     */
    public void setSettleFlag(Boolean settleFlag) {
        this.settleFlag = settleFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.amount
     *
     * @return the value of settle_item.amount
     *
     * @mbg.generated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.amount
     *
     * @param amount the value for settle_item.amount
     *
     * @mbg.generated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.commission_rate
     *
     * @return the value of settle_item.commission_rate
     *
     * @mbg.generated
     */
    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.commission_rate
     *
     * @param commissionRate the value for settle_item.commission_rate
     *
     * @mbg.generated
     */
    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column settle_item.create_time
     *
     * @return the value of settle_item.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column settle_item.create_time
     *
     * @param createTime the value for settle_item.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
