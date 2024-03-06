package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;
import org.springframework.http.MediaType;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ImageFormatTypeEnum {
    PNG(MediaType.IMAGE_PNG ,"png",1),
    JPG(MediaType.IMAGE_JPEG ,"jpg",2),
    JPEG(MediaType.IMAGE_JPEG ,"jpeg",3);

    private MediaType contentType;
    private String suffix;
    private int value;


    public int getValue() {
        return value;
    }

    ImageFormatTypeEnum(MediaType contentType, String suffix, int value) {
        this.contentType = contentType;
        this.suffix = suffix;
        this.value = value;
    }

    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ImageFormatTypeEnum c : ImageFormatTypeEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static MediaType getContentType(Integer status) {
        for (ImageFormatTypeEnum c : ImageFormatTypeEnum.values()) {
            if (c.getValue() == status) {
                return c.contentType;
            }
        }
        return null;
    }

    public static String getSuffix(Integer status) {
        for (ImageFormatTypeEnum c : ImageFormatTypeEnum.values()) {
            if (c.getValue() == status) {
                return c.suffix;
            }
        }
        return null;
    }

    public static ImageFormatTypeEnum get(Integer status) {
        for (ImageFormatTypeEnum c : ImageFormatTypeEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
