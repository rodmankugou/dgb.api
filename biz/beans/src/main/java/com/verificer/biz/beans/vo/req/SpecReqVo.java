package com.verificer.biz.beans.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class SpecReqVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;


    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("ID列表")
    private List<Long> stageIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Long> getStageIds() {
        return stageIds;
    }

    public void setStageIds(List<Long> stageIds) {
        this.stageIds = stageIds;
    }
}
