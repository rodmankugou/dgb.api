package com.verificer.biz.beans.vo.revise.req;

import com.verificer.biz.beans.vo.feature.FeatureSpecReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel
public class ReviseFromVo extends FeatureSpecReq {
    @ApiModelProperty("库存项ID")
    private Long id;
    @ApiModelProperty("数量")
    private BigDecimal count;

    @ApiModelProperty("增/减")
    private Boolean addFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Boolean getAddFlag() {
        return addFlag;
    }

    public void setAddFlag(Boolean addFlag) {
        this.addFlag = addFlag;
    }
}
