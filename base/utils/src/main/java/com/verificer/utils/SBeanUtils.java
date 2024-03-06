package com.verificer.utils;

import org.springframework.cglib.core.ReflectUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by liujinhua on 2017/8/22.
 */
public class SBeanUtils {
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
    
    
    /**
     * Copy property values from the origin bean to the destination bean for all cases where the property names are the same.
     *
     * @param dest
     * @param orig
     */
    public static void copyProperties(Object orig,Object dest) throws Exception {
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

            if(origProDesc == null && isBooleanType(destProDesc.getPropertyType())){
                String proName = !destProName.startsWith("is") ? "is" + SStringUtils.upperCaseFirstChar(destProName) : SStringUtils.lowerCaseFirstChar(destProName.substring(2));
                origProDesc = origProMap.get(proName);
            }
            if (origProDesc == null) {
                continue;
            }

            if (!(destProDesc.getPropertyType().equals(origProDesc.getPropertyType()))) {
                continue;
            }

            Method writeMethod = getWriteMethod(destProDesc,dest.getClass());
            Method readMethod = getReadMethod(origProDesc,orig.getClass());

            writeMethod.invoke(dest,readMethod.invoke(orig));

        }
    }

    private static boolean isBooleanType(Class<?> clazz){
        return clazz.equals(Boolean.class) || clazz.equals(boolean.class);
    }

    private static Method getWriteMethod(PropertyDescriptor pd,Class<?> beanClass){
        if(pd.getWriteMethod() != null)
            return pd.getWriteMethod();
        if(!pd.getPropertyType().equals(Boolean.class) && !pd.getPropertyType().equals(boolean.class)){
            return null;
        }else {
            Method m1 = null;
            Method m2 = null;
            try {
                m1 = beanClass.getMethod("setIs"+pd.getName(),pd.getPropertyType());
            } catch (NoSuchMethodException e) {
            }
            try {
                m2 = beanClass.getMethod("set"+SStringUtils.upperCaseFirstChar(pd.getName()),pd.getPropertyType());
            } catch (NoSuchMethodException e) {
            }

            return m1 != null ? m1 : m2;
        }

    }

    private static Method getReadMethod(PropertyDescriptor pd,Class<?> beanClass){
        if(pd.getReadMethod() != null)
            return pd.getReadMethod();
        if(!pd.getPropertyType().equals(Boolean.class) && !pd.getPropertyType().equals(boolean.class)){
            return null;
        }else {
            Method m1 = null;
            Method m2 = null;
            try {
                m1 = beanClass.getMethod("getIs"+pd.getName());
            } catch (NoSuchMethodException e) {
            }
            try {
                m2 = beanClass.getMethod("get"+pd.getName());
            } catch (NoSuchMethodException e) {
            }

            return m1 != null ? m1 : m2;
        }

    }

    public static void copyProperties2(Object orig, Object dest) {
        try {
            copyProperties(orig, dest);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * @description 从map中copy其中key值与属性名相同的值到bean中
     * @author liujinhua
     * @date 2017/11/2
     **/
    public static void copyPropertiesFromMap( Map orig, Object dest) throws Exception {
        Map<String, PropertyDescriptor> destProMap = new HashMap();
        PropertyDescriptor[] propertyDescriptors = ReflectCache.getBeanGetters(dest.getClass());

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();

            Method destWriteMethod = getWriteMethod(propertyDescriptor,dest.getClass());
            if (destWriteMethod == null) {
                continue;
            }

            if (!orig.containsKey(propertyName) && isBooleanType(propertyDescriptor.getPropertyType())) {
                propertyName = !propertyName.startsWith("is") ? "is" + SStringUtils.upperCaseFirstChar(propertyName) : SStringUtils.lowerCaseFirstChar(propertyName.substring(2));
            }

            if(!orig.containsKey(propertyName)){
                continue;
            }

            Object origValue = orig.get(propertyName);

            if (origValue == null) {
                destWriteMethod.invoke(dest, null);
                continue;
            }

            if (!(propertyDescriptor.getPropertyType().equals(origValue.getClass()))) {
                if(origValue instanceof String){
                    origValue = parseStringValue(propertyName,propertyDescriptor.getPropertyType(),(String) origValue);
                }else {
                    continue;
                }
            }
            destWriteMethod.invoke(dest, origValue);
        }
    }

    /**
     * 将字符串解释成特定类型的值
     * @param propertyName
     * @param propertyType
     * @param sVal
     * @return
     */
    private static Object parseStringValue(String propertyName,Class propertyType,String sVal){
        if(SStringUtils.isEmpty(sVal)){
            return null;
        }
        if (Integer.class.equals(propertyType) || int.class.equals(propertyType)) {
            return Integer.parseInt(sVal);
        } else if (Long.class.equals(propertyType) || long.class.equals(propertyType)) {
            return Long.parseLong(sVal);
        } else if (Double.class.equals(propertyType) || double.class.equals(propertyType)) {
            return Double.parseDouble(sVal);
        } else if (BigDecimal.class.equals(propertyType)) {
            BigDecimal bVal = null;
            if (!sVal.trim().equals("null")) {
                bVal = new BigDecimal(sVal);
            }
            return bVal;
        } else if (String.class.equals(propertyType)) {
            return sVal;
        } else if (Boolean.class.equals(propertyType) || boolean.class.equals(propertyType)) {
            Boolean bVal = null;
            if (sVal.equals("true") || sVal.equals("1")) {
                bVal = true;
            } else if (sVal.equals("false") || sVal.equals("0")) {
                bVal = false;
            } else {
                throw new RuntimeException(propertyName + "字段值，无法解释成boolean类型！");
            }
            return bVal;
        } else {
            throw new RuntimeException("无法解释" + propertyName + "字段，当前不支持对Integer、Long、Double、String、Boolean、BigDecimal之外的其它字段类型！");
        }
    }

    /**
     * @description 从map中copy其中key值与属性名相同的值到bean中
     * @author liujinhua
     * @date 2017/11/2
     **/
    public static void copyPropertiesFromMap2( Map orig,Object dest) {
        try {
            copyPropertiesFromMap(orig,dest);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * @description 将bean转换成LinkHashMap, map中可以的顺序与bean中字段定义的顺序一致
     * @author liujinhua
     * @date 2017/11/1
     **/
    public static LinkedHashMap<String, Object> beanToLinkHashMap(Object bean) throws Exception {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        List<Class<?>> classes = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = bean.getClass();
        while (clazz != Object.class) {
            classes.add(clazz);
            clazz = clazz.getSuperclass();
        }

        for (int i = classes.size() - 1; i >= 0; i--) {
            for (Field field : classes.get(i).getDeclaredFields()) {
                fields.add(field);
            }
        }

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(bean));
        }
        return map;
    }

    /**
     * @description 将bean转换成LinkHashMap, map中可以的顺序与bean中字段定义的顺序一致
     * @author liujinhua
     * @date 2017/11/1
     **/
    public static LinkedHashMap<String, Object> beanToLinkHashMap2(Object bean) {
        try {
            return beanToLinkHashMap(bean);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * @description 获取对象中的属性值
     * @author liujinhua
     * @date 2017/11/2
     **/
    public static Object getFieldValue2(Object bean,String fieldName){
        try {
            return getFieldValue(bean,fieldName);
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * @description 获取对象中的属性值
     * @author liujinhua
     * @date 2017/11/2
     **/
    public static Object getFieldValue(Object bean,String fieldName)throws Exception{
        PropertyDescriptor[] descriptors = ReflectCache.getBeanGetters(bean.getClass());
        for(PropertyDescriptor descriptor : descriptors){
            if(descriptor.getName().equals(fieldName)){
                return descriptor.getReadMethod().invoke(bean);
            }
        }
        return null;
    }


    /**
     * 将某个类中的所有BigDecimal类型的字段转换成String
     * @param orig
     * @return
     * @throws Exception
     */
    public static Map<String,Object> objPrecisionFormat(Object orig,Integer forcePrecision)  throws Exception{
        Map<String, PropertyDescriptor> origProMap = new HashMap();
        Map<String,Object> map = new HashMap();

        PropertyDescriptor[] origDescs = ReflectCache.getBeanProperties(orig.getClass());
        for (PropertyDescriptor descriptor : origDescs) {
            origProMap.put(descriptor.getName(), descriptor);
        }

        for (Map.Entry<String, PropertyDescriptor> origEntry : origProMap.entrySet()) {
            String proName = origEntry.getKey();
            PropertyDescriptor origProDesc = origEntry.getValue();

            if (origProDesc.getReadMethod() == null) {
                continue;
            }

            if(origProDesc.getPropertyType().equals(BigDecimal.class)){
                BigDecimal val = (BigDecimal) origProDesc.getReadMethod().invoke(orig);

                String fVal = null;
                if(val != null){
                    if(val.equals(BigDecimal.ZERO)){
                        fVal = "0";
                    }else {
                        if(forcePrecision != null)
                           val = val.setScale(forcePrecision, RoundingMode.HALF_UP);
                        fVal = val.toPlainString();
                    }
                }
                map.put(proName, fVal);
            }else {
                Object value = origProDesc.getReadMethod().invoke(orig);
//                System.out.println(proName);
                if(isBasicType(origProDesc.getPropertyType())){
                    map.put(proName,value);
                }else if(isList(origProDesc.getPropertyType())){
                    map.put(proName,listPrecisionFormat((List)value, forcePrecision));
                }else {
                    map.put(proName,objPrecisionFormat(value,forcePrecision));
                }
            }
        }
        return map;
    }

    /**
     * 是否不需要进一步处理的数据类型
     * @param clazz
     * @return
     */
    private static boolean isBasicType(Class clazz){
        if(clazz ==  null)
            throw new IllegalArgumentException("parameter clazz can not be null");
        if(clazz.equals(Boolean.class) || clazz.equals(boolean.class)
                || clazz.equals(Character.class) || clazz.equals(char.class)
                    || clazz.equals(Integer.class) || clazz.equals(int.class)
                    || clazz.equals(Long.class) || clazz.equals(long.class)
                    || clazz.equals(Float.class) || clazz.equals(float.class)
                    || clazz.equals(Double.class) || clazz.equals(double.class)
                    || clazz.equals(BigDecimal.class)
                    || clazz.equals(String.class)){
            return true;
        }
        return false;
    }

    public static boolean isList(Class clazz){
        if(clazz ==  null)
            throw new IllegalArgumentException("parameter clazz can not be null");

        if(clazz.equals(List.class))
            return true;

        Class<?>[] interfaces = clazz.getInterfaces();
        for(Class<?> inf : interfaces){
            if(inf.equals(List.class))
                return true;
        }
        return false;
    }

    /**
     * 将某个类中的所有BigDecimal类型的字段转换成String
     * @param orig
     * @return
     * @throws Exception
     */
    public static Map<String,Object> objPrecisionFormat2(Object orig){
        try {
            return objPrecisionFormat(orig,null);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 将某个类中的所有BigDecimal类型的字段转换成String
     * @param orig
     * @param forcePrecision 强制转换精度，如果该值为null，则按照BigDecimal对象的精度来转换
     * @return
     * @throws Exception
     */
    public static Map<String,Object> objPrecisionFormat2(Object orig,Integer forcePrecision){
        if(orig == null)
            return null;
        try {
            return objPrecisionFormat(orig,forcePrecision);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 将列表中的元素中的BigDecimal类型的字段转换成String
     * @param list
     * @param forPrecision 强制转换精度，如果该值为null，则按照BigDecimal对象的精度来转换
     * @return
     * @throws Exception
     */
    public static  List<Object> listPrecisionFormat(List list, Integer forPrecision) throws Exception {
        if(list == null){
            throw new IllegalArgumentException("parameter list can not be null");
        }

        List<Object> result = new LinkedList<>();
        for(Object object : list){
            if(object instanceof List){
                result.add(listPrecisionFormat((List) object,forPrecision));
            }else {
                result.add(objPrecisionFormat(object,forPrecision));
            }
        }
        return result;
    }

    /**
     * 将列表中的元素中的BigDecimal类型的字段转换成String
     * @param list
     * @return
     * @throws Exception
     */
    public static  List<Object> listPrecisionFormat(List list) throws Exception {
        return listPrecisionFormat(list,null);
    }

    /**
     * 将列表中的元素中的BigDecimal类型的字段转换成String
     * @param list
     * @param forPrecision 强制转换精度，如果该值为null，则按照BigDecimal对象的精度来转换
     * @return
     * @throws Exception
     */
    public static  List<Object> listPrecisionFormat2(List list, Integer forPrecision)  {
        try {
            return listPrecisionFormat(list,forPrecision);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 将列表中的元素中的BigDecimal类型的字段转换成String
     * @param list
     * @return
     * @throws Exception
     */
    public static  List<Object> listPrecisionFormat2(List list)  {
        return listPrecisionFormat2(list,null);
    }


    public static class A{
        private Boolean isFlag;

        public Boolean getIsFlag() {
            return isFlag;
        }

        public void setIsFlag(Boolean isFlag) {
            this.isFlag = isFlag;
        }
    }

    public static class B{
        private Boolean isFlag;

        public Boolean getFlag() {
            return isFlag;
        }

        public void setFlag(Boolean flag) {
            isFlag = flag;
        }
    }

    public static void main(String args[]){
//        A a = new A();
//        a.setIsFlag(false);
//        B b = new B();
//        SBeanUtils.copyProperties2(a,b);
//        System.out.println(b.getFlag());
//        b.setFlag(true);
//
//        SBeanUtils.copyProperties2(b,a);
//        System.out.println(a.getIsFlag());

        A a2 = new A();
        B b2 = new B();
        Map map = new HashMap();
        map.put("isFlag",new Boolean(true));
        SBeanUtils.copyPropertiesFromMap2(map,a2);
        SBeanUtils.copyPropertiesFromMap2(map,b2);
        System.out.println("A2:"+a2.getIsFlag()+",B2:"+b2.getFlag());
    }
}
