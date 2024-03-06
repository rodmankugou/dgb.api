package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ActivityTypeTwoEnums {
    OFFER_OR_BUY(201),
    BID (202),
    SELL (501),
    AUCTION (502);

    private int value;


    public int getValue() {
        return value;
    }

    ActivityTypeTwoEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ActivityTypeTwoEnums c : ActivityTypeTwoEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
