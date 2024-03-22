package com.verificer.biz.beans.vo.settle.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class SettleItemQryVo extends PageQueryVo {
    @ApiModelProperty("店铺ID")
    private String shopId;
    @ApiModelProperty("是否结算 true-是 false-否")
    private Boolean settleFlag;
    @ApiModelProperty("结算明细")
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
}
