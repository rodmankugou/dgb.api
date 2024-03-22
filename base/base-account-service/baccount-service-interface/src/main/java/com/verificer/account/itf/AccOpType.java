package com.verificer.account.itf;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AccOpType {
    USER_REF_COMMISSION(1),
    USER_REF_WITHDRAW_FROZEN(2),
    USER_REF_WITHDRAW(3),
    INTEGRAL_ADD_BY_ORD_SUC(11);


    private int value;


    public int getValue() {
        return value;
    }

    AccOpType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AccOpType c : AccOpType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AccOpType get(Integer status) {
        for (AccOpType c : AccOpType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }



}
