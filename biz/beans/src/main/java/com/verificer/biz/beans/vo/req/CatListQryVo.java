package com.verificer.biz.beans.vo.req;

import com.verificer.beans.PageQueryVo;

import java.io.Serializable;

public class CatListQryVo implements Serializable {

    private String shopId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
