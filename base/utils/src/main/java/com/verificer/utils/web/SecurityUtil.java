package com.verificer.utils.web;

import com.verificer.beans.UserIdentity ;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 当前登陆用户
 */
public class SecurityUtil {

    /**
     * 获取登陆用户
     */
    public static UserIdentity currentLogin() {
        try {

            UserIdentity userIdentity = UserIdentityUtils.getUserIdentity(getRequest());

            HttpServletRequest request = getRequest();
            return userIdentity;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取token
     */
    public static String getToken() {
        try {
            return getRequest().getHeader("token");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取client
     */
    public static String getClient() {
        try {
            return getRequest().getHeader("veri-ex-client");
        } catch (Exception e) {
            return null;
        }
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return attrs.getRequest();
    }


}
