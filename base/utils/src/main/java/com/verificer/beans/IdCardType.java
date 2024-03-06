package com.verificer.beans;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum IdCardType {
    PASSPORT(I18nCode.ID_CARD_TYPE_PASSPORT,1),
    ID_CARD(I18nCode.ID_CARD_TYPE_ID_CARD,2);
    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    IdCardType(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        return status != null && !(!status.equals(PASSPORT.getValue()));
    }

    public static String getCode(Integer status) {
        for (IdCardType c : IdCardType.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

}
