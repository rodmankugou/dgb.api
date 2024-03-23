package com.verificer.biz.beans.vo.feature;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@ApiModel
public class FeatureSpecReq implements Serializable {
    @ApiModelProperty("规格ID")
    private Long specId;

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }
}
