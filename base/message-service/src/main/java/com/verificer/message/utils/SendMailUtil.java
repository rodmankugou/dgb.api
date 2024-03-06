package com.verificer.message.utils;


import com.verificer.common.exception.BaseException;
import com.verificer.utils.SStringUtils;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 *
 */
public class SendMailUtil {

    public static boolean sendEmail(String sendEmail,String subject, String content) {

        if(SStringUtils.isEmpty(content)){
            throw new RuntimeException("parameter message can not be null or empty !");
        }

        // Get a Properties object
        Properties props = new Properties();
        //选择ssl方式
        MailUtil.sslAli(props);
        final String username = "noreply@bitver.com.my";//邮箱
        final String password = "V3r1f1c3R2020";//密码
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        // -- Create a new message --
        MimeMessage msg = new MimeMessage(session);





        try {
//            msg.setFrom(new InternetAddress(username));
            MimeMessageHelper helper = new MimeMessageHelper(msg,true,"utf-8");
            //SimpleMailMessage mailMsg = new SimpleMailMessage();//只支持文字
            helper.setFrom("noreply@bitver.com.my");//发件人
            helper.setTo(InternetAddress.parse(sendEmail));//收件人
            helper.setSubject(subject);//邮件标题
            helper.setText(content,true); //测试内容（html）

//            msg.setFrom(new InternetAddress("noreply@bitver.com.my"));
//            msg.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(sendEmail));
//            msg.setSubject(subject);
//            msg.setText(content);
            msg.setSentDate(new Date());

            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        sendEmail("shiaihua@aliyun.com", "邮箱激活","以下是注册验证码：123456，请勿告知他人");
        sendEmail("353365598@qq.com", "邮箱激活",
                "Dear user,<p></p><p></p>    Your Bitver account has been created,Please click the link below for account activation.<p></p>    <html><a href='http://www.baidu.com' target='_blank'>Account activate</a></html>");
//        sendEmail("353365598@qq.com", "邮箱激活",
//                "激活码为：3424788");
    }
}
