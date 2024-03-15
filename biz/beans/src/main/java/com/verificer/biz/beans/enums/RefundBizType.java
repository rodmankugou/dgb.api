package com.verificer.biz.beans.enums;

/**
 * 这个表的退款类型设计得尽量精细
 */
public enum RefundBizType {
    SELF_TAKE_ORD_REFUND(1);


    private int value;


    public int getValue() {
        return value;
    }

    RefundBizType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (RefundBizType c : RefundBizType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static RefundBizType get(Integer status) {
        for (RefundBizType c : RefundBizType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
