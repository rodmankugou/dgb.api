package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OrdOpType {
    Create_Order(1),
    Pay(2),
    Take(3),
    Refund(4),
    Stock_Up(5),
    Transit(6),
    Courier_Deliver(7),
    User_Confirm_Deliver(8),
    User_Evaluate(9),
    Auto_Evaluate(10),
    Supply(11);


    private int value;


    public int getValue() {
        return value;
    }

    OrdOpType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OrdOpType c : OrdOpType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static OrdOpType get(Integer status) {
        for (OrdOpType c : OrdOpType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
