package com.verificer.biz.beans.vo.settle.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class SettleItemQryVo extends PageQueryVo {
    @ApiModelProperty(value = "订单id",required = true)
    private Long orderId;
    @ApiModelProperty(value = "店铺ID",required = false)
    private String shopId;
    @ApiModelProperty(value = "是否结算 true-是 false-否",required = false)
    private Boolean settleFlag;
    @ApiModelProperty(value = "结算明细",required = false)
    private String remark;


    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Boolean getSettleFlag() {
        return settleFlag;
    }

    public void setSettleFlag(Boolean settleFlag) {
        this.settleFlag = settleFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
