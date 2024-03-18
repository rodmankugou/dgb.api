package com.verificer.biz.beans.vo.order;

import com.verificer.biz.beans.vo.req.OrdItemFormVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class OrdFormVo implements Serializable {
    private String userId;


    @ApiModelProperty("1-线下订单 2-自提订单 3-平台配送订单")
    private Integer orderType;

    @ApiModelProperty("售方类型，1-仓库 2-店铺")
    private Integer relType;

    @ApiModelProperty("仓库/店铺ID")
    private String relId;

    @ApiModelProperty(value = "地址ID，需要配送时，才需要填写",required = false)
    private Long addrId;

    @ApiModelProperty(value = "快递类型 快递类型 1-同城急送 2-普通快递",required = false)
    private Integer transitType;


    @ApiModelProperty("买家备注")
    private String buyerRemark;

    @ApiModelProperty("订单明细")
    List<OrdItemFormVo> details;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getRelType() {
        return relType;
    }

    public void setRelType(Integer relType) {
        this.relType = relType;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }


    public Integer getTransitType() {
        return transitType;
    }

    public void setTransitType(Integer transitType) {
        this.transitType = transitType;
    }


    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

        public List<OrdItemFormVo> getDetails() {
        return details;
    }

    public void setDetails(List<OrdItemFormVo> details) {
        this.details = details;
    }

    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
