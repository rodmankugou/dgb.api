package com.verificer.biz.beans.vo.sta.home;

import com.verificer.biz.beans.vo.sta.common.OneLatChart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class HomeSaleAndVisitVo  implements Serializable {
    @ApiModelProperty("销售额")
    private OneLatChart sales;

    @ApiModelProperty("访问量")
    private OneLatChart visit;

    public OneLatChart getSales() {
        return sales;
    }

    public void setSales(OneLatChart sales) {
        this.sales = sales;
    }

    public OneLatChart getVisit() {
        return visit;
    }

    public void setVisit(OneLatChart visit) {
        this.visit = visit;
    }
}
