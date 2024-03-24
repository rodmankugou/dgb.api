package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class GoodsRubbishVo implements Serializable {
    @ApiModelProperty("ID,单个删除时使用该参数")
    private Long id;
    @ApiModelProperty("ID列表，批量删除时使用该参数")
    private List<Long> ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
