package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum OrdTaskStaEnum {
    ING(0),  //处理中
    SUC(1),  //成功
    FAILED(2);  //失败


    private int value;


    public int getValue() {
        return value;
    }

    OrdTaskStaEnum( int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (OrdTaskStaEnum c : OrdTaskStaEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


    public static OrdTaskStaEnum get(Integer status) {
        for (OrdTaskStaEnum c : OrdTaskStaEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
