package com.verificer.biz.beans.vo.goods.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum GoodsSearchType {
    GOODS(1),
    CAT(2);


    private int value;


    public int getValue() {
        return value;
    }

    GoodsSearchType(int value) {
        this.value = value;
    }

}
