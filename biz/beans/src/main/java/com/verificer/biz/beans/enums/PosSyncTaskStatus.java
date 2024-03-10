package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum PosSyncTaskStatus {
    INIT(1),
    WAIT_RETRY(2),
    SUC(3),
    FAILED(4);


    private int value;


    public int getValue() {
        return value;
    }

    PosSyncTaskStatus(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (PosSyncTaskStatus c : PosSyncTaskStatus.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static PosSyncTaskStatus get(Integer status) {
        for (PosSyncTaskStatus c : PosSyncTaskStatus.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
