package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class StageFormVo {
    @ApiModelProperty("ID,新增时不需要该参数")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("联系人姓名")
    private String cpName;

    @ApiModelProperty("联系人手机号码")
    private String cpMobile;

    @ApiModelProperty("省编码")
    private String adrArea1;

    @ApiModelProperty("市编码")
    private String adrArea2;

    @ApiModelProperty("区/县编码")
    private String adrArea3;

    @ApiModelProperty("地址详情")
    private String adrDetail;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpMobile() {
        return cpMobile;
    }

    public void setCpMobile(String cpMobile) {
        this.cpMobile = cpMobile;
    }

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

    public String getAdrDetail() {
        return adrDetail;
    }

    public void setAdrDetail(String adrDetail) {
        this.adrDetail = adrDetail;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
