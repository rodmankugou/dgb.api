package com.verificer.biz.beans.vo.cart.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class CartAddVo implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty(value = "ID",required = true)
    private Long id;
    @ApiModelProperty(value = "增减数量，可以为负数",required = true)
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
