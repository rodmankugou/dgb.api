package com.verificer.beans.account;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 我的资产的资产项
 */
@ApiModel
public class AssetItemVo implements Serializable {

    // 账户Id
    @ApiModelProperty("账户id")
    private Long accountId;

    // 币种符号
    @ApiModelProperty("币种符号")
    private String symbol;

    //币种图标
    @ApiModelProperty("币种图标")
    private String imgUrl;

    // 可用
    @ApiModelProperty("可用")
    private BigDecimal availableAmount;

    // 拨付金额
    @ApiModelProperty("拨付金额")
    private BigDecimal bofuAmount;

    // 冻结
    @ApiModelProperty("冻结")
    private BigDecimal frozenAmount;

    // 总资产
    @ApiModelProperty("总资产")
    private BigDecimal totalAmount;

    //换算成美元后的金额
    @ApiModelProperty("换算成美元后的金额")
    private BigDecimal usdTransferAmount;

    // 币种ID
    @ApiModelProperty("币种ID")
    private Integer currencyId;

    // 充值金额下限
    @ApiModelProperty("充值金额下限")
    private BigDecimal minLimit;

    // 提币下限金额
    @ApiModelProperty("提币下限金额")
    private BigDecimal withdrawMinLimit;

    // 提币手续费
    @ApiModelProperty("提币手续费")
    private BigDecimal withdrawRate;

    private boolean allowedRecharge;

    private boolean allowedWithdraw;

    private boolean allowedTrade;

    private boolean hasTag;

    private Integer sortParam;

    public Integer getSortParam() {
		return sortParam;
	}

	public void setSortParam(Integer sortParam) {
		this.sortParam = sortParam;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getBofuAmount() {
        return bofuAmount;
    }

    public void setBofuAmount(BigDecimal bofuAmount) {
        this.bofuAmount = bofuAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(BigDecimal minLimit) {
        this.minLimit = minLimit;
    }

    public BigDecimal getWithdrawMinLimit() {
        return withdrawMinLimit;
    }

    public void setWithdrawMinLimit(BigDecimal withdrawMinLimit) {
        this.withdrawMinLimit = withdrawMinLimit;
    }

    public BigDecimal getWithdrawRate() {
        return withdrawRate;
    }

    public void setWithdrawRate(BigDecimal withdrawRate) {
        this.withdrawRate = withdrawRate;
    }

    public boolean isAllowedRecharge() {
        return allowedRecharge;
    }

    public void setAllowedRecharge(boolean allowedRecharge) {
        this.allowedRecharge = allowedRecharge;
    }

    public boolean isAllowedWithdraw() {
        return allowedWithdraw;
    }

    public void setAllowedWithdraw(boolean allowedWithdraw) {
        this.allowedWithdraw = allowedWithdraw;
    }

    public boolean isAllowedTrade() {
        return allowedTrade;
    }

    public void setAllowedTrade(boolean allowedTrade) {
        this.allowedTrade = allowedTrade;
    }

    public BigDecimal getTotalAmount() {
        return this.availableAmount.add(this.frozenAmount);
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isHasTag() {
        return hasTag;
    }

    public void setHasTag(boolean hasTag) {
        this.hasTag = hasTag;
    }


    public BigDecimal getUsdTransferAmount() {
        return usdTransferAmount;
    }

    public void setUsdTransferAmount(BigDecimal usdTransferAmount) {
        this.usdTransferAmount = usdTransferAmount;
    }
}
