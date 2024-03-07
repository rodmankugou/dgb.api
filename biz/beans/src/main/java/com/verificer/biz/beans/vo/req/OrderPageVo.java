package com.verificer.biz.beans.vo.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderPageVo extends PageQueryVo {
    @ApiModelProperty("订单id")
    private Long id;
    @ApiModelProperty("订单号")
    private String orderNum;
    @ApiModelProperty("开始时间")
    private Long sTime;
    @ApiModelProperty("结束时间")
    private Long eTime;
    @ApiModelProperty("结算状态，1-未结算 2-待结算 3-已结算")
    private Integer settleStatus;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("订单类型")
    private Integer orderType;
    @ApiModelProperty("店铺ID")
    private String refId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getsTime() {
        return sTime;
    }

    public void setsTime(Long sTime) {
        this.sTime = sTime;
    }

    public Long geteTime() {
        return eTime;
    }

    public void seteTime(Long eTime) {
        this.eTime = eTime;
    }

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
