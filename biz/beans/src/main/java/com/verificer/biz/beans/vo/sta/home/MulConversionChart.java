package com.verificer.biz.beans.vo.sta.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class MulConversionChart implements Serializable {


    @ApiModelProperty(value = "标题列表，如月份",required = true)
    private List<String> tips;

    @ApiModelProperty(value = "数值列表",required = true)
    private List<MulConChartItem> data;

    public MulConversionChart(List<String> tips, List<MulConChartItem> data) {
        this.tips = tips;
        this.data = data;
    }

    public MulConversionChart() {
    }




}
