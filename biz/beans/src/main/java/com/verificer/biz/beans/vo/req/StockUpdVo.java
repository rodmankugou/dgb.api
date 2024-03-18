package com.verificer.biz.beans.vo.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockUpdVo implements Serializable {
    private boolean addFlag;
    private String relId;
    private long specId;
    private BigDecimal count;
    private Integer opType;
    private String remark;

    public StockUpdVo(boolean addFlag, String relId, long specId, BigDecimal count,Integer opType,String remark) {
        this.addFlag = addFlag;
        this.relId = relId;
        this.specId = specId;
        this.count = count;
        this.opType = opType;
        this.remark = remark;
    }

    public boolean isAddFlag() {
        return addFlag;
    }

    public String getRelId() {
        return relId;
    }

    public long getSpecId() {
        return specId;
    }

    public BigDecimal getCount() {
        return count;
    }

    public Integer getOpType() {
        return opType;
    }

    public String getRemark() {
        return remark;
    }
}
