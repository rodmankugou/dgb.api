package com.verificer.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.core.util.datetime.FastDateFormat;
import org.web3j.abi.datatypes.Int;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SDateUtil {
    public static final long MS_PER_SEC = 1000;
    public static final long MS_PER_MINUTE = 60*MS_PER_SEC;
    public static final long MS_PER_HOUR = 60*MS_PER_MINUTE;
    public static final long MS_PER_DAY = 24*MS_PER_HOUR;
    public static final long SEC_PER_MINUTE = 60;
    public static final long SEC_PER_HOUR = 60*SEC_PER_MINUTE;
    public static final long SEC_PER_DAY = 24*SEC_PER_HOUR;

    public static final String FM_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static Long NanoSecToMs(Long nanoSec){
        if(nanoSec == null)
            return null;
        return nanoSec/1000000;
    }

    public static Long offsetDate(Long time, Integer days, Integer months, Integer years){
        return offsetDate(new Date(time),days,months,years).getTime();
    }
    // 偏移日期
    public static Date offsetDate(Date date, Integer days, Integer months, Integer years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 设置起时间
        if (days != null) {
            cal.add(Calendar.DATE, days);// 增加一天  
        }
        if (months != null) {
            cal.add(Calendar.MONTH, months);// 增加一个月   
        }
        if (years != null) {
            cal.add(Calendar.YEAR, years);// 增加一年
        }
        return cal.getTime();
    }

    // 转换为"yyyy-MM-dd HH:mm:ss"格式
    public static String formatToTimestampStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Integer getYear(Long time){
        return getYear(new Date(time));
    }
    //获取年份
    public static Integer getYear(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 设置起时间
        return cal.get(Calendar.YEAR);
    }

    public static Integer getNatureMonth(Long time){
        return getNatureMonth(new Date(time));
    }

    //获取月份
    public static Integer getNatureMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 设置起时间
        return cal.get(Calendar.MONTH)+1;
    }

    //一年的总天数
    public static Integer getDaysOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 设置起时间
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    //获取天的日期: yyyyMMdd格式
    public static Integer getDayTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(sdf.format(date));
    }

    public static Date parse_yyyy_MM_dd(String dStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return date;
    }

    public static Date parse(String dStr,String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return date;
    }

    public static Date parse_yyyyMMdd(String dStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(dStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return date;
    }

    public static Date parseZeroDay(String dayTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dayTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取今日0点
    public static Date getTodayStart() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Date beginOfDate = calendar1.getTime();
        return beginOfDate;
    }

    public static long getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    public static long getDayStartTime(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    public static long getDayEndTime(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime();
    }


    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

//    public static void main(String[] args) {
//        int day = differentDaysByMillisecond(getTodayStart(), new Date(1589299200000l));
//        System.out.println(day);
//    }


    /**
     * 获取某个时间所在的k柱的开始时间
     * @param time
     * @param field
     * @return
     */
    public static Date getRangeBeginTime(Long time, int field) {
        Date date = new Date();
        date.setTime(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (field == Calendar.DAY_OF_WEEK) {
            int dayOfWeek =  calendar.get(Calendar.DAY_OF_WEEK);
            Date d = getRangeBeginTime(time,Calendar.DATE);
            return new Date(d.getTime() - (dayOfWeek-1) * 24 * 60 * 60 * 1000);
        } else {
            return DateUtils.truncate(calendar.getTime(), field);
        }
    }

    public static Long getMonthSTime(Long time){
        return getRangeBeginTime(time,Calendar.MONTH).getTime();
    }

    public static Long getNextMonthSTime(Long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.add(Calendar.MONTH,1);
        return getMonthSTime(calendar.getTime().getTime());
    }

    /**
     * 以当天为坐标的之后的第n天
     * @param n 可以为负数
     * @return
     */
    public static Date nextNday(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,n);
        return calendar.getTime();
    }

    /**
     * 日期格式化
     * @param ms 毫秒
     * @return
     */
    public static String format(Long ms){

        return format(ms,null);
    }

    /**
     * 日期格式化
     * @param ms 毫秒
     * @param pattern
     * @return
     */
    public static String format(Long ms, String pattern){

        Date d = new Date(ms);
        return format(d,pattern);
    }



    /**
     * 日期格式化
     * @param format (yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String format(Date date){
        return format(date,null);
    }

    /**
     * 日期格式化
     * @param format (yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String format(Date date, String pattern)
    {
        if ( date == null ){
            return null;
        }

        if ( pattern == null)
        {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        java.text.DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date str2Date(String str, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date date = formatter.parse(str);
            return date;
        } catch (ParseException e) {
            throw new IllegalArgumentException("format false,please check the parameter");
        }
    }

    public static String formatGMT(Long time){
       return FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss z", TimeZone.getTimeZone("GMT"), Locale.ENGLISH).format(time);
    }

    public static Long setYear(Long time,Integer year){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(Calendar.YEAR,year);
        return calendar.getTime().getTime();
    }

    public static Long setNatureMonth(Long time,Integer month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(Calendar.MONTH,month-1);
        return calendar.getTime().getTime();
    }

    public static void main(String arg[]){
//        long dayRange = 24 * 60 * 60 *1000;
//        Long cur = System.currentTimeMillis();
//        System.out.println(getRangeBeginTime(cur,Calendar.DAY_OF_WEEK));
//        System.out.println(getRangeBeginTime(cur + 1 * dayRange,Calendar.DAY_OF_WEEK).getTime());
//        System.out.println(getRangeBeginTime(cur + 2 * dayRange,Calendar.DAY_OF_WEEK));
//        System.out.println(getRangeBeginTime(cur + 3 * dayRange,Calendar.DAY_OF_WEEK));
//        System.out.println(getRangeBeginTime(cur + 4 * dayRange,Calendar.DAY_OF_WEEK));
//        System.out.println(getRangeBeginTime(cur + 5 * dayRange,Calendar.DAY_OF_WEEK));
//        System.out.println(getRangeBeginTime(cur + 6 * dayRange,Calendar.DAY_OF_WEEK));
//        System.out.println(getRangeBeginTime(cur + 7 * dayRange,Calendar.DAY_OF_WEEK));
//        System.out.println(getRangeBeginTime(cur + 8 * dayRange,Calendar.DAY_OF_WEEK));

//        System.out.println(SDateUtil.parse("2022-06-22 00:00:00","yyyy-MM-dd HH:mm:ss").getTime());

//        System.out.println(SDateUtil.format(getMonthSTime(System.currentTimeMillis())));
//        System.out.println(SDateUtil.format(getNextMonthSTime(System.currentTimeMillis())));
        Long now = System.currentTimeMillis();
        now = setYear(now,2023);
        now = setNatureMonth(now,1);
//        now = getMonthSTime(now);
        System.out.println(format(now));
    }




    /**
     * 解释时间，如将4s解释成4000
     * @return
     */
    public static long parseUnit(String timeStr){
        timeStr = timeStr.toLowerCase(Locale.ROOT);
        String valueStr = "";
        long multiple = 1;
        if(timeStr.endsWith("ms")){
            valueStr = timeStr.substring(0,timeStr.length()-2);
            multiple = 1;
        }else if(timeStr.endsWith("ns")){
            valueStr = timeStr.substring(0,timeStr.length()-2);
            Double value = Double.parseDouble(valueStr);
            return new Double(value/1000000).longValue();
        }else if(timeStr.endsWith("s")){
            valueStr = timeStr.substring(0,timeStr.length()-1);
            multiple = MS_PER_SEC;
        }else if(timeStr.endsWith("m")){
            valueStr = timeStr.substring(0,timeStr.length()-1);
            multiple = MS_PER_MINUTE;
        }else if(timeStr.endsWith("h")){
            valueStr = timeStr.substring(0,timeStr.length()-1);
            multiple = MS_PER_HOUR;
        }else if(timeStr.endsWith("d")){
            valueStr = timeStr.substring(0,timeStr.length()-1);
            multiple = MS_PER_DAY;
        }else {
            throw new RuntimeException("Not support Time Unit");
        }
        Double value = Double.parseDouble(valueStr);
        return value.longValue()  * multiple;
    }

}
