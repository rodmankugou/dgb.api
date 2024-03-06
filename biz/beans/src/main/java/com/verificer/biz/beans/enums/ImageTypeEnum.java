package com.verificer.biz.beans.enums;

import org.springframework.http.MediaType;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ImageTypeEnum {
    HDFS(1);


    private int value;


    public int getValue() {
        return value;
    }

    ImageTypeEnum(int value) {
        this.value = value;
    }

    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ImageTypeEnum c : ImageTypeEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }



    public static ImageTypeEnum get(Integer status) {
        for (ImageTypeEnum c : ImageTypeEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
