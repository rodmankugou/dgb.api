package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AdjStatus {
    WAIT_CONFIRM(1),
    FINISH(2);


    private int value;


    public int getValue() {
        return value;
    }

    AdjStatus(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AdjStatus c : AdjStatus.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AdjStatus get(Integer status) {
        for (AdjStatus c : AdjStatus.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
