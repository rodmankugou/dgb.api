package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AdjItemSta {
    WAIT_CONFIRM(1),
    SUC(2);


    private int value;


    public int getValue() {
        return value;
    }

    AdjItemSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AdjItemSta c : AdjItemSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AdjItemSta get(Integer status) {
        for (AdjItemSta c : AdjItemSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }


}
