package com.verificer.biz.biz.service.core.stock.entity;

public class StockIdVo {
    private String relId;
    private Long specId;

    public StockIdVo(String relId, Long specId) {
        this.relId = relId;
        this.specId = specId;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }
}
