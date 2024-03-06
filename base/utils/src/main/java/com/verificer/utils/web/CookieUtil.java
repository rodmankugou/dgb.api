package com.verificer.utils.web;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtil {
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie[] arr$ = cookies;
            int len$ = cookies.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Cookie cookie = arr$[i$];
                if(cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static void addCurrentContextCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
        String domain = request.getServerName();
        addCookie(response,domain,SecurityConf.COOKIE_PATH,name,value);
    }

    public static void removeCurrentContextCookie(HttpServletRequest request,HttpServletResponse response,String name){
        String domain = request.getServerName();
        addCookie(response, domain, SecurityConf.COOKIE_PATH, name, (String)null, 0);
    }

    public static void addCookie(HttpServletResponse response, String domain, String path, String name, String value) {
        addCookie(response, domain, path, name, value, SecurityConf.LOGIN_TIMEOUT_SECOND);
    }

    public static void addCookie(HttpServletResponse response, String domain, String path, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String domain, String path, String name) {
        //System.out.println("--remove cookies---- ------");
        addCookie(response, domain, path, name, (String)null, 0);
    }

    private CookieUtil() {
    }
}