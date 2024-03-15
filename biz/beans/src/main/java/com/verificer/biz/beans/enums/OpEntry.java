package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OpEntry {
    System(1),
    App(2),
    Bo(3),
    Merchant(4),
    Pos(5);



    private int value;


    public int getValue() {
        return value;
    }

    OpEntry(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OpEntry c : OpEntry.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static OpEntry get(Integer status) {
        for (OpEntry c : OpEntry.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
