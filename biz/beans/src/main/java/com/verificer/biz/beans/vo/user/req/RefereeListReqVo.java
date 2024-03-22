package com.verificer.biz.beans.vo.user.req;

import com.verificer.beans.IdVo;
import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RefereeListReqVo extends RefereeBaseVo {


    private Integer opType;



    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }
}
