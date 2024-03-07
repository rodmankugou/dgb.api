package com.verificer.biz.beans.vo.req;

public class StockUpdVo {
    private boolean addFlag;
    private String relId;
    private long specId;
    private int count;

    public StockUpdVo(boolean addFlag, String relId, long specId, int count) {
        this.addFlag = addFlag;
        this.relId = relId;
        this.specId = specId;
        this.count = count;
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

    public int getCount() {
        return count;
    }
}
