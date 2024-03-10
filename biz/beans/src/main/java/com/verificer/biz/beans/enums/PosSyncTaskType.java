package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum PosSyncTaskType {
    GOODS_ADD(1),
    GOODS_UPD(2),
    CAT_ADD(11),
    CAT_UPD(12),
    MEMBER_ADD(21),
    MEMBER_UPD(22);


    private int value;


    public int getValue() {
        return value;
    }

    PosSyncTaskType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (PosSyncTaskType c : PosSyncTaskType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static PosSyncTaskType get(Integer status) {
        for (PosSyncTaskType c : PosSyncTaskType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
