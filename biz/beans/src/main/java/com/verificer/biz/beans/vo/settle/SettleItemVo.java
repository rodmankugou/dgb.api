package com.verificer.biz.beans.vo.settle;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class SettleItemVo implements Serializable {

    @ApiModelProperty("ID")
    private Long id;



    @ApiModelProperty("结算项")
    private String title;

    @ApiModelProperty("结算明细")
    private String remark;

    @ApiModelProperty("结算月份-年")
    private Integer year;

    @ApiModelProperty("结算月份-月")
    private Integer month;

    @ApiModelProperty("是否结算 true-是 false-否")
    private Boolean settleFlag;

    @ApiModelProperty("结算金额")
    @PriceDecimal
    private BigDecimal amount;

    @ApiModelProperty("佣金比例,0.01表示1%")
    private BigDecimal commissionRate;

    @ApiModelProperty("下属门店")
    private String childShopName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Boolean getSettleFlag() {
        return settleFlag;
    }

    public void setSettleFlag(Boolean settleFlag) {
        this.settleFlag = settleFlag;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getChildShopName() {
        return childShopName;
    }

    public void setChildShopName(String childShopName) {
        this.childShopName = childShopName;
    }
}
