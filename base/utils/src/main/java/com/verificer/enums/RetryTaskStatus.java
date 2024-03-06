package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum RetryTaskStatus {
    //  `status` tinyint DEFAULT NULL COMMENT '0-未成功 1-已成功 2-重试次数已达到但依然未成功',
    UN_SUCCESS(0),
    SUCCESS (1),
    FAILED(2);



    private int value;


    public int getValue() {
        return value;
    }

    RetryTaskStatus(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (RetryTaskStatus c : RetryTaskStatus.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
