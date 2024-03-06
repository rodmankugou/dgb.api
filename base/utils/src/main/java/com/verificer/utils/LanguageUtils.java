package com.verificer.utils;


import javax.servlet.http.HttpServletRequest;

public class LanguageUtils {
    public static final String zh_CN = "zh-CN";
    public static final String zh_TW = "zh-TW";
    public static final String en_US = "en-US";

    public static String checkLanguage(String language) {
        if (SStringUtils.isEmpty(language)) {
            language = zh_CN;
        }
        language = language.replace("_", "-");
        return language;
    }

    public static String getLanguage(HttpServletRequest request) {
        return checkLanguage(request.getHeader("language"));
    }
}
