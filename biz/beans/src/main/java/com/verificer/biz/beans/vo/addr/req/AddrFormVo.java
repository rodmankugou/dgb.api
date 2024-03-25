package com.verificer.biz.beans.vo.addr.req;

import com.verificer.beans.AppReqVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel
public class AddrFormVo extends AppReqVo {
    @ApiModelProperty("Id,修改时不需要传")
    private Long id;

    @ApiModelProperty(value = "省编码",required = true)
    private String adrArea1;


    @ApiModelProperty(value = "市编码",required = true)
    private String adrArea2;



    @ApiModelProperty(value = "区县编码",required = true)
    private String adrArea3;


    @ApiModelProperty(value = "地址",required = true)
    private String adr;

    @ApiModelProperty(value = "详细地址",required = true)
    private String adrDetail;

    @ApiModelProperty(value = "经度",required = true)
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("标签，例如家/公司")
    private String tag;

    @ApiModelProperty("联系电话")
    private String mobile;

    @ApiModelProperty("收货人姓名")
    private String rcName;

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
}
