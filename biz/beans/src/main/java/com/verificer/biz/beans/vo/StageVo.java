package com.verificer.biz.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

@ApiModel
public class StageVo {
    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("联系人姓名")
    private String cpName;

    @ApiModelProperty("联系人手机号码")
    private String cpMobile;

    @ApiModelProperty("省编码")
    private String adrArea1;

    @ApiModelProperty("省名")
    private String adrArea1Name;

    @ApiModelProperty("市编码")
    private String adrArea2;

    @ApiModelProperty("市名")
    private String adrArea2Name;

    @ApiModelProperty("区/县编码")
    private String adrArea3;

    @ApiModelProperty("区/县名")
    private String adrArea3Name;

    @ApiModelProperty("地址详情")
    private String adrDetail;

    @ApiModelProperty("全地址")
    private String fullAddr;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("创建时间")
    private Long createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAdrArea1Name() {
        return adrArea1Name;
    }

    public void setAdrArea1Name(String adrArea1Name) {
        this.adrArea1Name = adrArea1Name;
    }

    public String getAdrArea2() {
        return adrArea2;
    }

    public void setAdrArea2(String adrArea2) {
        this.adrArea2 = adrArea2;
    }

    public String getAdrArea2Name() {
        return adrArea2Name;
    }

    public void setAdrArea2Name(String adrArea2Name) {
        this.adrArea2Name = adrArea2Name;
    }

    public String getAdrArea3() {
        return adrArea3;
    }

    public void setAdrArea3(String adrArea3) {
        this.adrArea3 = adrArea3;
    }

    public String getAdrArea3Name() {
        return adrArea3Name;
    }

    public void setAdrArea3Name(String adrArea3Name) {
        this.adrArea3Name = adrArea3Name;
    }

    public String getAdrDetail() {
        return adrDetail;
    }

    public void setAdrDetail(String adrDetail) {
        this.adrDetail = adrDetail;
    }

    public String getFullAddr() {
        return fullAddr;
    }

    public void setFullAddr(String fullAddr) {
        this.fullAddr = fullAddr;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
