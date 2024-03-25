package com.verificer.biz.beans.vo.settle.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PlaIncomeLogQryVo extends PageQueryVo {
    @ApiModelProperty("省编码，如广东是44")
    private String adrArea1;
    @ApiModelProperty("市编码")
    private String adrArea2;
    @ApiModelProperty("区编码")
    private String adrArea3;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("true-收入 false-支出")
    private Boolean incomeFlag;

    @ApiModelProperty("查询时间-开始")
    private Long sTime;
    @ApiModelProperty("查询时间-结束")
    private Long eTime;

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

    public Long getsTime() {
        return sTime;
    }

    public void setsTime(Long sTime) {
        this.sTime = sTime;
    }

    public Long geteTime() {
        return eTime;
    }

    public void seteTime(Long eTime) {
        this.eTime = eTime;
    }

    public Boolean getIncomeFlag() {
        return incomeFlag;
    }

    public void setIncomeFlag(Boolean incomeFlag) {
        this.incomeFlag = incomeFlag;
    }


}
