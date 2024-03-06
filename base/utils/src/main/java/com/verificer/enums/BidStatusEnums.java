package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum BidStatusEnums {
    TOP(1), //为当前的最高报价
    INVALID (2), //被别的报价覆盖
    SUCCESS (3), //成功拍下
    CANCELED (4), //拍卖被取消
    CONFIRMED (5), //成功拍下并确认成功
    CONFIRM_FAILED (6), //拍下但确认不成功
    CHAINING(103), //上链中
    CHAIN_FAILED(201); //上链成功




    private int value;


    public int getValue() {
        return value;
    }

    BidStatusEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (BidStatusEnums c : BidStatusEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
