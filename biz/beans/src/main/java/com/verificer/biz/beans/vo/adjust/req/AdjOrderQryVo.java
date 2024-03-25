package com.verificer.biz.beans.vo.adjust.req;

import com.verificer.beans.PageQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class AdjOrderQryVo extends PageQueryVo {
    @ApiModelProperty("仓库ID/仓库ID")
    private String merId;

    @ApiModelProperty(hidden = true)
    private List<Integer> types;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }
}
