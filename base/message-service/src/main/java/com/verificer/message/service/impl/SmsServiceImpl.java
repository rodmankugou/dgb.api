package com.verificer.message.service.impl;

import com.verificer.message.service.SmsService;
import com.verificer.message.utils.SendSmsUtil2;

/**
 * Created by 35336 on 2020/12/24.
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public boolean sendSms(String phone, String code, String language) {
        String content = getSmsTemplate("0",code,language);

        return SendSmsUtil2.sendNotify(phone,content);
    }

    @Override
    public boolean sendSms(String phone, String msgContent) {
        return SendSmsUtil2.sendNotify(phone,msgContent);
    }

    public static String getSmsTemplate(String type, String code,String language) {
        String str = "";

        if("en_US".equals(language)){
            if ("1".equals(type)) {
                str = "Your registration OTP is " + code + ". Please do not reveal it to others.";
            } else if ("2".equals(type)) {
                str = "Your password reset OTP is " + code + ". Please do not reveal it to others.";
            } else if ("3".equals(type)) {
                str = "Your password change OTP is " + code + ". Please do not reveal it to others.";
            } else if ("4".equals(type)) {
                str = "Your Google 2FA OTP is " + code + ". Please do not reveal it to others.";
            } else {
                str = "Your OTP is " + code + ". Please do not reveal it to others.";
            }
//            if ("1".equals(type)) {
//                str = "[VERIFICER]Your registration OTP is " + code + ". Please do not reveal it to others.";
//            } else if ("2".equals(type)) {
//                str = "[VERIFICER]Your password reset OTP is " + code + ". Please do not reveal it to others.";
//            } else if ("3".equals(type)) {
//                str = "[VERIFICER]Your password change OTP is " + code + ". Please do not reveal it to others.";
//            } else if ("4".equals(type)) {
//                str = "[VERIFICER]Your Google 2FA OTP is " + code + ". Please do not reveal it to others.";
//            } else {
//                str = "[VERIFICER]Your OTP is " + code + ". Please do not reveal it to others.";
//            }
        }else if("zh_TW".equals(language)){
            if ("1".equals(type)) {
                str = "【HEX】你的注冊驗證碼是" + code + "，請不要把驗證碼泄露給其他人，如非本人請勿操作。";
            } else if ("2".equals(type)) {
                str = "【HEX】您正在重置登錄密碼，验证码" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
            } else if ("3".equals(type)) {
                str = "【HEX】您在在設置交易密码，验证码" + code + "，請不要把驗證碼泄露給其他人，如非本人請勿操作。";
            } else if ("4".equals(type)) {
                str = "【HEX】您正在綁定谷歌驗證器，验证码" + code + "，請不要把驗證碼泄露給其他人，如非本人請勿操作。";
            } else {
                str = "【HEX】您的驗證碼是" + code + "若非本人操作請忽略此訊息。。";
            }
//            if ("1".equals(type)) {
//                str = "[VERIFICER]Your registration OTP is " + code + ". Please do not reveal it to others.";
//            } else if ("2".equals(type)) {
//                str = "[VERIFICER]Your password reset OTP is " + code + ". Please do not reveal it to others.";
//            } else if ("3".equals(type)) {
//                str = "[VERIFICER]Your password change OTP is " + code + ". Please do not reveal it to others.";
//            } else if ("4".equals(type)) {
//                str = "[VERIFICER]Your Google 2FA OTP is " + code + ". Please do not reveal it to others.";
//            } else {
//                str = "[VERIFICER]Your OTP is " + code + ". Please do not reveal it to others.";
//            }
        }else{
            if ("1".equals(type)) {
                str = "【HEX】您的注册验证码是" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
            } else if ("2".equals(type)) {
                str = "【HEX】您正在重置登录密码，验证码" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
            } else if ("3".equals(type)) {
                str = "【HEX】您正在设置交易密码，验证码" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
            } else if ("4".equals(type)) {
                str = "【HEX】您正在绑定谷歌验证器，验证码" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
            } else {
                str = "【HEX】您的验证码是" + code + "若非本人操作请忽略此消息。";
            }
//            if ("1".equals(type)) {
//                str = "【VERIFICER】您的注册验证码是" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
//            } else if ("2".equals(type)) {
//                str = "【VERIFICER】您正在重置登录密码，验证码" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
//            } else if ("3".equals(type)) {
//                str = "【VERIFICER】您正在设置交易密码，验证码" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
//            } else if ("4".equals(type)) {
//                str = "【VERIFICER】您正在绑定谷歌验证器，验证码" + code + "，请不要把验证码泄漏给其他人，如非本人请勿操作。";
//            } else {
//                str = "【VERIFICER】您的验证码是" + code + "若非本人操作请忽略此消息。";
//            }
        }
        return str;
    }
}
