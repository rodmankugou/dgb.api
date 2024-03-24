package com.verificer.biz.beans.vo.shop;

import com.verificer.biz.beans.vo.stock.MerStockVo;
import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class ShopStockVo implements Serializable {
    @ApiModelProperty("店铺ID")
    private String id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("管理员名称")
    private String cpName;
    @ApiModelProperty("总库存")
    private BigDecimal totalCount;


    @ApiModelProperty("单SKU最高库存")
    private BigDecimal maxSkuCount;

    @ApiModelProperty("单SKU最低库存")
    private BigDecimal minSkuCount;


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


}
