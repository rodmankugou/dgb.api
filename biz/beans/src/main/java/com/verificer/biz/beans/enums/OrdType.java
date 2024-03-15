package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OrdType {
    POS(1), //线下收银
    SELF_TAKE(2),//门店自提
    STAGE(3), //平台配送
    REISSUE(4); //补发单


    private int value;


    public int getValue() {
        return value;
    }

    OrdType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OrdType c : OrdType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static OrdType get(Integer status) {
        for (OrdType c : OrdType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
