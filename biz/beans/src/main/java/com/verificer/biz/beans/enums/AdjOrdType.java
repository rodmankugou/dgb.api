package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AdjOrdType {
    OTHER_2_STAGE(91),
    OTHER_2_SHOP(92),
    STAGE_2_STAGE(11),
    STAGE_2_SHOP(12),
    SHOP_2_STAGE(21),
    SHOP_2_SHOP(22);


    private int value;


    public int getValue() {
        return value;
    }

    AdjOrdType(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AdjOrdType c : AdjOrdType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static AdjOrdType get(Integer status) {
        for (AdjOrdType c : AdjOrdType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }



}
