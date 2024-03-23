package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AdjOrdSta {
    WAIT_CONFIRM(1),
    PART_CONFIRM(2),
    SUC(3);


    private int value;


    public int getValue() {
        return value;
    }

    AdjOrdSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AdjOrdSta c : AdjOrdSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AdjOrdSta get(Integer status) {
        for (AdjOrdSta c : AdjOrdSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }



}
