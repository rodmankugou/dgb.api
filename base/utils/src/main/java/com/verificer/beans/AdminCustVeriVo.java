package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class AdminCustVeriVo implements Serializable {
    @ApiModelProperty("用户Uid")
    private Long customerId;
    @ApiModelProperty("国家id")
    private Long nationalId;
    @ApiModelProperty("国家名")
    private String nationalName;
    @ApiModelProperty("姓氏")
    private String firstName;
    @ApiModelProperty("名字")
    private String lastName;
    @ApiModelProperty("证件类型 1-护照 2-身份证")
    private Integer idCardType;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户手机号码")
    private String mobile;
    @ApiModelProperty("证件号码")
    private String idCardNum;
    @ApiModelProperty("实名认证状态， 1-审核中 2-审核通过 3-审核驳回 4-Jumio不通过，需人工审核")
    private Integer status;
    @ApiModelProperty("正面照")
    private String idCardFrontPhoto;
    @ApiModelProperty("说明页照")
    private String idCardBackPhoto;
    @ApiModelProperty("手持证件照")
    private String idCardRealPhoto;
    @ApiModelProperty("Jumio备注")
    private String jumioRemark;
    @ApiModelProperty("备注")
    private String rejectReasonMsg;
    @ApiModelProperty("提交时间")
    private Long jumioSubmitTime;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(Integer idCardType) {
        this.idCardType = idCardType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdCardFrontPhoto() {
        return idCardFrontPhoto;
    }

    public void setIdCardFrontPhoto(String idCardFrontPhoto) {
        this.idCardFrontPhoto = idCardFrontPhoto;
    }

    public String getIdCardBackPhoto() {
        return idCardBackPhoto;
    }

    public void setIdCardBackPhoto(String idCardBackPhoto) {
        this.idCardBackPhoto = idCardBackPhoto;
    }

    public String getIdCardRealPhoto() {
        return idCardRealPhoto;
    }

    public void setIdCardRealPhoto(String idCardRealPhoto) {
        this.idCardRealPhoto = idCardRealPhoto;
    }

    public String getJumioRemark() {
        return jumioRemark;
    }

    public void setJumioRemark(String jumioRemark) {
        this.jumioRemark = jumioRemark;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public String getRejectReasonMsg() {
        return rejectReasonMsg;
    }

    public void setRejectReasonMsg(String rejectReasonMsg) {
        this.rejectReasonMsg = rejectReasonMsg;
    }

    public Long getJumioSubmitTime() {
        return jumioSubmitTime;
    }

    public void setJumioSubmitTime(Long jumioSubmitTime) {
        this.jumioSubmitTime = jumioSubmitTime;
    }
}
