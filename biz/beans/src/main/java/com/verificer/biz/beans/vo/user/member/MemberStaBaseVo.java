package com.verificer.biz.beans.vo.user.member;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberStaBaseVo implements Serializable {
    private Integer totalCount;
    private BigDecimal totalAmount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
