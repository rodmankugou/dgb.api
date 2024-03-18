package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum StockOpType {
    ADJUST_IN(1),
    ADJUST_OUT(2),
    SUPPLY(3),
    DAMAGED(4),
    ORD_CREATE(5),
    ORD_CANCEL(6),
    ORD_REFUND(7);


    private int value;


    public int getValue() {
        return value;
    }

    StockOpType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (StockOpType c : StockOpType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static StockOpType get(Integer status) {
        for (StockOpType c : StockOpType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
