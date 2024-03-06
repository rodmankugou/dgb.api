package com.verificer.message.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.verificer.message.SmsConstatns;
import com.verificer.message.service.SmsClient;
import com.verificer.utils.FastJson;
import com.verificer.utils.SStringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 35336 on 2021/3/18.
 */
public class AliyunSmsClient implements SmsClient{
    private String regionId;
    private String domain;
    private String accessKeyId;
    private String secret;
    private String from; //短信头部签名
    private Map<String,String> scenesMap;

    public AliyunSmsClient(String from,String regionId,String domain, String accessKeyId, String secret, Map<String,String> scenesMap) {
        this.from = from;
        this.regionId = regionId;
        this.domain = domain;
        this.accessKeyId = accessKeyId;
        this.secret = secret;
        this.scenesMap = scenesMap;
    }

    @Override
    public String getChannelId() {
        return SmsConstatns.CHANNEL_ALIYUN;
    }



    /**
     * 对带地区号的手机号码进行处理，处理成 1、大陆手机号则不带地区号；2、其他地区，区号+手机号
     * @param phone
     * @return
     */
    private String formatPhoneNum(String phone){
        return phone.substring(1);
    }



    private boolean sendNotify(boolean isTemplate,Map<String,String> param) {

        DefaultProfile profile = DefaultProfile.getProfile(this.regionId, this.accessKeyId, this.secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2018-05-01");
        if(isTemplate) {
            request.setSysAction("SendMessageWithTemplate");
        }else{
            request.setSysAction("SendMessageToGlobe");
        }
        request.putQueryParameter("RegionId", regionId);
        for (Map.Entry<String, String> m :param.entrySet()){
            request.putQueryParameter(m.getKey(),m.getValue());
        }

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }



    @Override
    public boolean sendMsg(String mobile, String scene, String language, Map<String, Object> params) {
        String templateId = scenesMap.get(new StringBuffer().append(scene).append("_").append(language).toString());
        if(SStringUtils.isEmpty(templateId)){
            throw new RuntimeException("Cannot find a suitable templateId, please check the scene and language parameters!");
        }
        Map<String, String> req = new HashMap<String,String>();
        req.put("To", formatPhoneNum(mobile));
        req.put("From", from);
        req.put("TemplateCode", templateId);
        req.put("TemplateParam", FastJson.toJson(params));

        return sendNotify(true,req);
    }

    public static void main(String[] args) throws Exception {

        Map<String,String> scenesMap = new HashMap<>();
        scenesMap.put("verify_code_zh_CN","SMS_10960012");
        AliyunSmsClient util = new AliyunSmsClient("Octopex",
                "cn-hongkong",
                "dysmsapi.ap-southeast-1.aliyuncs.com",
                "LTAI4G6cYRNPHozrrAnwoEgc",
                "OeO6bOr462qsYV5SPQt9OV1Xzy1NWD",
                scenesMap);

        Map<String,Object> obj = new HashMap<>();
        obj.put("code","12456");
        util.sendMsg("+8615920278701","verify_code","zh_CN",obj);
    }

}
