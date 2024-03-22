package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum MemberTypeTimeUnit {
    YEAR(1),
    MONTH(2),
    DAY(3);


    private int value;


    public int getValue() {
        return value;
    }

    MemberTypeTimeUnit(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (MemberTypeTimeUnit c : MemberTypeTimeUnit.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static MemberTypeTimeUnit get(Integer status) {
        for (MemberTypeTimeUnit c : MemberTypeTimeUnit.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
