package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class HelpFormVo implements Serializable {
    @ApiModelProperty(value = "ID,新增时不需要该参数",required = false)
    Long id;

    @ApiModelProperty("标题")
    String title;
    @ApiModelProperty("H5内容")
    String content;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
