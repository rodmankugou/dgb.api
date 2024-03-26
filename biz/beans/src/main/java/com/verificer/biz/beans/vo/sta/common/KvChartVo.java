package com.verificer.biz.beans.vo.sta.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class KvChartVo implements Serializable {
    @ApiModelProperty("总数")
    private BigDecimal total;

    @ApiModelProperty("统计行")
    private List<KvStaVo> list;


    public KvChartVo() {
    }

    public KvChartVo(BigDecimal total, List<KvStaVo> list) {
        this.total = total;
        this.list = list;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<KvStaVo> getList() {
        return list;
    }

    public void setList(List<KvStaVo> list) {
        this.list = list;
    }
}
