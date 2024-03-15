package com.verificer.biz.biz.entity;

import java.math.BigDecimal;

public class AfterSale {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.root_order_id
     *
     * @mbg.generated
     */
    private Long rootOrderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.order_id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.goods_id
     *
     * @mbg.generated
     */
    private Long goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.goods_name
     *
     * @mbg.generated
     */
    private String goodsName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.spec_id
     *
     * @mbg.generated
     */
    private Long specId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.spec_name
     *
     * @mbg.generated
     */
    private String specName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.count
     *
     * @mbg.generated
     */
    private BigDecimal count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.reason
     *
     * @mbg.generated
     */
    private String reason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.img_list
     *
     * @mbg.generated
     */
    private String imgList;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.review_staff_id
     *
     * @mbg.generated
     */
    private Long reviewStaffId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.review_staff_name
     *
     * @mbg.generated
     */
    private String reviewStaffName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.reject_reason
     *
     * @mbg.generated
     */
    private String rejectReason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.create_time
     *
     * @mbg.generated
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_sale.expire_time
     *
     * @mbg.generated
     */
    private Long expireTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.id
     *
     * @return the value of after_sale.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.id
     *
     * @param id the value for after_sale.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.root_order_id
     *
     * @return the value of after_sale.root_order_id
     *
     * @mbg.generated
     */
    public Long getRootOrderId() {
        return rootOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.root_order_id
     *
     * @param rootOrderId the value for after_sale.root_order_id
     *
     * @mbg.generated
     */
    public void setRootOrderId(Long rootOrderId) {
        this.rootOrderId = rootOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.status
     *
     * @return the value of after_sale.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.status
     *
     * @param status the value for after_sale.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.order_id
     *
     * @return the value of after_sale.order_id
     *
     * @mbg.generated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.order_id
     *
     * @param orderId the value for after_sale.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.goods_id
     *
     * @return the value of after_sale.goods_id
     *
     * @mbg.generated
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.goods_id
     *
     * @param goodsId the value for after_sale.goods_id
     *
     * @mbg.generated
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.goods_name
     *
     * @return the value of after_sale.goods_name
     *
     * @mbg.generated
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.goods_name
     *
     * @param goodsName the value for after_sale.goods_name
     *
     * @mbg.generated
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.spec_id
     *
     * @return the value of after_sale.spec_id
     *
     * @mbg.generated
     */
    public Long getSpecId() {
        return specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.spec_id
     *
     * @param specId the value for after_sale.spec_id
     *
     * @mbg.generated
     */
    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.spec_name
     *
     * @return the value of after_sale.spec_name
     *
     * @mbg.generated
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.spec_name
     *
     * @param specName the value for after_sale.spec_name
     *
     * @mbg.generated
     */
    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.count
     *
     * @return the value of after_sale.count
     *
     * @mbg.generated
     */
    public BigDecimal getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.count
     *
     * @param count the value for after_sale.count
     *
     * @mbg.generated
     */
    public void setCount(BigDecimal count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.reason
     *
     * @return the value of after_sale.reason
     *
     * @mbg.generated
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.reason
     *
     * @param reason the value for after_sale.reason
     *
     * @mbg.generated
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.img_list
     *
     * @return the value of after_sale.img_list
     *
     * @mbg.generated
     */
    public String getImgList() {
        return imgList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.img_list
     *
     * @param imgList the value for after_sale.img_list
     *
     * @mbg.generated
     */
    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.review_staff_id
     *
     * @return the value of after_sale.review_staff_id
     *
     * @mbg.generated
     */
    public Long getReviewStaffId() {
        return reviewStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.review_staff_id
     *
     * @param reviewStaffId the value for after_sale.review_staff_id
     *
     * @mbg.generated
     */
    public void setReviewStaffId(Long reviewStaffId) {
        this.reviewStaffId = reviewStaffId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.review_staff_name
     *
     * @return the value of after_sale.review_staff_name
     *
     * @mbg.generated
     */
    public String getReviewStaffName() {
        return reviewStaffName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.review_staff_name
     *
     * @param reviewStaffName the value for after_sale.review_staff_name
     *
     * @mbg.generated
     */
    public void setReviewStaffName(String reviewStaffName) {
        this.reviewStaffName = reviewStaffName == null ? null : reviewStaffName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.reject_reason
     *
     * @return the value of after_sale.reject_reason
     *
     * @mbg.generated
     */
    public String getRejectReason() {
        return rejectReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.reject_reason
     *
     * @param rejectReason the value for after_sale.reject_reason
     *
     * @mbg.generated
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.create_time
     *
     * @return the value of after_sale.create_time
     *
     * @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.create_time
     *
     * @param createTime the value for after_sale.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_sale.expire_time
     *
     * @return the value of after_sale.expire_time
     *
     * @mbg.generated
     */
    public Long getExpireTime() {
        return expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_sale.expire_time
     *
     * @param expireTime the value for after_sale.expire_time
     *
     * @mbg.generated
     */
    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}