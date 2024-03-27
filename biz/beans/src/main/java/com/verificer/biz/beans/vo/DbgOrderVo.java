package com.verificer.biz.beans.vo;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class DbgOrderVo implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("状态 1-待支付 2-备货中 3-待发货 4-运输中 5-已收货（完成）6-已评价 7-已关闭  22-待自提 101-超时被取消 102-用户取消")
    private Integer status;

    @ApiModelProperty("订单类型，1-线下订单 2-自提订单 3-平台配送订单 4-补货单 5-免单")
    private Integer orderType;

    @ApiModelProperty("售方类型，1-仓库 2-店铺")
    private Integer relType;

    @ApiModelProperty("仓库/店铺ID")
    private String relId;

    @ApiModelProperty("店铺/仓库名称")
    private String refName;

    @ApiModelProperty("会员折扣金额")
    @PriceDecimal
    private BigDecimal memberDiscountAmount;

    @ApiModelProperty("运费")
    @PriceDecimal
    private BigDecimal transitFee;

    @ApiModelProperty("订单金额")
    @PriceDecimal
    private BigDecimal amount;

    @ApiModelProperty("支付方式  1-微信支付 2-线下pos支付 3-现金支付")
    private Integer payType;

    @ApiModelProperty("支付记录id")
    private Long payId;

    @ApiModelProperty("是否会员的订单")
    private Boolean memberFlag;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("快的类型 快递类型 1-同城急送 2-普通快递 10-自提 ")
    private Integer transitType;

    @ApiModelProperty("收货人姓名")
    private String rcName;

    @ApiModelProperty("收货人手机号码")
    private String rcMobile;

    @ApiModelProperty("收获地址")
    private String rcFullAddr;

    @ApiModelProperty("活动ID")
    private Long activityId;

    @ApiModelProperty("活动标题")
    private String activityTitle;

    @ApiModelProperty("买家备注")
    private String buyerRemark;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("订单号")
    private String orderNum;

    @ApiModelProperty("运输单ID")
    private String transitOrderId;

    @ApiModelProperty("发货时间")
    private Long transitTime;

    @ApiModelProperty("物流单号")
    private String transitOrderNum;

    @ApiModelProperty("是否已提取。true-是；false-否")
    private Boolean takeFlag;

    @ApiModelProperty("提货时间")
    private Long takeTime;

    @ApiModelProperty("")
    private Long transitRcTime;

    @ApiModelProperty("订单明细")
    private List<OrderDetailVo> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getRelType() {
        return relType;
    }

    public void setRelType(Integer relType) {
        this.relType = relType;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTransitType() {
        return transitType;
    }

    public void setTransitType(Integer transitType) {
        this.transitType = transitType;
    }


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTransitOrderId() {
        return transitOrderId;
    }

    public void setTransitOrderId(String transitOrderId) {
        this.transitOrderId = transitOrderId;
    }

    public Long getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(Long transitTime) {
        this.transitTime = transitTime;
    }

    public String getTransitOrderNum() {
        return transitOrderNum;
    }

    public void setTransitOrderNum(String transitOrderNum) {
        this.transitOrderNum = transitOrderNum;
    }

    public Boolean getTakeFlag() {
        return takeFlag;
    }

    public void setTakeFlag(Boolean takeFlag) {
        this.takeFlag = takeFlag;
    }

    public Long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Long takeTime) {
        this.takeTime = takeTime;
    }

    public List<OrderDetailVo> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderDetailVo> orderItems) {
        this.orderItems = orderItems;
    }

    public String getRcName() {
        return rcName;
    }

    public void setRcName(String rcName) {
        this.rcName = rcName;
    }

    public String getRcMobile() {
        return rcMobile;
    }

    public void setRcMobile(String rcMobile) {
        this.rcMobile = rcMobile;
    }

    public BigDecimal getMemberDiscountAmount() {
        return memberDiscountAmount;
    }

    public void setMemberDiscountAmount(BigDecimal memberDiscountAmount) {
        this.memberDiscountAmount = memberDiscountAmount;
    }

    public BigDecimal getTransitFee() {
        return transitFee;
    }

    public void setTransitFee(BigDecimal transitFee) {
        this.transitFee = transitFee;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRcFullAddr() {
        return rcFullAddr;
    }

    public void setRcFullAddr(String rcFullAddr) {
        this.rcFullAddr = rcFullAddr;
    }

    public Long getTransitRcTime() {
        return transitRcTime;
    }

    public void setTransitRcTime(Long transitRcTime) {
        this.transitRcTime = transitRcTime;
    }

    public Boolean getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(Boolean memberFlag) {
        this.memberFlag = memberFlag;
    }
}




