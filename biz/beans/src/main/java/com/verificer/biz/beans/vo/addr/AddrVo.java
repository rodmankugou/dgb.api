package com.verificer.biz.beans.vo.addr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class AddrVo implements Serializable {
    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("省编码")
    private String adrArea1;

    @ApiModelProperty("省名")
    private String adrArea1Name;

    @ApiModelProperty("市编码")
    private String adrArea2;

    @ApiModelProperty("市名")
    private String adrArea2Name;

    @ApiModelProperty("区县编码")
    private String adrArea3;

    @ApiModelProperty("区县名")
    private String adrArea3Name;

    @ApiModelProperty("地址")
    private String adr;

    @ApiModelProperty("详细地址")
    private String adrDetail;


    @ApiModelProperty("全地址")
    private String fullAddr;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("标签，例如家/公司")
    private String tag;

    @ApiModelProperty("联系电话")
    private String mobile;

    @ApiModelProperty("收货人姓名")
    private String rcName;

    @ApiModelProperty("创建名称")
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRcName() {
        return rcName;
    }

    public void setRcName(String rcName) {
        this.rcName = rcName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
