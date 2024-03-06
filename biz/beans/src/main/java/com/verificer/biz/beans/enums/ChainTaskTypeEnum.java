package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ChainTaskTypeEnum {
    KYC_SUBMIT(2),
    KYC_AUDIT(3),
    ISSUE_COIN(4),
    TRANSFER(5),
    ADD_ISSUE_COIN(6),
    REIM_SUBMIT(7),
    REIM_AUDIT(8),
    CHANGE_HDFS_FILE_PERMISSION(9);


    private int value;


    public int getValue() {
        return value;
    }

    ChainTaskTypeEnum(int value) {
        this.value = value;
    }

    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ChainTaskTypeEnum c : ChainTaskTypeEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }



    public static ChainTaskTypeEnum get(Integer status) {
        for (ChainTaskTypeEnum c : ChainTaskTypeEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
