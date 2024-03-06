package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ActivityTypeThreeEnums {
    OFFER(1),
    BUY (2);

    private int value;


    public int getValue() {
        return value;
    }

    ActivityTypeThreeEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ActivityTypeThreeEnums c : ActivityTypeThreeEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
