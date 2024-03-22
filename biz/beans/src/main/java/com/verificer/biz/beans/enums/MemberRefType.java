package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum MemberRefType {
    SHOP(1),
    USER(2);


    private int value;


    public int getValue() {
        return value;
    }

    MemberRefType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (MemberRefType c : MemberRefType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static MemberRefType get(Integer status) {
        for (MemberRefType c : MemberRefType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
