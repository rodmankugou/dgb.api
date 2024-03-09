package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AdjType {
    STAGE_OUT(1),
    STAGE_IN(2),
    STAGE_SUPPLY(3),
    SHOP_IN(11),
    SHOP_OUT(12);


    private int value;


    public int getValue() {
        return value;
    }

    AdjType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AdjType c : AdjType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AdjType get(Integer status) {
        for (AdjType c : AdjType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
