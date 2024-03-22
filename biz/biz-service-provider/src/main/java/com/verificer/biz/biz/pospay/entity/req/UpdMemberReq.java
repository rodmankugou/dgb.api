package com.verificer.biz.biz.pospay.entity.req;

import java.io.Serializable;

public class UpdMemberReq implements Serializable {
    private Long posMemberId;
    private Long expiryTime;

    public Long getPosMemberId() {
        return posMemberId;
    }

    public void setPosMemberId(Long posMemberId) {
        this.posMemberId = posMemberId;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
