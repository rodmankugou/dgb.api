package com.verificer.biz.beans.vo.goods.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AGoodsDtlReqVo extends ABaseGoodQryVo{
    @ApiModelProperty("ID")
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
