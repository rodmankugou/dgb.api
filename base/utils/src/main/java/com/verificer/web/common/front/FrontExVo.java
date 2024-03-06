package com.verificer.web.common.front;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 前台交易对
 */
public class FrontExVo implements Serializable {

    // 交易对ID
    private Integer exchangeId;

    //对标币种id
    private Integer quoteCurrencyId;

    // 缩写
    private String symbol;

    // 价格精度
    private Integer pricePrecision;

    // 数量精度
    private Integer amountPrecision;

    // 图片地址
    private String imageURL;

    //是否允许交易
    private Boolean allowTrade;

    //分区类型
    private String partitionType;

    private BigDecimal feeRate;

    //自选状态
    private Boolean optionalStatus;

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(Integer pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public Integer getAmountPrecision() {
        return amountPrecision;
    }

    public void setAmountPrecision(Integer amountPrecision) {
        this.amountPrecision = amountPrecision;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getAllowTrade() {
        return allowTrade;
    }

    public void setAllowTrade(Boolean allowTrade) {
        this.allowTrade = allowTrade;
    }

    public String getPartitionType() {
        return partitionType;
    }

    public void setPartitionType(String partitionType) {
        this.partitionType = partitionType;
    }

    public Integer getQuoteCurrencyId() {
        return quoteCurrencyId;
    }

    public void setQuoteCurrencyId(Integer quoteCurrencyId) {
        this.quoteCurrencyId = quoteCurrencyId;
    }

    public Boolean getOptionalStatus() {
        return optionalStatus;
    }

    public void setOptionalStatus(Boolean optionalStatus) {
        this.optionalStatus = optionalStatus;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }
}
