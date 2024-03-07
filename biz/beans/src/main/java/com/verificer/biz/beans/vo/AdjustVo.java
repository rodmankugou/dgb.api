package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AdjustVo implements Serializable {

    @ApiModelProperty("Id")
    private Integer id;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("方向。1-仓库发往店铺 0-店铺退往仓库")
    private Integer direction;

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
    private Integer count;

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

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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
