package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/1/1.
 */
@ApiModel
public class CustomerVerifiedVo implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户id")
    private Long customerId;
    @ApiModelProperty("地区id")
    private Long nationalId;
    @ApiModelProperty("地区名称")
    private String nationalName;
    @ApiModelProperty("姓氏")
    private String firstName;
    @ApiModelProperty("名字")
    private String lastName;
    @ApiModelProperty("证件号码")
    private String idCardNum;
    @ApiModelProperty("证件正面照")
    private String idCardFrontPhoto;
    @ApiModelProperty("证件信息/背面照")
    private String idCardBackPhoto;
    @ApiModelProperty("本人与证件的合照")
    private String idCardRealPhoto;
    @ApiModelProperty("审核状态  1-审核中 2-审核通过 3-审核驳回")
    private Integer status;
    private String rejectReasonCode;
    @ApiModelProperty("拒绝原因")
    private String rejectReason;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
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

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRejectReasonCode() {
        return rejectReasonCode;
    }

    public void setRejectReasonCode(String rejectReasonCode) {
        this.rejectReasonCode = rejectReasonCode;
    }

    public String getNationalName() {
        return nationalName;
    }

    public void setNationalName(String nationalName) {
        this.nationalName = nationalName;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
