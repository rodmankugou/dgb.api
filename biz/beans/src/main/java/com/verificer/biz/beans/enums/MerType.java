package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum MerType {
    STAGE(1),
    SHOP(2);


    private int value;


    public int getValue() {
        return value;
    }

    MerType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (MerType c : MerType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static MerType get(Integer status) {
        for (MerType c : MerType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
