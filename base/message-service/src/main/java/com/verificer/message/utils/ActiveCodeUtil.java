package com.verificer.message.utils;


import com.verificer.ErrCode;
import com.verificer.I18nCode;
import com.verificer.common.exception.BaseException;
import com.verificer.message.service.EmailService;
import com.verificer.message.service.SmsService;
import com.verificer.utils.GenerateUtil;
import com.verificer.utils.RedisUtil;
import com.verificer.utils.check.CheckUtil;
import com.verificer.web.common.i18n.I18nUtils;

/**
 */
public class ActiveCodeUtil {

    private RedisUtil redisUtil;
    private boolean debug = false; //是否调试模式

    private SmsService smsService;

    private EmailService emailService;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    private String buildTempKey(String account) {
        return "DEBUG_KEY_" + account;
    }



    private String buildRegVoiceRetryKey(String account) {
        return "retry_REG_voice_" + account;
    }

    private String buildRetryKey(String scenes,String account) {
        return new StringBuffer("verify:code:retry:").append(scenes).append(":").append(account).toString();
    }

    /***
     * 为满足测试要求，将验证码保存到一个指定的key中，方便测试调用接口获取
     */
    public void storeCodeToCache(String account, Long seconds,String code){
        try {
            String key = buildTempKey(account);
            redisUtil.set(key, code, seconds);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /***
     * 为满足测试要求，将验证码保存到一个指定的key中，方便测试调用接口获取
     */
    public String getDebugCode(String account){
        String key = buildTempKey(account);
        return (String) redisUtil.get(key);
    }


    public String queryUserTestVerifyCode(String account){
        try {
            String code = (String) redisUtil.get(buildTempKey(account));
            return code;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }



    private String buildKey(String scenes,String account){
        return new StringBuffer("verify:code:").append(scenes).append(":").append(account).toString();
    }

    /**
     *
     * @param language 语言
     * @param scenes 场景，如注册/登录
     * @param account 账号
     * @param seconds 验证码有效期
     * @param retryTime 重试时间间隔
     * @return
     */
    public String sendCode(String language,String scenes,String account, Long seconds,Long retryTime) {
        if(!debug){
            if(!canRetryCode(scenes,account)){
                //验证码发送过快
                throw new BaseException(ErrCode.MOBILE_CODE_SEND_FAST);
            }
        }
        String regCode = GenerateUtil.generateNumber(6);
        //System.out.println(account+"的短信验证码为："+regCode);

        String key = buildKey(scenes,account);
        String retryKey = buildRetryKey(scenes,account);
        redisUtil.set(key, regCode, seconds);
        redisUtil.set(retryKey,account,retryTime);

        if(debug){
            storeCodeToCache(account,seconds,regCode);
        }

        boolean flag = false;
        if(CheckUtil.checkEmail(account)){
            String mailContent = I18nUtils.getMessage(I18nCode.EMAIL_VERIFY_CODE_TEMPLATE,new Object[]{regCode},language);
            String mailTitle = I18nUtils.getMessage(I18nCode.EMAIL_VERIFY_CODE_TITLE,null,language);

            flag = emailService.sendEmail(account,mailTitle,mailContent);
        }else if(CheckUtil.checkMobileNumber(account)){
            flag = smsService.sendSms(account,regCode,language);
        }else {
            throw new BaseException(ErrCode.SEND_ACCOUNT_VALUE_IS_ILLEGAL);
        }
        if(false){
            throw new BaseException(ErrCode.SEND_VERIFY_FAILED);
        }
        return regCode;
    }




    /*******其他文字验证码******/

    /**
     * 获取验证码
     * @param scenes  场景，如登录、注册等
     * @param account
     * @return
     */
    public String getCode(String scenes, String account) {
        String code = (String) redisUtil.get(buildKey(scenes,account));
        return code;
    }


    /**
     * 校验验证码
     * @param account 场景，如登录、注册等
     * @param code
     * @return
     */
    public boolean checkCode(String scenes,String account, String code) {
        String exit = getCode(scenes,account);
        if (null != exit && exit.equalsIgnoreCase(code)) {
            return true;
        }

        return false;
    }

    /**
     * 场景，如登录、注册等
     * @param scenes
     * @param account
     * @return
     */
    public boolean canRetryCode(String scenes,String account){
        return !redisUtil.exists(buildRetryKey(scenes,account));
    }

}
