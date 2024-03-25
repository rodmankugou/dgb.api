package com.verificer.beans;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/28.
 */
public class AppPageQueryVo extends AppReqVo{
    private static final int DEFAULT_PAGE_SIZE = 10;
    private Integer page;
    private Integer pageSize;
    @ApiModelProperty(hidden = true)
    private Integer from;
    @ApiModelProperty(hidden = true)
    private Integer limit;

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
        int _page = page == null ? 1 : page;
        int _pageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize ;
        return _pageSize * (_page - 1) ;
    }


    public Integer getLimit() {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize ;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
