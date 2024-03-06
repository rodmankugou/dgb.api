package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum TransTypeEnums {
    WITHDRAW(I18nCode.TRAN_TYPE_WITHDRAW,1),
    MAIN_REIM(I18nCode.TRAN_TYPE_MAIN_REIM,2),
    SUPP_REIM(I18nCode.TRAN_TYPE_SUPP_REIM,3),
    EXCHANGE(I18nCode.TRAN_TYPE_EXCHANGE,4);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    TransTypeEnums(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (TransTypeEnums c : TransTypeEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (TransTypeEnums c : TransTypeEnums.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static TransTypeEnums get(Integer status) {
        for (TransTypeEnums c : TransTypeEnums.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
