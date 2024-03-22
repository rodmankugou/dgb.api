package com.verificer.biz.beans.vo.settle.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SettleOrdQryVo extends PageQueryVo {
    @ApiModelProperty("省编码，如广东是44")
    private String adrArea1;
    @ApiModelProperty("市编码")
    private String adrArea2;
    @ApiModelProperty("区编码")
    private String adrArea3;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("查询时间-开始年份")
    private Integer sYear;
    @ApiModelProperty("查询时间-开始月份")
    private Integer sMonth;
    @ApiModelProperty("查询时间-截止年份")
    private Integer eYear;
    @ApiModelProperty("查询时间-截止月份")
    private Integer eMonth;

    public String getAdrArea1() {
        return adrArea1;
    }

    public void setAdrArea1(String adrArea1) {
        this.adrArea1 = adrArea1;
    }

    public String getAdrArea2() {
        return adrArea2;
    }

    public void setAdrArea2(String adrArea2) {
        this.adrArea2 = adrArea2;
    }

    public String getAdrArea3() {
        return adrArea3;
    }

    public void setAdrArea3(String adrArea3) {
        this.adrArea3 = adrArea3;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public Integer getsYear() {
        return sYear;
    }

    public void setsYear(Integer sYear) {
        this.sYear = sYear;
    }

    public Integer getsMonth() {
        return sMonth;
    }

    public void setsMonth(Integer sMonth) {
        this.sMonth = sMonth;
    }

    public Integer geteYear() {
        return eYear;
    }

    public void seteYear(Integer eYear) {
        this.eYear = eYear;
    }

    public Integer geteMonth() {
        return eMonth;
    }

    public void seteMonth(Integer eMonth) {
        this.eMonth = eMonth;
    }
}
