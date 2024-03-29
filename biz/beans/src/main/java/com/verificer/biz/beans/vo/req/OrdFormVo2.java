package com.verificer.biz.beans.vo.req;

import com.verificer.biz.beans.vo.order.req.OrdFormVo;
import io.swagger.annotations.ApiModel;

@ApiModel
public class OrdFormVo2 extends OrdFormVo {


    private Long userId;

    private Integer status;

    private Integer payType;

    private String orderNum;

    private Long createTime;
    private Long transitTime;
    private String transitOrderNum;
    private Long takeTime;
    private String takeCode;


    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(Long transitTime) {
        this.transitTime = transitTime;
    }

    public String getTransitOrderNum() {
        return transitOrderNum;
    }

    public void setTransitOrderNum(String transitOrderNum) {
        this.transitOrderNum = transitOrderNum;
    }

    public Long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Long takeTime) {
        this.takeTime = takeTime;
    }

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }
}
