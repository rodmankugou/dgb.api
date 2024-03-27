package com.verificer.biz.biz.service.cache.gache.sort.impl.mult;

import java.io.Serializable;

public class MulParam implements Serializable {
    long price = 0;
    long sale = 0;
    long distance = 0;
    long shop = 0;

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getShop() {
        return shop;
    }

    public void setShop(long shop) {
        this.shop = shop;
    }
}
