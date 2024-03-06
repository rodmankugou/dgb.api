package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum AcceptStatusEmun {
    WAIT_CONFIM(1), //待确认
    SUCCESS (2), //已成功
    FAILED(3); //失败




    private int value;


    public int getValue() {
        return value;
    }

    AcceptStatusEmun(int value) {
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (AcceptStatusEmun c : AcceptStatusEmun.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }


}
