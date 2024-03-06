package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum TaskTypeEnums {
    CONFIRM(1), //拍卖确认
    REBATE (2), //拍卖分润
    CANCEL(3); //拍卖流拍



    private int value;


    public int getValue() {
        return value;
    }

    TaskTypeEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (TaskTypeEnums c : TaskTypeEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
