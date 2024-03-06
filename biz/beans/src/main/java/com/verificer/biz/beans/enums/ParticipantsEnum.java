package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ParticipantsEnum {
    BAKN(I18nCode.PARTICIPANTS_BANK,0),   //银行
    OWNER(I18nCode.PARTICIPANTS_OWNER,1),   //发展商
    MAIN_CONTRACTOR(I18nCode.PARTICIPANTS_MAIN_CONTRACTOR,2),  //建筑商
    SUPPLIER(I18nCode.PARTICIPANTS_SUPPLIER,3);  //供应商

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    ParticipantsEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ParticipantsEnum c : ParticipantsEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (ParticipantsEnum c : ParticipantsEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static ParticipantsEnum get(Integer status) {
        for (ParticipantsEnum c : ParticipantsEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
