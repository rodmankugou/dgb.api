package com.verificer.beans;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
public class AdminHelpCategoryDetailQueryVo implements Serializable {
    private String title; //标题
    private Integer page;
    private Integer pageSize;
    private Integer from;
    private Integer limit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
