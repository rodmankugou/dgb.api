package com.verificer.utils.web;

/**
 * @author thinkpad
 */
public class SecurityConf {

    public static String LOGIN_URL = "toLogin/view";

    public static String NEEDSIGN_URL = "/needSign";

    public static String INVALIDATESIGN_URL = "/invalidateSign";
    
    /**
     * 登录处理url
     */
    public static String LOGIN_TIMEOUT_URL = "/needLogin";
    
    /**
     * 登录超时时间,微秒
     */

//    public static int LOGIN_TIMEOUT_SECOND = 30*24*60;
//    public static int SIGN_TIMEOUT_SECOND = 30*24*60;
    public static int LOGIN_TIMEOUT_SECOND = 1 * 60 * 60;
    public static int SIGN_TIMEOUT_SECOND = 1 * 60 * 60;
    public static int ACTIVE_TIMEOUT_SECOND = 20 * 60;

    public static String COOKIE_NAME = "ex_jys_token";

    public static String USER_IDENTITY_KEY = "user_identity_key";

    public static String SECRET_KEY = "@#$%^&FGH100";

    public static String COOKIE_DOMAIN = "localhost";

    public static String COOKIE_PATH = "/";
}
