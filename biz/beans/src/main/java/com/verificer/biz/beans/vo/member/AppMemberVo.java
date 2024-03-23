package com.verificer.biz.beans.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AppMemberVo implements Serializable {

    @ApiModelProperty("是否会员 true-是 false-否")
    private Boolean memberFlag;

    @ApiModelProperty("会员昵称")
    private String nickName;

    @ApiModelProperty("剩余到期天数")
    private Long restDay;

    @ApiModelProperty("会员编号")
    private String num;

    @ApiModelProperty("开始时间")
    private Long sTime;

    @ApiModelProperty("到期时间")
    private Long eTime;

    @ApiModelProperty("绑定的门店名称")
    private String refShopName;

    @ApiModelProperty("店铺二维码")
    private String shopQrCode;

    public Boolean getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(Boolean memberFlag) {
        this.memberFlag = memberFlag;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getRestDay() {
        return restDay;
    }

    public void setRestDay(Long restDay) {
        this.restDay = restDay;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getRefShopName() {
        return refShopName;
    }

    public void setRefShopName(String refShopName) {
        this.refShopName = refShopName;
    }

    public String getShopQrCode() {
        return shopQrCode;
    }

    public void setShopQrCode(String shopQrCode) {
        this.shopQrCode = shopQrCode;
    }
}
