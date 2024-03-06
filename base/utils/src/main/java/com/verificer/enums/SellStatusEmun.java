package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum SellStatusEmun {
    WAITING(1), //待售卖
    SELLING (2), //售卖中
    SUCCESS(3), //已完成
    OVERSTOCKING(4), //到期仍卖不出去
    CANCELED(5), //卖家取消
    CHAINING(103), //上链中
    CHAIN_FAILED(201); //上链成功



    private int value;


    public int getValue() {
        return value;
    }

    SellStatusEmun(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (SellStatusEmun c : SellStatusEmun.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
