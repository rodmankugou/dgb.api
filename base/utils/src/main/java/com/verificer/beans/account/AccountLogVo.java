package com.verificer.beans.account;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountLogVo implements Serializable {
    private Long id;

    private String customerId;

    private Long accountId;

    private String subName;

    private Integer opType;

    private BigDecimal opAmount;

    private Long attachid;

    private BigDecimal beforeAvailable;

    private BigDecimal beforeFrozen;

    private BigDecimal afterAvailable;

    private BigDecimal afterFrozen;

    private String remark;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public BigDecimal getOpAmount() {
        return opAmount;
    }

    public void setOpAmount(BigDecimal opAmount) {
        this.opAmount = opAmount;
    }

    public Long getAttachid() {
        return attachid;
    }

    public void setAttachid(Long attachid) {
        this.attachid = attachid;
    }

    public BigDecimal getBeforeAvailable() {
        return beforeAvailable;
    }

    public void setBeforeAvailable(BigDecimal beforeAvailable) {
        this.beforeAvailable = beforeAvailable;
    }

    public BigDecimal getBeforeFrozen() {
        return beforeFrozen;
    }

    public void setBeforeFrozen(BigDecimal beforeFrozen) {
        this.beforeFrozen = beforeFrozen;
    }

    public BigDecimal getAfterAvailable() {
        return afterAvailable;
    }

    public void setAfterAvailable(BigDecimal afterAvailable) {
        this.afterAvailable = afterAvailable;
    }

    public BigDecimal getAfterFrozen() {
        return afterFrozen;
    }

    public void setAfterFrozen(BigDecimal afterFrozen) {
        this.afterFrozen = afterFrozen;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
