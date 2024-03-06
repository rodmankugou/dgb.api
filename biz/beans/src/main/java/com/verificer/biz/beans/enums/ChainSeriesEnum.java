package com.verificer.biz.beans.enums;

public enum ChainSeriesEnum {
    BITVER("BITVER");


    private String value;


    public String getValue() {
        return value;
    }

    ChainSeriesEnum(String value) {
        this.value = value;
    }

    public static boolean isValidStatus(String status) {
        if (status == null)
            return false;
        for (ChainSeriesEnum c : ChainSeriesEnum.values()) {
            if (c.getValue().equals(status)) {
                return true;
            }
        }
        return false;
    }



    public static ChainSeriesEnum get(Integer status) {
        for (ChainSeriesEnum c : ChainSeriesEnum.values()) {
            if (c.getValue().equals(status)) {
                return c;
            }
        }
        return null;
    }
}
