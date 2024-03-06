package com.verificer.beans.account;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.verificer.beans.BasePrecisionVo;
import com.verificer.utils.serialization.PrecisionSerializer;

import java.io.Serializable;

/**
 * 我的资产的资产项
 */
@JsonSerialize(using = PrecisionSerializer.class)
public class AssetStrVo extends BasePrecisionVo implements Serializable {

    // 币种符号
    private String symbol;

    // 可用
    private String availableAmount;

    // 冻结
    private String frozenAmount;

    // 总资产
    private String totalAmount;

    // 币种ID
    private Integer currencyId;

    private String rechargeMinLimit;

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

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getRechargeMinLimit() {
        return rechargeMinLimit;
    }

    public void setRechargeMinLimit(String rechargeMinLimit) {
        this.rechargeMinLimit = rechargeMinLimit;
    }
}
