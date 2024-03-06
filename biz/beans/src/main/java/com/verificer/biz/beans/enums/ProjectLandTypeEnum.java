package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * 项目土地类型枚举
 * Created by 35336 on 2021/1/4.
 */
public enum ProjectLandTypeEnum {
    RESIDENTIAL(I18nCode.PROJECT_LAND_TYPE_RESIDENTIAL ,1),
    COMMERCIAL(I18nCode.PROJECT_LAND_TYPE_COMMERCIAL ,2),
    OFFICE(I18nCode.PROJECT_LAND_TYPE_OFFICE ,3),
    FACTORY(I18nCode.PROJECT_LAND_TYPE_FACTORY ,4);
    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    ProjectLandTypeEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ProjectLandTypeEnum c : ProjectLandTypeEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (ProjectLandTypeEnum c : ProjectLandTypeEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static ProjectLandTypeEnum get(Integer status) {
        for (ProjectLandTypeEnum c : ProjectLandTypeEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
