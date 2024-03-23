package com.verificer.utils.decimal;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.verificer.GlobalConfig;
import com.verificer.utils.reflect.ClassCache;
import com.verificer.utils.reflect.SBeanUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SBigDecimalUtils {
    private static final ClassCache cache = new ClassCache();


    /**
     * 获取真实精度，如2.100,返回1
     * @param number
     * @return
     */
    public static int getRealScale(String number){
        if(number.contains(".")){
            //去掉无效的精度
            while (number.endsWith("0")){
                number = number.substring(0,number.length()-1);
            }
            if(number.endsWith(".")){
                number = number.substring(0,number.length()-1);
            }
        }
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.scale();
    }

    public static int getRealScale(BigDecimal number){
        return getRealScale(number.toPlainString());
    }

    /**
     * 设置真实的精度，如1.100000的精度将被设置为1(值1.1)
     * @param decimal
     * @return
     */
    public static BigDecimal setRealScale(BigDecimal decimal){
        int realScale = getRealScale(decimal.toPlainString());
        return decimal.setScale(realScale);
    }

    public static String formatPercent(BigDecimal value,int precision){
        if(value == null )
            return null;

        BigDecimal val2 = value.multiply(new BigDecimal(100)).setScale(precision);
        if(val2.compareTo(BigDecimal.ZERO) == 0)
            return "0%";
        else
            return val2.toPlainString()+"%";
    }



    public static <T> T prcFormat2(T obj){
        try {
            return prcFormat(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static <T> T prcFormat(T obj) throws IllegalAccessException {


        if(obj == null)
            return obj;

        if(isMapOrCollection(obj))
            prcFormats(obj);

        //不处理集合元素为BigDecimal类型的集合
        if(SBeanUtils.isNonDecimalSimpleType(obj) || obj instanceof BigDecimal)
            return obj;


        Field[] fields = cache.getFields(obj);
        for(Field field : fields){
            field.setAccessible(true);
            Object val = field.get(obj);
            if(val == null)
                continue;

            if(val instanceof BigDecimal){
                BigDecimal bVal = (BigDecimal) val;
                boolean isHit = false;
                int prec = 2;
                if(field.getAnnotation(PriceDecimal.class) != null){
                    isHit = true;
                    prec = GlobalConfig.PREC;
                }

                if(isHit){
                    BigDecimal nVal = bVal.setScale(prec, RoundingMode.HALF_UP);
//                    BigDecimal intVal = nVal.setScale(0,RoundingMode.UP);
//                    if(nVal.compareTo(intVal) == 0)
//                        nVal = intVal;
                    field.set(obj,nVal);
                }else {
                    field.set(obj,bVal.stripTrailingZeros());
                }

            }else {
                if(SBeanUtils.isNonDecimalSimpleType(val))
                    continue;

                if(isMapOrCollection(val)){
                    prcFormats(val);
                }

            }
        }


        return obj;
    }

    private static boolean isSku(Object obj,Field[] fields) throws IllegalAccessException {
        for(Field field : fields){
            if(field.getName().equals("skuFlag") ){
                if(field.getType().equals(boolean.class)){
                    field.setAccessible(true);
                    return (boolean)field.get(obj);
                }else if(field.getType().equals(Boolean.class)){
                    field.setAccessible(true);
                    return field.get(obj) != null && (Boolean) field.get(obj);
                }else{
                    return false;
                }
            }

        }

        return false;
    }


    private static boolean isMapOrCollection(Object obj){
        return SBeanUtils.isCollections(obj) || SBeanUtils.isMap(obj);
    }

    private static void prcFormats(Object obj) throws IllegalAccessException {
        if(SBeanUtils.isCollections(obj)){
            Collection<?> collection = (Collection<?>) obj;
            Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()){
                prcFormat(iterator.next());
            }
        }else if(SBeanUtils.isMap(obj)){
            Map<?,?> map = new HashMap<>();
            for(Map.Entry entry : map.entrySet())
                prcFormat(entry.getValue());
        }
    }

    static  class Order{
        @CountDecimal
        BigDecimal amount = new BigDecimal("1.23456");
        @PriceDecimal
        BigDecimal count = new BigDecimal("1.23456");
        BigDecimal other = new BigDecimal("1.23456000");

        List<Item> items  = Arrays.asList(new Item[]{new Item(true),new Item(false)});

        @Override
        public String toString() {
            return "Order{" +
                    "amount=" + amount +
                    ", count=" + count +
                    ", other=" + other +
                    ", items=" + items +
                    '}';
        }
    }

    static class Item{
        @CountDecimal
        BigDecimal amount = new BigDecimal("6.000000");
        @PriceDecimal
        BigDecimal count = new BigDecimal("6.54321");
        BigDecimal other = new BigDecimal("6.54321");
        Boolean skuFlag;

        public Item(Boolean isSku) {
            this.skuFlag = isSku;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "amount=" + amount +
                    ", count=" + count +
                    ", other=" + other +
                    ", skuFlag=" + skuFlag +
                    '}';
        }
    }

    public static void main(String args[]) throws IllegalAccessException {
        Order order = new Order();
        System.out.println(order);
        prcFormat(order);
        System.out.println(order);
    }
}
