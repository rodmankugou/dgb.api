package com.verificer.biz.biz.pospay.entity.req;

import com.verificer.biz.biz.pospay.entity.YbPostBackParameter;

public class QryGoodsReq {
    private YbPostBackParameter postBackParameter;

    public YbPostBackParameter getPostBackParameter() {
        return postBackParameter;
    }

    public void setPostBackParameter(YbPostBackParameter postBackParameter) {
        this.postBackParameter = postBackParameter;
    }
}
