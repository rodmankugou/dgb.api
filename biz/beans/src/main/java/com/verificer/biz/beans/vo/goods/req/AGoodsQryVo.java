package com.verificer.biz.beans.vo.goods.req;

import com.verificer.beans.PageQueryVo;
import com.verificer.biz.beans.vo.goods.enums.GoodsSortType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class AGoodsQryVo extends PageQueryVo {
    /**
     * 排序方式
     */
    private Integer sortType;
    /**
     * 是否非会员专区
     */
    private Boolean nonMemberFlag = false;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 分类ID
     */
    private Long catId;

    /**
     * 搜索类型
     */
    private Integer searchType;

    /**
     * 搜索关键词
     */
    private String sKey;

    /**
     * 当前用户是否会员
     */
    private Boolean userMemberFlag;

    private Boolean excludeSaleOutFlag = false;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;


    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Boolean getNonMemberFlag() {
        return nonMemberFlag;
    }

    public void setNonMemberFlag(Boolean nonMemberFlag) {
        this.nonMemberFlag = nonMemberFlag;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Boolean getUserMemberFlag() {
        return userMemberFlag;
    }

    public void setUserMemberFlag(Boolean userMemberFlag) {
        this.userMemberFlag = userMemberFlag;
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

    public Boolean getExcludeSaleOutFlag() {
        return excludeSaleOutFlag;
    }

    public void setExcludeSaleOutFlag(Boolean excludeSaleOutFlag) {
        this.excludeSaleOutFlag = excludeSaleOutFlag;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
