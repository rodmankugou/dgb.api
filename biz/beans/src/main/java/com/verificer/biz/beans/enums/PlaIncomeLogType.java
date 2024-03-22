package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum PlaIncomeLogType {
    MEMBER_REGISTER(1),
    SHOP_SETTLE(2);


    private int value;


    public int getValue() {
        return value;
    }

    PlaIncomeLogType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (PlaIncomeLogType c : PlaIncomeLogType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static PlaIncomeLogType get(Integer status) {
        for (PlaIncomeLogType c : PlaIncomeLogType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }



}
