package com.verificer.biz.beans.vo.user.req;

import java.io.Serializable;

public class SumAccAmountVo implements Serializable {
    private Long accountId;
    private Integer opType;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }
}
