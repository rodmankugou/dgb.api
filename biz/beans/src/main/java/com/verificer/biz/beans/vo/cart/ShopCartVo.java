package com.verificer.biz.beans.vo.cart;

import java.io.Serializable;
import java.util.List;

public class ShopCartVo implements Serializable {
    private String shopId;
    private String shopName;
    private List<CartVo> items;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<CartVo> getItems() {
        return items;
    }

    public void setItems(List<CartVo> items) {
        this.items = items;
    }
}
