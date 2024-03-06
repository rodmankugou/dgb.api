package com.verificer.message.service;

import java.util.Map;

/**
 * Created by 35336 on 2021/3/18.
 */
public interface SmsClient {
    String getChannelId();

    /**
     * 发送消息
     * @param mobile
     * @param scene 场景
     * @param language 语言
     * @param params 模板参数值
     */
    boolean sendMsg(String mobile,String scene, String language, Map<String,Object> params);
}
