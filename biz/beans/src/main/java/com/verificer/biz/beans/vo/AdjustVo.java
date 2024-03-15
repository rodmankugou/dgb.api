package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class AdjustVo implements Serializable {

    @ApiModelProperty("Id")
    private Integer id;

    @ApiModelProperty("状态 1-待确认 11-已确认收货")
    private Integer status;

    @ApiModelProperty("1-仓库配货到仓库 2-仓库接收门店退货  3-仓库进货 11-门店从仓库入货 2-门店退货给仓库")
    private Integer type;

    @ApiModelProperty("仓库ID")
    private String stageId;

    @ApiModelProperty("仓库名称")
    private String stageName;

    @ApiModelProperty("店铺ID")
    private String shopId;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("规格ID")
    private Long specId;

    @ApiModelProperty("规格名称")
    private String specName;

    @ApiModelProperty("数量")
    private BigDecimal count;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("创建时间")
    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
