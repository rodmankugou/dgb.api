package com.verificer.biz.beans.vo.adjust;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class TreeAdjItemVo implements Serializable {
    @ApiModelProperty("商品或者规格ID")
    private Long id;
    @ApiModelProperty("商品或者规格ID")
    private String name;
    @ApiModelProperty("发货数量")
    private BigDecimal count;
    @ApiModelProperty("确认收货数量")
    private BigDecimal realCount;

    private List<TreeAdjItemVo> specList;

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

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getRealCount() {
        return realCount;
    }

    public void setRealCount(BigDecimal realCount) {
        this.realCount = realCount;
    }

    public List<TreeAdjItemVo> getSpecList() {
        return specList;
    }

    public void setSpecList(List<TreeAdjItemVo> specList) {
        this.specList = specList;
    }
}
