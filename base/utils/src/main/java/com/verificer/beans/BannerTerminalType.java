package com.verificer.beans;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum BannerTerminalType {
    PC(I18nCode.BANNER_TERMINAL_TYPE_PC,1), //PC网页端
    APP(I18nCode.BANNER_TERMINAL_TYPE_APP,2), //app端
    APP_INVITE(I18nCode.BANNER_TERMINAL_TYPE_APP_INVITE,3), //app活动使用的banner
    ACTIVITY(I18nCode.BANNER_TERMINAL_TYPE_ACTIVITY,4); //活动Banner

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    BannerTerminalType(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (BannerTerminalType c : BannerTerminalType.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (BannerTerminalType c : BannerTerminalType.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static BannerTerminalType get(Integer status) {
        for (BannerTerminalType c : BannerTerminalType.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
