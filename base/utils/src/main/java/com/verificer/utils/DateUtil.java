package com.verificer.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

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

    //获取年份
    public static Integer getYear(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// 设置起时间
        return cal.get(Calendar.YEAR);
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

    public static void main(String arg[]){
        long dayRange = 24 * 60 * 60 *1000;
        Long cur = System.currentTimeMillis();
        System.out.println(getRangeBeginTime(cur,Calendar.DAY_OF_WEEK));
        System.out.println(getRangeBeginTime(cur + 1 * dayRange,Calendar.DAY_OF_WEEK).getTime());
        System.out.println(getRangeBeginTime(cur + 2 * dayRange,Calendar.DAY_OF_WEEK));
        System.out.println(getRangeBeginTime(cur + 3 * dayRange,Calendar.DAY_OF_WEEK));
        System.out.println(getRangeBeginTime(cur + 4 * dayRange,Calendar.DAY_OF_WEEK));
        System.out.println(getRangeBeginTime(cur + 5 * dayRange,Calendar.DAY_OF_WEEK));
        System.out.println(getRangeBeginTime(cur + 6 * dayRange,Calendar.DAY_OF_WEEK));
        System.out.println(getRangeBeginTime(cur + 7 * dayRange,Calendar.DAY_OF_WEEK));
        System.out.println(getRangeBeginTime(cur + 8 * dayRange,Calendar.DAY_OF_WEEK));
    }
}
