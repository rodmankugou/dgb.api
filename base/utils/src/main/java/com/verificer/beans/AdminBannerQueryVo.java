package com.verificer.beans;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/25.
 */
public class AdminBannerQueryVo implements Serializable {
    private String name;
    private Integer page;
    private Integer pageSize;

    private Integer limit;
    private Integer from;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }
}
