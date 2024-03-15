package com.verificer.beans.suportVo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class HelpVo implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("标题")
    private String title;// 名称


    @ApiModelProperty("H5内容")
    private String content;// 图片地址

    @ApiModelProperty("创建时间")
    private Long createTime;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


}
