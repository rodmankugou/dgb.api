package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AfterSaleSta {
    INIT(1),
    WAIT_REVIEW(2),
    SUC(3),
    REJECT(4);


    private int value;


    public int getValue() {
        return value;
    }

    AfterSaleSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AfterSaleSta c : AfterSaleSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AfterSaleSta get(Integer status) {
        for (AfterSaleSta c : AfterSaleSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
