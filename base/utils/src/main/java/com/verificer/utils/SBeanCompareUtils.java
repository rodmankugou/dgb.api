package com.verificer.utils;

import com.verificer.enums.BooleanEnums;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.cglib.core.ReflectUtils.getBeanProperties;

public class SBeanCompareUtils {
    private static final Log logger = LogFactory.getLog(SBeanCompareUtils.class);
    static class ReflectCache{
        static Map<Class,PropertyDescriptor[]> cache = new ConcurrentHashMap<>();
        static Map<Class,PropertyDescriptor[]> getterCache = new ConcurrentHashMap<>();

        static PropertyDescriptor[] getBeanProperties(Class clazz){
            if(!cache.containsKey(clazz)){
                PropertyDescriptor[] propertyDescriptors = ReflectUtils.getBeanProperties(clazz);
                cache.put(clazz,propertyDescriptors);
            }
            return cache.get(clazz);
        }

        static PropertyDescriptor[] getBeanGetters(Class clazz){

            if(!getterCache.containsKey(clazz)){
                PropertyDescriptor[] propertyDescriptors = ReflectUtils.getBeanGetters(clazz);
                getterCache.put(clazz,propertyDescriptors);
            }
            return getterCache.get(clazz);
        }
    }


    public static boolean isEqual(Object orig,Object dest){
        Map<String, PropertyDescriptor> destProMap = new HashMap();
        Map<String, PropertyDescriptor> origProMap = new HashMap();
        PropertyDescriptor[] propertyDescriptors = ReflectCache.getBeanProperties(dest.getClass());
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            destProMap.put(descriptor.getName(), descriptor);
        }

        PropertyDescriptor[] origDescs = ReflectCache.getBeanProperties(orig.getClass());
        for (PropertyDescriptor descriptor : origDescs) {
            origProMap.put(descriptor.getName(), descriptor);
        }

        for (Map.Entry<String, PropertyDescriptor> destEntry : destProMap.entrySet()) {
            String destProName = destEntry.getKey();
            PropertyDescriptor destProDesc = destEntry.getValue();
            PropertyDescriptor origProDesc = origProMap.get(destProName);
            if (origProDesc == null) {
                return false;
            }

            if (!(destProDesc.getPropertyType().equals(origProDesc.getPropertyType()))) {
                return false;
            }

            if (origProDesc.getReadMethod() == null || destProDesc.getReadMethod() == null) {
                return false;
            }

            try {
                Object origVal = origProDesc.getReadMethod().invoke(orig);
                Object destVal = destProDesc.getReadMethod().invoke(dest);

                boolean isEqual = true;
                if(origVal == null ){
                    if(destVal == null)
                        isEqual = true;
                    else
                        isEqual = false;
                } else {
                    if(destVal == null)
                        isEqual = false;
                    else {
                        if(origProDesc.getPropertyType().equals(BigDecimal.class)){

                            BigDecimal bdValue = (BigDecimal) origVal;
                            isEqual = bdValue.compareTo((BigDecimal) destVal) == 0;

                        }else if(isIntArrayType(origProDesc.getPropertyType())){
                            int[] oar = (int[]) origVal;
                            int[] dar = (int[]) destVal;
                            if(oar == null && dar == null)
                                isEqual = true;
                            else if(oar.length == 0 && dar.length == 0)
                                isEqual = true;
                            else
                                isEqual = SStringUtils.composite(oar,",").equals(SStringUtils.composite(dar,","));
                        } else if(isIntegerArrayType(origProDesc.getPropertyType())){
                            Integer[] oar = (Integer[]) origVal;
                            Integer[] dar = (Integer[]) destVal;
                            if(oar == null && dar == null)
                                isEqual = true;
                            else if(oar.length == 0 && dar.length == 0)
                                isEqual = true;
                            else
                                isEqual = SStringUtils.composite(oar,",").equals(SStringUtils.composite(dar,","));
                        } else {
                            isEqual = origVal.equals(destVal);
                        }
                    }

                }


                if(!isEqual){
//                    logger.error("字段"+destProName+"的值对应不上");
                    return false;
                }
            } catch (Exception e) {
               throw new RuntimeException("比较"+destProName+"字段值时出现异常："+e.getMessage(),e);
            }
        }
        return true;
    }

    static boolean isIntArrayType(Class clazz){
        return clazz.getName().equals("[I");
    }

    static boolean isIntegerArrayType(Class clazz){
        return clazz.getName().equals("[Ljava.lang.Integer;");
    }






}
