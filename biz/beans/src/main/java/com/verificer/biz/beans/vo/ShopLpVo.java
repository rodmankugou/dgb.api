package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ShopLpVo implements Serializable {

    @ApiModelProperty("负责人/法人身份证号码")
    private String lpIdCardNum;

    @ApiModelProperty("负责人/法人身份证照姓名面。图片url。")
    private String lpIdCardFront;

    @ApiModelProperty("负责人/法人身份证照姓名面。图片url。")
    private String lpIdCardBack;

    @ApiModelProperty("负责人/法人姓名")
    private String lpName;

    @ApiModelProperty("负责人/法人手机号码")
    private String lpMobile;

    public String getLpIdCardNum() {
        return lpIdCardNum;
    }

    public void setLpIdCardNum(String lpIdCardNum) {
        this.lpIdCardNum = lpIdCardNum;
    }

    public String getLpIdCardFront() {
        return lpIdCardFront;
    }

    public void setLpIdCardFront(String lpIdCardFront) {
        this.lpIdCardFront = lpIdCardFront;
    }

    public String getLpIdCardBack() {
        return lpIdCardBack;
    }

    public void setLpIdCardBack(String lpIdCardBack) {
        this.lpIdCardBack = lpIdCardBack;
    }

    public String getLpName() {
        return lpName;
    }

    public void setLpName(String lpName) {
        this.lpName = lpName;
    }

    public String getLpMobile() {
        return lpMobile;
    }

    public void setLpMobile(String lpMobile) {
        this.lpMobile = lpMobile;
    }
}
