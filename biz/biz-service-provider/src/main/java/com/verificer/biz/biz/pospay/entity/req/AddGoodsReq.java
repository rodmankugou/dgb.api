package com.verificer.biz.biz.pospay.entity.req;

import java.math.BigDecimal;

public class AddGoodsReq {
    private Long goodsId;
    private String name;
    private Long posCatId;
    private String specName;
    private Boolean isMainSpec;
    private BigDecimal price;
    private Boolean isWeighing;

    public Long getGoodsId() {
        return goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getPosCatId() {
        return posCatId;
    }

    public void setPosCatId(Long posCatId) {
        this.posCatId = posCatId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getMainSpec() {
        return isMainSpec;
    }

    public void setMainSpec(Boolean mainSpec) {
        isMainSpec = mainSpec;
    }

    public Boolean getWeighing() {
        return isWeighing;
    }

    public void setWeighing(Boolean weighing) {
        isWeighing = weighing;
    }
}
