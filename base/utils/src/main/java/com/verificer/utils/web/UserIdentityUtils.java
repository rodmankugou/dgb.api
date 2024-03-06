package com.verificer.utils.web;


import com.google.common.base.Throwables;
import com.verificer.beans.UserIdentity ;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class UserIdentityUtils {
    private static final String DATE_PATTERN = "yyyyMMdd HH:mm:ss";

    public static String serialize(UserIdentity userIdentity) {
        StringBuilder sb = new StringBuilder();
        sb.append(userIdentity.getId());

        return BlowfishUtils.encrypt(sb.toString(), SecurityConf.SECRET_KEY);
    }

    public static UserIdentity unserialize(String value) {
        String text = BlowfishUtils.decrypt(value, SecurityConf.SECRET_KEY);
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setId(Long.parseLong(text));
        return userIdentity;
    }

	public static UserIdentity getUserIdentity(HttpServletRequest request) {
        return (UserIdentity) request.getAttribute(SecurityConf.USER_IDENTITY_KEY);
    }

    private static String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        return df.format(date);
    }

    private static Date parseDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw Throwables.propagate(e);
        }
    }

    private UserIdentityUtils() {
    }
}
