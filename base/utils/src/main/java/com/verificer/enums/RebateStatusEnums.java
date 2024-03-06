package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum RebateStatusEnums {
    WAITING(0), //待处理
    SUCCESS (1), //处理成功
    FAILED(2); //处理失败



    private int value;


    public int getValue() {
        return value;
    }

    RebateStatusEnums(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (RebateStatusEnums c : RebateStatusEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
