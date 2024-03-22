package com.verificer.biz.beans.vo.settle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class SettleOrdVo implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("订单编号")
    private String ordNum;

    @ApiModelProperty("分佣比例")
    private BigDecimal commissionRate;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("结算份数")
    private Long count;

    @ApiModelProperty("年份")
    private Integer year;

    @ApiModelProperty("月份")
    private Integer month;

    @ApiModelProperty("结算时间")
    private Long settleTime;

    @ApiModelProperty("是否结算")
    private Boolean settleFlag;

    @ApiModelProperty("结算凭证")
    private String certificateImg;

    @ApiModelProperty("结算员")
    private String transferStaffName;

    @ApiModelProperty("店铺ID")
    private String shopId;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("门店等级 1-一级门店 2-二级门店" )
    private Integer shopLevel;

    @ApiModelProperty("门店当前会员数")
    private Integer curMemberCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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

    public Long getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Long settleTime) {
        this.settleTime = settleTime;
    }

    public Boolean getSettleFlag() {
        return settleFlag;
    }

    public void setSettleFlag(Boolean settleFlag) {
        this.settleFlag = settleFlag;
    }

    public String getCertificateImg() {
        return certificateImg;
    }

    public void setCertificateImg(String certificateImg) {
        this.certificateImg = certificateImg;
    }

    public String getTransferStaffName() {
        return transferStaffName;
    }

    public void setTransferStaffName(String transferStaffName) {
        this.transferStaffName = transferStaffName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(Integer shopLevel) {
        this.shopLevel = shopLevel;
    }

    public Integer getCurMemberCount() {
        return curMemberCount;
    }

    public void setCurMemberCount(Integer curMemberCount) {
        this.curMemberCount = curMemberCount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
