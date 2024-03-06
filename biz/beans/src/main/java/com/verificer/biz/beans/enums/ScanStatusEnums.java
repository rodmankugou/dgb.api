package com.verificer.biz.beans.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ScanStatusEnums {
    WAITING(1), //待确认，该状态只会通知一次
    CONFIRM(2), //成功并已确认，该状态只会通知一次
    FAILED (3); //失败，该状态只会通知一次



    private int value;


    public int getValue() {
        return value;
    }

    ScanStatusEnums(int value) {
        this.value = value;
    }





}
