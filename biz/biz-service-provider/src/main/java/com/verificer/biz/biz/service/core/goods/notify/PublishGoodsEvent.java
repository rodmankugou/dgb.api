package com.verificer.biz.biz.service.core.goods.notify;

import com.verificer.biz.beans.enums.MerType;

public class PublishGoodsEvent {
    private Long goodsId;
    private Long specId;
    private MerType merType;
    private String merId;

    public PublishGoodsEvent(Long goodsId, Long specId, MerType merType, String merId) {
        this.goodsId = goodsId;
        this.specId = specId;
        this.merType = merType;
        this.merId = merId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public MerType getMerType() {
        return merType;
    }

    public String getMerId() {
        return merId;
    }
}
