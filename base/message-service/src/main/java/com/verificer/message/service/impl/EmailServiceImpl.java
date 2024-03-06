package com.verificer.message.service.impl;

import com.verificer.message.service.EmailService;
import com.verificer.message.utils.SendMailUtil;

/**
 * Created by 35336 on 2020/12/24.
 */
public class EmailServiceImpl implements EmailService {
    @Override
    public boolean sendEmail(String targetEmail,String subject, String content) {
        return SendMailUtil.sendEmail(targetEmail,subject,content);
    }

    @Override
    public boolean sendEmail(String targetMail, String msgContent) {
        return SendMailUtil.sendEmail(targetMail,msgContent,"en_US");
    }
}
