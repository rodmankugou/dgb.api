package com.verificer.beans.bankcard;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author baixiaozheng
 * @desc ${DESCRIPTION}
 * @date 2018/5/13 下午4:33
 */
@ApiModel
public class BankcardVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 银行
     */
    @ApiModelProperty("银行")
    private String bankName;
    /**
     * 银行开户行支行
     */
    @ApiModelProperty("银行开户行支行")
    private String subBankName;
    /**
     * 银行卡
     */
    @ApiModelProperty("银行卡")
    private String cardNumber;
    /**
     * 账号持有人名字
     */
    @ApiModelProperty("账号持有人名字")
    private String cardOwner;
    /**
     * 详细信息
     */
    @ApiModelProperty("详细信息")
    private String detail;

    /**
     * routingNumber
     */
    @ApiModelProperty("routingNumber")
    private String routingNumber;
    /**
     * swift
     */
    @ApiModelProperty("swift")
    private String swift;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    @ApiModelProperty("状态")
    private Integer status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubBankName() {
        return subBankName;
    }

    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
