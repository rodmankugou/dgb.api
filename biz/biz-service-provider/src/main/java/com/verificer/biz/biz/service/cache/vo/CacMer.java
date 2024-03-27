package com.verificer.biz.biz.service.cache.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CacMer implements Serializable{
    @ApiModelProperty
    private String id;
    @ApiModelProperty
    private String name;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;


    @ApiModelProperty("库存信息")
    List<CacStock> stockList ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public List<CacStock> getStockList() {
        return stockList;
    }

    public void setStockList(List<CacStock> stockList) {
        this.stockList = stockList;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }



}
