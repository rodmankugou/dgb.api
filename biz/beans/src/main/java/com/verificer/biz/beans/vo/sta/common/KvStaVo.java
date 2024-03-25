package com.verificer.biz.beans.vo.sta.common;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class KvStaVo implements Serializable {
    @ApiModelProperty("商品名")
    private String key;

    @ApiModelProperty("统计值")
    private BigDecimal value;

    public KvStaVo() {
    }

    public KvStaVo(String key, BigDecimal value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
