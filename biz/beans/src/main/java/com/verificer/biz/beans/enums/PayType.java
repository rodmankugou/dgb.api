package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum PayType {
    WX(1), //微信支付
    POS(2),//POS机
    CASH(3); //现金


    private int value;


    public int getValue() {
        return value;
    }

    PayType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (PayType c : PayType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static PayType get(Integer status) {
        for (PayType c : PayType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
