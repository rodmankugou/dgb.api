package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AddrDelType {
    UPD(1),
    DEL(2);


    private int value;


    public int getValue() {
        return value;
    }

    AddrDelType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AddrDelType c : AddrDelType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AddrDelType get(Integer status) {
        for (AddrDelType c : AddrDelType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
