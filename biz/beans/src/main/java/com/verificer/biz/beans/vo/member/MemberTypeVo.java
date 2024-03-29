package com.verificer.biz.beans.vo.member;

import com.verificer.utils.decimal.PriceDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class MemberTypeVo implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("子标题")
    private String subTitle;


    @ApiModelProperty("价格")
    @PriceDecimal
    private BigDecimal price;

    @ApiModelProperty("多少元每天")
    @PriceDecimal
    private BigDecimal pricePerDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
