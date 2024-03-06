package com.verificer.message.service;

import com.verificer.message.utils.SendMailUtil;

/**
 * Created by 35336 on 2020/12/24.
 */

public interface EmailService {
    /**
     *
     * @param targetEmail 收件邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return
     */
    public  boolean sendEmail(String targetEmail,String subject, String content);

    public  boolean sendEmail(String targetMail, String msgContent) ;

}
