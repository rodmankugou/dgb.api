package com.verificer.biz.biz.service.cache.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verificer.biz.beans.vo.goods.AGoodsVo;
import com.verificer.biz.biz.service.cache.gache.sort.impl.mult.MulParam;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class SortableGoods extends AGoodsVo {


    private Integer rank;


    private Long distance;

    private BigDecimal mulSortVal;
    private MulParam mulParam = new MulParam();


    private Long saleCount;
    private BigDecimal minPrice;
    private BigDecimal minOrigPrice;
    private Long marketTime;




    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public BigDecimal getMulSortVal() {
        return mulSortVal;
    }

    public void setMulSortVal(BigDecimal mulSortVal) {
        this.mulSortVal = mulSortVal;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinOrigPrice() {
        return minOrigPrice;
    }

    public void setMinOrigPrice(BigDecimal minOrigPrice) {
        this.minOrigPrice = minOrigPrice;
    }

    public MulParam getMulParam() {
        return mulParam;
    }

    public void setMulParam(MulParam mulParam) {
        this.mulParam = mulParam;
    }


    public Long getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(Long marketTime) {
        this.marketTime = marketTime;
    }
}
