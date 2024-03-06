package com.verificer.beans.account;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.verificer.beans.BasePrecisionVo;
import com.verificer.utils.match.FormatUtil;
import com.verificer.utils.serialization.PrecisionSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 资产折合项
 */
@JsonSerialize(using = PrecisionSerializer.class)
public class AssetCovertVO extends BasePrecisionVo implements Serializable {

    // 折合类型
    private String convertSymbol;

    // 可用
    private BigDecimal available=BigDecimal.ZERO;

    // 冻结
    private BigDecimal frozen= BigDecimal.ZERO;


    private BigDecimal cnyPrice = BigDecimal.ZERO;
    private BigDecimal usdPrice = BigDecimal.ZERO;

    public BigDecimal getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(BigDecimal usdPrice) {
        this.usdPrice = usdPrice;
    }

    public String getConvertSymbol() {
        return convertSymbol;
    }

    public void setConvertSymbol(String convertSymbol) {
        this.convertSymbol = convertSymbol;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    public BigDecimal getTotal() {
        return this.available.add(this.frozen);
    }

    public String getTotalCny(){
        BigDecimal total = getTotal().multiply(this.cnyPrice);
        return FormatUtil.directFormatBigDecimal(total,2);
    }

    public void setCnyPrice(BigDecimal cnyPrice) {
        this.cnyPrice = cnyPrice;
    }

    @Override
    public String toString() {
        return "AssetCovertVO{" +
                "convertSymbol='" + convertSymbol + '\'' +
                ", available=" + available +
                ", frozen=" + frozen +
                '}';
    }
}
