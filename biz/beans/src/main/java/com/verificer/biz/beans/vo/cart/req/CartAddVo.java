package com.verificer.biz.beans.vo.cart.req;

import com.verificer.biz.beans.vo.AppIdVo;
import com.verificer.utils.decimal.PrcLimit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class CartAddVo extends AppIdVo {
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


}
