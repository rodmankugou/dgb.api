package com.verificer.beans;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
public class AdminHelpCategoryQueryVo implements Serializable {
    private String category; //分类标题
    private String nationalType; //语言环境
    private Integer page;
    private Integer pageSize;
    private Integer from;
    private Integer limit;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNationalType() {
        return nationalType;
    }

    public void setNationalType(String nationalType) {
        this.nationalType = nationalType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
