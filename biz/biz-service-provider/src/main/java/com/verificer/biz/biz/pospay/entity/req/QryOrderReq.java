package com.verificer.biz.biz.pospay.entity.req;

import com.verificer.biz.biz.pospay.entity.YbPostBackParameter;

public class QryOrderReq {
    private Long startTime;
    private Long endTime;
    private YbPostBackParameter postBackParameter;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public YbPostBackParameter getPostBackParameter() {
        return postBackParameter;
    }

    public void setPostBackParameter(YbPostBackParameter postBackParameter) {
        this.postBackParameter = postBackParameter;
    }
}
