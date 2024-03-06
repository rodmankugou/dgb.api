package com.verificer.web.common.enums;

import com.verificer.I18nCode;
import com.verificer.beans.*;

/**
 * Created by Administrator on 2018\6\5 0005.
 */
public enum ClientType {

    PC(I18nCode.CLIENT_TYPE_PC,1) {
        @Override
        public String toString() {
            return "PC端";
        }
    },
    APP(I18nCode.CLIENT_TYPE_APP,2) {
        @Override
        public String toString() {
            return "app端";
        }
    };

    private int value;
    private String code;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    ClientType(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ClientType c : ClientType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (ClientType c : ClientType.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static ClientType get(Integer status) {
        for (ClientType c : ClientType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
