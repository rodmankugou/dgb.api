package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ReferrerWithdrawSta {
    WAIT_REVIEW(1),
    REJECT(2),
    PASS(3),
    TRANSFER_FIN(4);


    private int value;


    public int getValue() {
        return value;
    }

    ReferrerWithdrawSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ReferrerWithdrawSta c : ReferrerWithdrawSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static ReferrerWithdrawSta get(Integer status) {
        for (ReferrerWithdrawSta c : ReferrerWithdrawSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }



}
