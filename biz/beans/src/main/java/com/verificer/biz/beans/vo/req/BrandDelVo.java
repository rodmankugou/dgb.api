package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jnr.ffi.annotations.SaveError;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

@ApiModel
public class BrandDelVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
