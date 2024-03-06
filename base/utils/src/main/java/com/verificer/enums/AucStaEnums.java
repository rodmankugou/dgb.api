package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AucStaEnums {
    WAITING(1), //待拍卖
    IN_AUCTION (2), //拍卖中
    SUCCESS(3), //拍卖成功
    OVERSTOCKED(4), //流拍成功
    CONFIRMED(6), //拍卖成功并确认
    REBATED(7), //发放安慰奖成功
    CONFIRM_FAILED(16), //拍卖成功并失败
    OVERSTOCKING(24), //流拍
    CONFIRMING(26), //确认中
    REBATING(27), //确认中


    //以下都是被废弃的状态
    CANCELED(-1),
    CANCELING(-1),
    CHAINING(103), //上链中
    CHAIN_FAILED(201); //上链成功


    private int value;


    public int getValue() {
        return value;
    }

    AucStaEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AucStaEnums c : AucStaEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
