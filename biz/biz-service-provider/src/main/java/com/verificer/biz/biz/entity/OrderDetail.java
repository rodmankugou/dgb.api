package com.verificer.biz.biz.entity;

import java.math.BigDecimal;

public class OrderDetail {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.order_id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.goods_id
     *
     * @mbg.generated
     */
    private Long goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.goods_name
     *
     * @mbg.generated
     */
    private String goodsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.spec_id
     *
     * @mbg.generated
     */
    private Long specId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.spec_name
     *
     * @mbg.generated
     */
    private String specName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.spec_img
     *
     * @mbg.generated
     */
    private String specImg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.transit_fee
     *
     * @mbg.generated
     */
    private BigDecimal transitFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.real_price
     *
     * @mbg.generated
     */
    private BigDecimal realPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.count
     *
     * @mbg.generated
     */
    private BigDecimal count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.amount
     *
     * @mbg.generated
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.real_amount
     *
     * @mbg.generated
     */
    private BigDecimal realAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_detail.refund_status
     *
     * @mbg.generated
     */
    private Integer refundStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.id
     *
     * @return the value of order_detail.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.id
     *
     * @param id the value for order_detail.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.order_id
     *
     * @return the value of order_detail.order_id
     *
     * @mbg.generated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.order_id
     *
     * @param orderId the value for order_detail.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.goods_id
     *
     * @return the value of order_detail.goods_id
     *
     * @mbg.generated
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.goods_id
     *
     * @param goodsId the value for order_detail.goods_id
     *
     * @mbg.generated
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.goods_name
     *
     * @return the value of order_detail.goods_name
     *
     * @mbg.generated
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.goods_name
     *
     * @param goodsName the value for order_detail.goods_name
     *
     * @mbg.generated
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.spec_id
     *
     * @return the value of order_detail.spec_id
     *
     * @mbg.generated
     */
    public Long getSpecId() {
        return specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.spec_id
     *
     * @param specId the value for order_detail.spec_id
     *
     * @mbg.generated
     */
    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.spec_name
     *
     * @return the value of order_detail.spec_name
     *
     * @mbg.generated
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.spec_name
     *
     * @param specName the value for order_detail.spec_name
     *
     * @mbg.generated
     */
    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.spec_img
     *
     * @return the value of order_detail.spec_img
     *
     * @mbg.generated
     */
    public String getSpecImg() {
        return specImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.spec_img
     *
     * @param specImg the value for order_detail.spec_img
     *
     * @mbg.generated
     */
    public void setSpecImg(String specImg) {
        this.specImg = specImg == null ? null : specImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.transit_fee
     *
     * @return the value of order_detail.transit_fee
     *
     * @mbg.generated
     */
    public BigDecimal getTransitFee() {
        return transitFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.transit_fee
     *
     * @param transitFee the value for order_detail.transit_fee
     *
     * @mbg.generated
     */
    public void setTransitFee(BigDecimal transitFee) {
        this.transitFee = transitFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.price
     *
     * @return the value of order_detail.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.price
     *
     * @param price the value for order_detail.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.real_price
     *
     * @return the value of order_detail.real_price
     *
     * @mbg.generated
     */
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.real_price
     *
     * @param realPrice the value for order_detail.real_price
     *
     * @mbg.generated
     */
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.count
     *
     * @return the value of order_detail.count
     *
     * @mbg.generated
     */
    public BigDecimal getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.count
     *
     * @param count the value for order_detail.count
     *
     * @mbg.generated
     */
    public void setCount(BigDecimal count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.amount
     *
     * @return the value of order_detail.amount
     *
     * @mbg.generated
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.amount
     *
     * @param amount the value for order_detail.amount
     *
     * @mbg.generated
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.real_amount
     *
     * @return the value of order_detail.real_amount
     *
     * @mbg.generated
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.real_amount
     *
     * @param realAmount the value for order_detail.real_amount
     *
     * @mbg.generated
     */
    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.status
     *
     * @return the value of order_detail.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.status
     *
     * @param status the value for order_detail.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_detail.refund_status
     *
     * @return the value of order_detail.refund_status
     *
     * @mbg.generated
     */
    public Integer getRefundStatus() {
        return refundStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_detail.refund_status
     *
     * @param refundStatus the value for order_detail.refund_status
     *
     * @mbg.generated
     */
    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }
}
