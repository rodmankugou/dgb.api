package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum TranStatusEnums {

    WAIT_REVIEW(I18nCode.TRAN_STATS_WAIT_REVIEW,1),
    PASS(I18nCode.TRAN_STATS_PASS,2),
    REJECT(I18nCode.TRAN_STATS_REJECT,3),
    FINISH(I18nCode.TRAN_STATS_FINISH,4),
    FAILED(I18nCode.TRAN_STATS_FAILED,5);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    TranStatusEnums(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (TranStatusEnums c : TranStatusEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (TranStatusEnums c : TranStatusEnums.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static TranStatusEnums get(Integer status) {
        for (TranStatusEnums c : TranStatusEnums.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
