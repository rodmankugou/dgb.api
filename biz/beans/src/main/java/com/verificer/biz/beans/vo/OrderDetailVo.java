package com.verificer.biz.beans.vo;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class OrderDetailVo implements Serializable {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("商品名称")
    private Long goodsName;

    @ApiModelProperty("规格ID")
    private Long specId;

    @ApiModelProperty("规格名称")
    private Long specName;

    @ApiModelProperty("规格图")
    private Long specImg;

    @ApiModelProperty("运输费用")
    private BigDecimal transitFee;

    @ApiModelProperty("单价")
    private BigDecimal price;

    @ApiModelProperty("数量")
    private Integer count;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("1-未发货 2-运输中 3-已签收")
    private Integer status;

    @ApiModelProperty("退款状态，1-退款中 2-退款失败 3-退款成功")
    private Integer refundStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(Long goodsName) {
        this.goodsName = goodsName;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecName() {
        return specName;
    }

    public void setSpecName(Long specName) {
        this.specName = specName;
    }

    public BigDecimal getTransitFee() {
        return transitFee;
    }

    public void setTransitFee(BigDecimal transitFee) {
        this.transitFee = transitFee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getSpecImg() {
        return specImg;
    }

    public void setSpecImg(Long specImg) {
        this.specImg = specImg;
    }
}
