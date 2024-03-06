package com.verificer.utils;

import com.verificer.web.common.i18n.I18nUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujinhua on 2017/1/13.
 */
public class SEnumUtils {
    private static final Logger LOG = Logger.getLogger(SEnumUtils.class);

    public static <T > List<Map> toList(Class<T> enumClass,boolean enableI18n,String language) {
        Method[] methods = enumClass.getDeclaredMethods();
        List<Map> result = new ArrayList<>();

        for (Object obj : enumClass.getEnumConstants()) {
            //枚举所有的枚举常量
            Map map = new HashMap();
            result.add(map);

            for (Method method : methods) {
                if(Modifier.isStatic(method.getModifiers()))
                    continue;
                //枚举某个枚举常量中的所有字段】
                method.setAccessible(true);
                String methodName = method.getName();
                if (methodName.startsWith("get") && methodName.length() > 3) {
                    String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                    try {
                        if(enableI18n){
                            if(fieldName.equals("code")){
                                map.put("text", I18nUtils.getMessage((String) method.invoke(obj),null,language));
                            }
                            map.put(fieldName, method.invoke(obj));
                        }else {
                            map.put(fieldName, method.invoke(obj));
                        }

                    } catch (Exception e) {
                        map.put(fieldName, null);
                        LOG.error(e.getMessage(), e);
                    }
                }
            }

        }

        return result;
    }

    public static <T extends Enum> String parse(Class<T> enumClass,int value){
        try {
            java.lang.reflect.Field codeField = enumClass.getDeclaredField("value");
            java.lang.reflect.Field nameField = enumClass.getDeclaredField("name");
            codeField.setAccessible(true);
            nameField.setAccessible(true);
            if(codeField == null || nameField == null){
                throw new IllegalStateException("作为第一个参数传入的枚举类型必须包含value和name属性");
            }

            for(Object obj : enumClass.getEnumConstants()){
                int eCode = (Integer)codeField.get(obj);
                if(eCode == value){
                    return (String)nameField.get(obj);
                }
            }

            return "";
        } catch (Exception e) {
            throw new IllegalStateException("将code装换成name时出现异常",e);
        }
    }


    /**
     * 通过值获取enum对象
     * @param enumClass
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Enum> T getByValue(Class<T> enumClass,int value){
        try {
            java.lang.reflect.Field codeField = enumClass.getDeclaredField("value");
            java.lang.reflect.Field nameField = enumClass.getDeclaredField("name");
            codeField.setAccessible(true);
            nameField.setAccessible(true);
            if(codeField == null || nameField == null){
                throw new IllegalStateException("作为第一个参数传入的枚举类型必须包含value和name属性");
            }

            for(T obj : enumClass.getEnumConstants()){
                int eCode = (Integer)codeField.get(obj);
                if(eCode == value){
                    return obj;
                }
            }

            return null;
        } catch (Exception e) {
            throw new IllegalStateException("将code装换成name时出现异常",e);
        }
    }



}
