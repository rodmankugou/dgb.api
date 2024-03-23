package com.verificer.biz.beans.vo.evaluate;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModelProperty;

public class EvaluateQryVo extends PageQueryVo {
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("用户昵称")
    private String userName;
    @ApiModelProperty("搜索时间-开始时间")
    private Long sTime;
    @ApiModelProperty("搜索时间-截止时间")
    private Long eTime;
    @ApiModelProperty("状态 1-待审核 2-通过 3-不通过")
    private Integer status;


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
