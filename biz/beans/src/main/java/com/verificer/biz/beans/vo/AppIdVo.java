package com.verificer.biz.beans.vo;

import com.verificer.beans.AppReqVo;
import io.swagger.annotations.ApiModel;

@ApiModel
public class AppIdVo extends AppReqVo {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
