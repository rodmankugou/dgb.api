package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
@ApiModel
public class AdminHelpCategoryDetailVo implements Serializable {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("是否启用，true or false")
    private Boolean enable;
    @ApiModelProperty("上级分类id")
    private Long helpCategoryId;
    @ApiModelProperty("排序参数，值越小优先级越高")
    private Integer sortParam;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(Long helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
    }

    public Integer getSortParam() {
        return sortParam;
    }

    public void setSortParam(Integer sortParam) {
        this.sortParam = sortParam;
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
