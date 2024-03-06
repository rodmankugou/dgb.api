package com.verificer.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;


public class FastJson {
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json,clazz);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json,clazz);
    }
    /**
     * po类转换成json String
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        String result= JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return result;
    }

    public static <K,V> Map<K,V> parseMap(String json,Class<K> kClass, Class<V> vClass){
        return (Map<K, V>) JSON.parseObject(json, new TypeReference<Map<K, V>>() {});
    }



}
