package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/26.
 */
@ApiModel
public class AdminQuotationConfigVo implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("交易对英文缩写")
    private String symbol;

    @ApiModelProperty("是否启用，true or false")
    private Boolean enable;

    @ApiModelProperty("排序参数，值越小优先级越高")
    private Integer sortParam;

    @ApiModelProperty("创建时间")
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getSortParam() {
        return sortParam;
    }

    public void setSortParam(Integer sortParam) {
        this.sortParam = sortParam;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
