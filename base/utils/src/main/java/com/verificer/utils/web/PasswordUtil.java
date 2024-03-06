package com.verificer.utils.web;

import com.verificer.utils.MD5Util;

public class PasswordUtil {

    /**
     * 多重加密
     *
     * @param pwd
     * @return
     */
    private static String multipleEncryption(String pwd) {
        return SHAUtil.digest(SHAUtil.digest(MD5Util.digest((SHAUtil.digest(MD5Util.digest(pwd))))));
    }

    /**
     * 交易密码
     *
     * @param pwd
     * @return
     */
    public static String payPassword(String pwd) {
        return multipleEncryption(pwd);
    }

    /***
     * 交易密码
     * @param pwd
     * @return
     */
    public static String loginPassword(String pwd) {
        return SHAUtil.digest(SHAUtil.digest(MD5Util.digest(SHAUtil.digest(MD5Util.digest(pwd)))));
    }


    public static void main(String[] args) {
        System.out.println(loginPassword("Ji6241124"));
    }
}
