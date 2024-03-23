package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum EvaluateSta {
    WAIT_REVIEW(1),
    PASS(2),
    REJECT(3);


    private int value;


    public int getValue() {
        return value;
    }

    EvaluateSta(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (EvaluateSta c : EvaluateSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static EvaluateSta get(Integer status) {
        for (EvaluateSta c : EvaluateSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }



}
