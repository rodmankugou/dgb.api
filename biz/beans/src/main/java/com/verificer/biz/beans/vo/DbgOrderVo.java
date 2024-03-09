package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class DbgOrderVo implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("状态 1-待支付 2-备货中 3-待发货 4-运输中 5-已收货（完成）6-已评价 7-已关闭 21-线下订单 22-线下自提 101-超时被取消 102-用户取消")
    private Integer status;

    @ApiModelProperty("订单类型，1-线下订单 2-自提订单 3-平台配送订单")
    private Integer orderType;

    @ApiModelProperty("售方类型，1-仓库 2-店铺")
    private Integer refType;

    @ApiModelProperty("仓库/店铺ID")
    private String refId;

    @ApiModelProperty("店铺/仓库名称")
    private String refName;

    @ApiModelProperty("支付方式  1-微信支付 2-线下pos支付 3-现金支付")
    private Integer payType;

    @ApiModelProperty("支付记录id")
    private Long payId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("快的类型 快递类型 1-同城急送 2-普通快递")
    private Integer transitType;

    @ApiModelProperty("收货人姓名")
    private String rcName;

    @ApiModelProperty("收货人手机号码")
    private String rcMobile;

    @ApiModelProperty("收获地址")
    private String rcAddr;

    @ApiModelProperty("收获地址详细地址")
    private String rcAddrDetail;



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

    public Integer getRefType() {
        return refType;
    }

    public void setRefType(Integer refType) {
        this.refType = refType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
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

    public String getRcAddr() {
        return rcAddr;
    }

    public void setRcAddr(String rcAddr) {
        this.rcAddr = rcAddr;
    }

    public String getRcAddrDetail() {
        return rcAddrDetail;
    }

    public void setRcAddrDetail(String rcAddrDetail) {
        this.rcAddrDetail = rcAddrDetail;
    }
}
