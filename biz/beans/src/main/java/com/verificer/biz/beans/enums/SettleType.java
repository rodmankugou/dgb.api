package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum SettleType {
    SHOP_MEMBER(1);


    private int value;


    public int getValue() {
        return value;
    }

    SettleType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (SettleType c : SettleType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static SettleType get(Integer status) {
        for (SettleType c : SettleType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
