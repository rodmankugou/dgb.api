package com.verificer.biz.beans.vo.stock;

import java.io.Serializable;
import java.math.BigDecimal;

public class StaMaxMinVo implements Serializable {
    private String relId;
    private BigDecimal max;
    private BigDecimal min;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }
}
