package com.verificer.biz.biz.pospay.entity;

import java.io.Serializable;
import java.util.List;

public class YbQryOrdResp implements Serializable {
    private YbPostBackParameter postBackParameter;
    private List<YbOrder> result;
    private Integer pageSize;

    public YbPostBackParameter getPostBackParameter() {
        return postBackParameter;
    }

    public void setPostBackParameter(YbPostBackParameter postBackParameter) {
        this.postBackParameter = postBackParameter;
    }

    public List<YbOrder> getResult() {
        return result;
    }

    public void setResult(List<YbOrder> result) {
        this.result = result;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
