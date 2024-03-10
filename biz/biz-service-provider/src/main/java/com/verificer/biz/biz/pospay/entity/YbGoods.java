package com.verificer.biz.biz.pospay.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class YbGoods implements Serializable {
    private Long uid;
    private String name;
    private Long categoryUid;
    private String sellPrice;
    @JSONField(name = "attribute5")
    private String goodsNum;
    @JSONField(name = "attribute6")
    private String specName;
    @JSONField(name = "attribute7")
    private Integer mainSpecFlag;

    private Integer enable;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(Long categoryUid) {
        this.categoryUid = categoryUid;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getMainSpecFlag() {
        return mainSpecFlag;
    }

    public void setMainSpecFlag(Integer mainSpecFlag) {
        this.mainSpecFlag = mainSpecFlag;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
