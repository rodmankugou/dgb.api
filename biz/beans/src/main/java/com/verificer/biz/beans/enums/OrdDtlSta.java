package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OrdDtlSta {
    WAIT_TRANSIT(1),
    IN_TRANSIT(2),
    RECEIVED(3);


    private int value;


    public int getValue() {
        return value;
    }

    OrdDtlSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OrdDtlSta c : OrdDtlSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static OrdDtlSta get(Integer status) {
        for (OrdDtlSta c : OrdDtlSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
