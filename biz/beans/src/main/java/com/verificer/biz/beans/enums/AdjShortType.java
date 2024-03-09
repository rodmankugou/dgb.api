package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AdjShortType {
    STAGE_TO_SHOP(1),
    SHOP_TO_STAGE(2),
    STAGE_SUPPLY(3);


    private int value;


    public int getValue() {
        return value;
    }

    AdjShortType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AdjShortType c : AdjShortType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AdjShortType get(Integer status) {
        for (AdjShortType c : AdjShortType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
