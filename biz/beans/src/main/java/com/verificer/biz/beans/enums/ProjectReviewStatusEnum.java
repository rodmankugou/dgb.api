package com.verificer.biz.beans.enums;

import com.verificer.I18nCode;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum ProjectReviewStatusEnum {
    WAIT_REVIEW(I18nCode.PROJECT_REVIEW_STATUS_WAIT_REVIEW ,1),
    PASS(I18nCode.PROJECT_INTENT_REVIEW_STATUS_PASS ,2),
    REJECT(I18nCode.PROJECT_INTENT_REVIEW_STATUS_REJECT ,3);

    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    ProjectReviewStatusEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }


    public static boolean isValidStatus(Integer status) {
        if (status == null)
            return false;
        for (ProjectReviewStatusEnum c : ProjectReviewStatusEnum.values()) {
            if (c.getValue() == status) {
                return true;
            }
        }
        return false;
    }

    public static String getCode(Integer status) {
        for (ProjectReviewStatusEnum c : ProjectReviewStatusEnum.values()) {
            if (c.getValue() == status) {
                return c.getCode();
            }
        }
        return null;
    }

    public static ProjectReviewStatusEnum get(Integer status) {
        for (ProjectReviewStatusEnum c : ProjectReviewStatusEnum.values()) {
            if (c.getValue() == status) {
                return c;
            }
        }
        return null;
    }
}
