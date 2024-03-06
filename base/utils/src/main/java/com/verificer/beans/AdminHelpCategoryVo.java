package com.verificer.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
@ApiModel
public class AdminHelpCategoryVo implements Serializable {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("分类标题")
    private String category;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("是否启用，true or false")
    private Boolean enable;
    @ApiModelProperty("语言环境，用于国际化")
    private String internationalType;
    @ApiModelProperty("排序参数，值越小优先级越高")
    private Integer sortParam;
    @ApiModelProperty("更新时间")
    private Long updateTime;
    @ApiModelProperty("所属的顶级分类，1-帮助中心 2-服务中心 3-条款说明 4-关于我们")
    private Integer helpTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getInternationalType() {
        return internationalType;
    }

    public void setInternationalType(String internationalType) {
        this.internationalType = internationalType;
    }

    public Integer getSortParam() {
        return sortParam;
    }

    public void setSortParam(Integer sortParam) {
        this.sortParam = sortParam;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getHelpTypeId() {
        return helpTypeId;
    }

    public void setHelpTypeId(Integer helpTypeId) {
        this.helpTypeId = helpTypeId;
    }
}
