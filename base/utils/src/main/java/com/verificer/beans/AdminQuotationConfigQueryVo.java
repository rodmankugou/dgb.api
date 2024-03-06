package com.verificer.beans;

import java.io.Serializable;

/**
 * Created by 35336 on 2021/2/26.
 */
public class AdminQuotationConfigQueryVo implements Serializable
{
    private String symbol;
    private Integer page;
    private Integer pageSize;
    private Integer from;
    private Integer limit;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
