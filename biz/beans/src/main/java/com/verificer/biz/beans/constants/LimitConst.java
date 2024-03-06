package com.verificer.biz.beans.constants;


public class LimitConst {
    public static String FORGET_PWD_SEND_CODE_ACTION = "FORGET_PWD_SEND_CODE_ACTION:";
    public static int FORGET_PWD_SEND_CODE_LIMIT_COUNT = 20;

    public static String WITHDRAW_ACTION = "WITHDRAW_ACTION:";
    public static int WITHDRAW_LIMIT_COUNT = 6;
    public static long WITHDRAW_PERIOD = 24*60*60L;

    public static String EXCHANGE_ACTION = "EXCHANGE_ACTION:";
    public static int EXCHANGE_LIMIT_COUNT = 6;
    public static long EXCHANGE_PERIOD = 24*60*60L;

    public static String SETUP_BANK_ACCOUNT_ACTION = "SETUP_BANK_ACCOUNT_ACTION";
    public static int SETUP_BANK_ACCOUNT_LIMIT_COUNT = 6;
    public static long SETUP_BANK_ACCOUNT_PERIOD = 1*60*60L;


}
