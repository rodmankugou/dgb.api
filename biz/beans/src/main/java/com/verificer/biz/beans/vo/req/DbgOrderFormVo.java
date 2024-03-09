package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class DbgOrderFormVo implements Serializable {


    @ApiModelProperty("1-线下订单 2-自提订单 3-平台配送订单")
    private Integer orderType;

    @ApiModelProperty("售方类型，1-仓库 2-店铺")
    private Integer refType;

    @ApiModelProperty("仓库/店铺ID")
    private String refId;

    @ApiModelProperty(value = "地址ID，需要配送时，才需要填写",required = false)
    private Long addrId;

    @ApiModelProperty(value = "快的类型 快递类型 1-同城急送 2-普通快递",required = false)
    private Integer transitType;

    @ApiModelProperty("addressId")
    private Long id;

    @ApiModelProperty("买家备注")
    private String buyerRemark;

    List<OrderDetailFormVo> details;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getRefType() {
        return refType;
    }

    public void setRefType(Integer refType) {
        this.refType = refType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }


    public Integer getTransitType() {
        return transitType;
    }

    public void setTransitType(Integer transitType) {
        this.transitType = transitType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    public List<OrderDetailFormVo> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailFormVo> details) {
        this.details = details;
    }

    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }
}
