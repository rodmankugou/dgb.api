package com.verificer.biz.beans.vo.req;

import java.io.Serializable;

public class StockMerQryVo implements Serializable {
    /**
     * 门店或者店铺ID
     */
    public String relId;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }
}
