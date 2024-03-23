package com.verificer.biz.beans.enums;

/**
 * 评价状态
 */
public enum EvaSta {
    WAIT_REVIEW(1),
    PASS(2),
    REJECT(3);


    private int value;


    public int getValue() {
        return value;
    }

    EvaSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (EvaSta c : EvaSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static EvaSta get(Integer status) {
        for (EvaSta c : EvaSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }



}
