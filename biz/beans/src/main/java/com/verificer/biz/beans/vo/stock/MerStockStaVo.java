package com.verificer.biz.beans.vo.stock;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class MerStockStaVo implements Serializable {
    @ApiModelProperty("店铺/库存ID")
    private String relId;
    @ApiModelProperty(hidden = true)
    private Boolean stageFlag;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("管理员名称")
    private String cpName;
    @ApiModelProperty("总库存")
    private BigDecimal totalCount;
    @ApiModelProperty("SKU品种数")
    private BigDecimal skuTypeCount;

    @ApiModelProperty("单SKU最高库存")
    private BigDecimal maxSkuCount;

    @ApiModelProperty("单SKU最低库存")
    private BigDecimal minSkuCount;

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

    public BigDecimal getMaxSkuCount() {
        return maxSkuCount;
    }

    public void setMaxSkuCount(BigDecimal maxSkuCount) {
        this.maxSkuCount = maxSkuCount;
    }

    public BigDecimal getMinSkuCount() {
        return minSkuCount;
    }

    public void setMinSkuCount(BigDecimal minSkuCount) {
        this.minSkuCount = minSkuCount;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public Boolean getStageFlag() {
        return stageFlag;
    }

    public void setStageFlag(Boolean stageFlag) {
        this.stageFlag = stageFlag;
    }
}
