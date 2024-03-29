package com.verificer.biz.beans.vo.sta.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class OneLatChart implements Serializable {


    @ApiModelProperty("总数/主数据")
    private BigDecimal total;

    @ApiModelProperty("环比，可能不需要用到")
    private BigDecimal rise;

    @ApiModelProperty("平均值")
    private BigDecimal avg;

    @ApiModelProperty(value = "标题列表，如月份",required = true)
    private List<String> tips;

    @ApiModelProperty(value = "数值列表",required = true)
    private List<BigDecimal> data;

    public OneLatChart() {
    }

    public static OneLatChart createAvgChart(BigDecimal total, List<String> tips, List<BigDecimal> data,BigDecimal avg){
        OneLatChart chart = new OneLatChart();
        chart.total = total;
        chart.avg = avg;
        chart.tips = tips;
        chart.data = data;
        return chart;
    }




    public OneLatChart(BigDecimal total, BigDecimal rise, List<String> tips, List<BigDecimal> data) {
        this.total = total;
        this.rise = rise;
        this.tips = tips;
        this.data = data;
    }

    public BigDecimal getTotal() {
        return total;
    }


    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<String> getTips() {
        return tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }

    public List<BigDecimal> getData() {
        return data;
    }

    public void setData(List<BigDecimal> data) {
        this.data = data;
    }

    public BigDecimal getRise() {
        return rise;
    }

    public void setRise(BigDecimal rise) {
        this.rise = rise;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "OneLatChart{" +
                "total=" + total +
                ", rise=" + rise +
                ", avg=" + avg +
                ", tips=" + tips +
                ", data=" + data +
                '}';
    }
}
