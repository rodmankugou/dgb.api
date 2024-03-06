package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ChainTaskStatusEnum {
    PENDING(0),
    PROCESSING(1),
    SUCCESS(2),
    FAILED(3);



    private int value;


    public int getValue() {
        return value;
    }

    ChainTaskStatusEnum(int value) {
        this.value = value;
    }

    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ChainTaskStatusEnum c : ChainTaskStatusEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }



    public static ChainTaskStatusEnum get(Integer status) {
        for (ChainTaskStatusEnum c : ChainTaskStatusEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
