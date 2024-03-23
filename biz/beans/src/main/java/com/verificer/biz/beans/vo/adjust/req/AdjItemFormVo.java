package com.verificer.biz.beans.vo.adjust.req;

import com.verificer.utils.decimal.PrcLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class AdjItemFormVo implements Serializable {
    @ApiModelProperty("商品ID")
    private Long goodsId;
    @ApiModelProperty("规格ID")
    private Long specId;

    @ApiModelProperty("数量")
    @PrcLimit(2)
    private BigDecimal count;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
