package com.verificer.biz.beans.vo.evaluate;

import com.verificer.beans.IdVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@ApiModel
public class EvaluateReviewVo extends IdVo {
    @ApiModelProperty("ture-通过 false-不通过")
    private Boolean passFlag;

    public Boolean getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(Boolean passFlag) {
        this.passFlag = passFlag;
    }
}
