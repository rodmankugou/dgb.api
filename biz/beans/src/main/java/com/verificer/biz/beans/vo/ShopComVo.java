package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ShopComVo implements Serializable {

    @ApiModelProperty("营业执照")
    private String comLicense;

    @ApiModelProperty("公司名称")
    private String comName;

    @ApiModelProperty("统一社会信用码")
    private String comCode;

    @ApiModelProperty("公司所在地")
    private String comAddr;

    @ApiModelProperty("经营范围")
    private String comOpRange;

    @ApiModelProperty("公司法人身份证号码")
    private String comLpIdCardNum;

    public String getComLicense() {
        return comLicense;
    }

    public void setComLicense(String comLicense) {
        this.comLicense = comLicense;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComAddr() {
        return comAddr;
    }

    public void setComAddr(String comAddr) {
        this.comAddr = comAddr;
    }

    public String getComOpRange() {
        return comOpRange;
    }

    public void setComOpRange(String comOpRange) {
        this.comOpRange = comOpRange;
    }

    public String getComLpIdCardNum() {
        return comLpIdCardNum;
    }

    public void setComLpIdCardNum(String comLpIdCardNum) {
        this.comLpIdCardNum = comLpIdCardNum;
    }
}
