package com.verificer.web.common.enums;

/**
 * Created by yaojunbing on 2018/4/9.
 */
public enum NoticeType {

    NOTICE() {
        @Override
        public String toString() {
            return "公告";
        }
    },
    MEDIA() {
        @Override
        public String toString() {
            return "媒体";
        }
    };
}
