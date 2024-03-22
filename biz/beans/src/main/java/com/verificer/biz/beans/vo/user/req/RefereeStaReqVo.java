package com.verificer.biz.beans.vo.user.req;

import io.swagger.annotations.ApiModel;

@ApiModel
public class RefereeStaReqVo extends RefereeBaseVo {


    private Integer opType;

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }
}
