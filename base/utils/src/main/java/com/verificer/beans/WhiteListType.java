package com.verificer.beans;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum WhiteListType {
    FREE_OF_EXCHANGE(I18nCode.WHITE_LIST_TYPE_FREE_OF_EXCHANGE,1), //免手续费
    PROHIBIT_TRANSACTION(I18nCode.WHITE_LIST_TYPE_PROHIBIT_TRANSACTION,2); //禁止交易

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    WhiteListType(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (WhiteListType c : WhiteListType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (WhiteListType c : WhiteListType.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static WhiteListType get(Integer status) {
        for (WhiteListType c : WhiteListType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
