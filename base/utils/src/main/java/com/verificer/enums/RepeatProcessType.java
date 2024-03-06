package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum RepeatProcessType {
    CANCEL_ABNORMAL(1),
    REBATE(2),
    CONFIRM_AND_REBATE(3),
    REBATE_AFTER_WINNER_CONFIRM(4),
    OVERSTOCK(5);



    private int value;


    public int getValue() {
        return value;
    }

    RepeatProcessType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (RepeatProcessType c : RepeatProcessType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
