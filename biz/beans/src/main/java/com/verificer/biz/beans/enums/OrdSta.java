package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OrdSta {
    WAIT_PAY(1),
    InStock(2),
    WaitTransit(3),
    InTransit(4),
    Received(5),
    Evaluated(6),
    Finish(7),
    WaitSelfTake(22),
    Canceled(101),
    Refunded(102);


    private int value;


    public int getValue() {
        return value;
    }

    OrdSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OrdSta c : OrdSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static OrdSta get(Integer status) {
        for (OrdSta c : OrdSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}