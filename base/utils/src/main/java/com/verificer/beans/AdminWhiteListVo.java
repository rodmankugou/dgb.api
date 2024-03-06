package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
@ApiModel
public class AdminWhiteListVo implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户id")
    private Long customerId;
    @ApiModelProperty("交易对id")
    private Integer exchangeId;
    @ApiModelProperty("交易对英文缩写")
    private String exchangeSymbol;
    @ApiModelProperty("用户账号")
    private String customerAccount;
    @ApiModelProperty("名字")
    private String firstName;
    @ApiModelProperty("姓氏")
    private String lastName;
    @ApiModelProperty("证件号码")
    private String idCardNum;
    @ApiModelProperty("申请状态,1-待审核 2-审核通过 3-审核驳回")
    private Integer applyStatus;
    @ApiModelProperty("白名单类型,1-免手续费 2-交易禁止")
    private Integer applyType;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("修改时间")
    private Long updateTime;

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

    public String getExchangeSymbol() {
        return exchangeSymbol;
    }

    public void setExchangeSymbol(String exchangeSymbol) {
        this.exchangeSymbol = exchangeSymbol;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
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
}
