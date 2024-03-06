package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OrdTaskType {
    OVERSTOCK(1),  //流拍
    CONFIRM(2),  //确认
    REBATE(3);  //分润


    private int value;


    public int getValue() {
        return value;
    }

    OrdTaskType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OrdTaskType c : OrdTaskType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static OrdTaskType get(Integer status) {
        for (OrdTaskType c : OrdTaskType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
