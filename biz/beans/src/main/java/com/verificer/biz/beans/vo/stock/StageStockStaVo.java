package com.verificer.biz.beans.vo.stock;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class StageStockStaVo implements Serializable {
    @ApiModelProperty("仓库ID")
    private String relId;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("管理员名称")
    private String cpName;
    @ApiModelProperty("总库存")
    private BigDecimal totalCount;
    @ApiModelProperty("SKU品种数")
    private BigDecimal skuTypeCount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getSkuTypeCount() {
        return skuTypeCount;
    }

    public void setSkuTypeCount(BigDecimal skuTypeCount) {
        this.skuTypeCount = skuTypeCount;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }
}
