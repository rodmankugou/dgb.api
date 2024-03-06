package com.verificer.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AccLogOpDirectionEnums {
    IN(I18nCode.ACC_OP_DIRECTION_IN ,1),
    OUT(I18nCode.ACC_OP_DIRECTION_OUT ,2),
    OTHER(I18nCode.ACC_OP_DIRECTION_OTHER ,3);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    AccLogOpDirectionEnums(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AccLogOpDirectionEnums c : AccLogOpDirectionEnums.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (AccLogOpDirectionEnums c : AccLogOpDirectionEnums.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static AccLogOpDirectionEnums get(Integer status) {
        for (AccLogOpDirectionEnums c : AccLogOpDirectionEnums.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
