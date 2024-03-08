package com.verificer.biz.biz.pospay.entity.req;

import org.web3j.abi.datatypes.Bool;

import java.math.BigDecimal;

public class UpdGoodsReq {
    private Long uid;
    private BigDecimal price;
    private BigDecimal categoryUid;
    private String name;
    private String specName;
    private Boolean isMainSpec;
    private Integer enable; //1可用 0禁用 -1删除



    public static UpdGoodsReq buildDelReq(Long uid){
        UpdGoodsReq req =new UpdGoodsReq();
        req.uid = uid;
        req.enable = -1;
        return req;
    }

    public static UpdGoodsReq buildDisableReq(Long uid){
        UpdGoodsReq req =new UpdGoodsReq();
        req.uid = uid;
        req.enable = 0;
        return req;
    }

    public static UpdGoodsReq buildEnableReq(Long uid){
        UpdGoodsReq req =new UpdGoodsReq();
        req.uid = uid;
        req.enable = 1;
        return req;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(BigDecimal categoryUid) {
        this.categoryUid = categoryUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }


    public Boolean getMainSpec() {
        return isMainSpec;
    }

    public void setMainSpec(Boolean mainSpec) {
        isMainSpec = mainSpec;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }


}
