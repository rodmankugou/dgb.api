package com.verificer.biz.beans.vo.settle;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class PlaIncomeLogVo implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("收支来源 1-会员注册 2-门店接口")
    private Integer type;

    @ApiModelProperty("订单编号")
    private String ordNum;

    @ApiModelProperty("金额")
    @PriceDecimal
    private BigDecimal amount;

    @ApiModelProperty("收支类型， true-收入 false-支出")
    private Boolean incomeFlag;

    @ApiModelProperty("时间")
    private Long createTime;


    @ApiModelProperty("说明")
    private String remark;

    @ApiModelProperty("店铺名")
    private String shopName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getIncomeFlag() {
        return incomeFlag;
    }

    public void setIncomeFlag(Boolean incomeFlag) {
        this.incomeFlag = incomeFlag;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
