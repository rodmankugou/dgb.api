package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum MemberOrdSta {
    WAI_PAY(1),
    SUC(2),
    TIME_OUT_CLOSE(3);


    private int value;


    public int getValue() {
        return value;
    }

    MemberOrdSta( int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (MemberOrdSta c : MemberOrdSta.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static MemberOrdSta get(Integer status) {
        for (MemberOrdSta c : MemberOrdSta.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
