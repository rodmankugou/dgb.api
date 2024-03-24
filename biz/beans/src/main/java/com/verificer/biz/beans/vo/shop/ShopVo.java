package com.verificer.biz.beans.vo.shop;

import com.verificer.biz.beans.vo.ShopBkVo;
import com.verificer.biz.beans.vo.ShopComVo;
import com.verificer.biz.beans.vo.ShopLpVo;
import com.verificer.biz.beans.vo.ShopLsVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class ShopVo implements Serializable {
    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("上级店铺ID")
    private Long parentId;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺管理员登录账号")
    private String loginName;

    @ApiModelProperty("店铺管理员登录密码")
    private String loginPwd;

    @ApiModelProperty("店铺头像")
    private String avatarImg;

    @ApiModelProperty("店铺大图")
    private String bigImg;

    @ApiModelProperty("店铺简介")
    private String description;

    @ApiModelProperty("分佣比例")
    private BigDecimal commissionRate;

    @ApiModelProperty("店铺等级，1-一级店铺 2-普通店铺")
    private Integer shopLevel;

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

    @ApiModelProperty("详细地址")
    private String adrDetail;

    @ApiModelProperty("全地址")
    private String fullAddr;

    @ApiModelProperty("联系人姓名")
    private String cpName;

    @ApiModelProperty("联系人电话")
    private String cpTel;

    @ApiModelProperty("联系人手机号码")
    private String cpMobile;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("是否冻结，false-否 ture-是")
    private Boolean frozenFlag;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("运营时间开始时间。毫秒时间戳，如早上8时，则为（8*60*60*1000）")
    private Long opSTime;

    @ApiModelProperty("运营时间结束时间。毫秒时间戳，如早上8时，则为（8*60*60*1000）")
    private Long opETime;


    @ApiModelProperty("公司信息")
    private ShopComVo com;

    @ApiModelProperty("负责人信息")
    private ShopLpVo lp;

    @ApiModelProperty("营业执照信息")
    private ShopLsVo ls;


    @ApiModelProperty("银行信息")
    private ShopBkVo bk;

    @ApiModelProperty("结算银行信息")
    private ShopBkVo sbk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Integer getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(Integer shopLevel) {
        this.shopLevel = shopLevel;
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

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpTel() {
        return cpTel;
    }

    public void setCpTel(String cpTel) {
        this.cpTel = cpTel;
    }

    public String getCpMobile() {
        return cpMobile;
    }

    public void setCpMobile(String cpMobile) {
        this.cpMobile = cpMobile;
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

    public Boolean getFrozenFlag() {
        return frozenFlag;
    }

    public void setFrozenFlag(Boolean frozenFlag) {
        this.frozenFlag = frozenFlag;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getOpSTime() {
        return opSTime;
    }

    public void setOpSTime(Long opSTime) {
        this.opSTime = opSTime;
    }

    public Long getOpETime() {
        return opETime;
    }

    public void setOpETime(Long opETime) {
        this.opETime = opETime;
    }

    public ShopComVo getCom() {
        return com;
    }

    public void setCom(ShopComVo com) {
        this.com = com;
    }

    public ShopLpVo getLp() {
        return lp;
    }

    public void setLp(ShopLpVo lp) {
        this.lp = lp;
    }

    public ShopBkVo getBk() {
        return bk;
    }

    public void setBk(ShopBkVo bk) {
        this.bk = bk;
    }

    public ShopBkVo getSbk() {
        return sbk;
    }

    public void setSbk(ShopBkVo sbk) {
        this.sbk = sbk;
    }

    public ShopLsVo getLs() {
        return ls;
    }

    public void setLs(ShopLsVo ls) {
        this.ls = ls;
    }
}
