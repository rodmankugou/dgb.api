package com.verificer.beans;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum WhiteListStatus {
    WAIT_AUDIT(I18nCode.WHITE_LIST_WAIT_AUDIT,1),
    PASS(I18nCode.WHITE_LIST_PASS,2),
    NOT_PASS(I18nCode.WHITE_LIST_NOT_PASS,3);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    WhiteListStatus(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (WhiteListStatus c : WhiteListStatus.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (WhiteListStatus c : WhiteListStatus.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

}
