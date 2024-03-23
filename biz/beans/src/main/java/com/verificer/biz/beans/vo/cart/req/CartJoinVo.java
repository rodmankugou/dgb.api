package com.verificer.biz.beans.vo.cart.req;

import com.verificer.utils.decimal.PrcLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


@ApiModel
public class CartJoinVo implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long userId;


    @ApiModelProperty(value = "是否门店",required = true)
    private Boolean shopFlag;

    @ApiModelProperty(value = "门店ID",required = false)
    private String shopId;

    @ApiModelProperty(value = "规格ID",required = true)
    private Long specId;

    @ApiModelProperty(value = "数量",required = true)
    private Integer count;

    public Boolean getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(Boolean shopFlag) {
        this.shopFlag = shopFlag;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
