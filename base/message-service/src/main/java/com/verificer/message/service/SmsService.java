package com.verificer.message.service;

/**
 * Created by 35336 on 2020/12/24.
 */
public interface SmsService {
    public boolean sendSms(String phone, String code, String language) ;
    public boolean sendSms(String phone, String msgContent) ;

}
