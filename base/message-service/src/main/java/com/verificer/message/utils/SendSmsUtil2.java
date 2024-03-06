package com.verificer.message.utils;

import com.verificer.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SendSmsUtil2 {
    private static final Logger logger = LoggerFactory.getLogger(SendSmsUtil2.class);

    /**
     * 通过onewassms平台发送
     * @param mobile
     * @param message
     */
    public static boolean sendByOneWaySms(String mobile,String message){
        String apiusername = "API5M7X73FL46";
        String apipassword = "API5M7X73FL465M7X7";
        String senderid = "QMGSA";
        message = "QMDX: " + message;
//        message = message.replace(" ", "%20");
        try {
            message = URLEncoder.encode(message,"utf-8" );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

        try {
            String responseStr = "";
            String sURl = "http://gateway.onewaysms.com.my:10001/api.aspx?apiusername="+apiusername+
                    "&apipassword="+apipassword+
                    "&mobileno="+mobile+
                    "&senderid="+senderid+
                    "&languagetype=1"+
                    "&message="+message;

            String respStr = HttpClientUtils.sendHttpGet(sURl);
            logger.info("OneWaySms send sms api response :" + respStr);


            Double respNum = Double.parseDouble(respStr);
            if(respNum > 0)
                return true;
            else
                return false;

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    public static boolean sendByOneWaySms(String mobile,String message,String charset){
        String apiusername = "API5M7X73FL46";
        String apipassword = "API5M7X73FL465M7X7";
        String senderid = "QMGSA";
        message = "QMDX: " + message;
//        message = message.replace(" ", "%20");
        try {
            message = URLEncoder.encode(message,charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

        try {
            String responseStr = "";
            String sURl = "http://gateway.onewaysms.com.my:10001/api.aspx?apiusername="+apiusername+
                    "&apipassword="+apipassword+
                    "&mobileno="+mobile+
                    "&senderid="+senderid+
                    "&languagetype=2"+
                    "&message="+message;

            String respStr = HttpClientUtils.sendHttpGet(sURl);
            logger.info("OneWaySms send sms api response :" + respStr);


            Double respNum = Double.parseDouble(respStr);
            if(respNum > 0)
                return true;
            else
                return false;

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }


    /**
     * 通过Exabyte平台发送
     * @param mobile
     * @param message
     */
    public static boolean sendByExabyte(String mobile,String message){
        message = "QMDX: " + message;
//        message = message.replace(" ", "%20");
        try {
            message = URLEncoder.encode(message,"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

        String user = "qmgsa"; //user isms username

        String pass = "isQeBg9SzI"; //user isms password

        try {
            int type = 2; //for unicode change to 2, normal will the 1.

            String sendid = "QMGSA"; //Malaysia does not support sender id yet.

            String url = "https://smsportal.exabytes.my/isms_send.php?agreedterm=yes&un="+user+"" +
                    "&pwd="+pass+
                    "&dstno="+mobile+
                    "&msg="+message+"&type=1";


            String respStr = HttpClientUtils.sendHttpGet(url);

            logger.info("Exabyte send sms api response :" + respStr);

            if("2000 = SUCCESS".equals(respStr))
                return true;
            else
                return false;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }




    public static boolean sendNotify(String phone, String content) {
        return sendByOneWaySms(phone,content) || sendByExabyte(phone,content);
    }

    public static void main(String args[]) throws InterruptedException {
        String charsets[] = {"utf-8"};
        for(String charset : charsets){
            boolean flag1 = sendByOneWaySms("+60126663967","\\u4f60\\u597d",charset);
            System.out.println(flag1);
            Thread.sleep(5000);

            boolean flag2 = sendByOneWaySms("+60126663967","\u4f60\u597d",charset);
            System.out.println(flag2);



        }
//        boolean flag = sendByOneWaySms("+60126663967","亲爱的Ives，\r\n\r\n恭喜，您的身份已验证并通过 QMDX KYC 流程。 您现在可以在 QMDX 上进行交易。\r\n\r\n如果您有任何疑问，请随时通过 info@qmdx.exchange 与我们联系。\r\n\r\nQMDX 团队");
    }

}
