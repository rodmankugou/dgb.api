package com.verificer.biz.beans.vo.goods.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum GoodsSortType {
    MUL(1),
    PRICE(2),
    SALES(3),
    MARKET_TIME(4),
    DISTANCE(5);


    private int value;


    public int getValue() {
        return value;
    }

    GoodsSortType(int value) {
        this.value = value;
    }

}
