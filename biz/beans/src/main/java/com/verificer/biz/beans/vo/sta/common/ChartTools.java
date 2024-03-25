package com.verificer.biz.beans.vo.sta.common;

import com.verificer.utils.RandomUtils;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.reflect.SBeanUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ChartTools {

    public static OneLatChart genOneLat(int sRand, int eRand){
        double seed = 10;
        BigDecimal total = new BigDecimal(RandomUtils.getInt(sRand,eRand));
        BigDecimal rise = new BigDecimal(50).divide(new BigDecimal(100)).setScale(4,RoundingMode.HALF_UP);
        BigDecimal mAvg = total.divide(new BigDecimal(12),0, RoundingMode.HALF_UP);
        List<BigDecimal> data = new LinkedList<>();
        for(int i =0 ;i < 12;i++){
            BigDecimal random = new BigDecimal(RandomUtils.getInt(1,10));
            random = random.divide(new BigDecimal(seed/2),2,RoundingMode.HALF_UP);
            data.add(mAvg.multiply(random).setScale(0,RoundingMode.HALF_UP));
        }



        OneLatChart chart = new OneLatChart(total,rise,genLastNTime(Calendar.MONTH,12),data);
        chart.setAvg(mAvg);
        return chart;
    }

    /**
     *
     * @param size
     * @param unit 值参考 Calendar，例如Calendar.MONTH = 2
     */
    public static List<String> genLastNTime(int unit,int size){
        if(unit == Calendar.DAY_OF_WEEK)
            throw new RuntimeException("不支持DAY_OF_WEEK");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(unit,1-size);
        long now = calendar.getTime().getTime();

        List<String> rst = new LinkedList<>();
        for(int i =0 ; i< size;i++){
            rst.add(formatByUnit(now,unit));
            calendar.add(unit,1);
            now = calendar.getTime().getTime();
        }

        return rst;
    }

    public static String formatByUnit(Long time,int unit){
        if(Calendar.YEAR == unit){
            return SDateUtil.getYear(time).toString();
        }else if(Calendar.MONTH == unit){
            return SDateUtil.getNatureMonth(time)+"月";
        }else if(Calendar.DAY_OF_MONTH == unit){
            return SDateUtil.format(time,"MM-dd");
        }else if(Calendar.HOUR == unit ){
            time = SDateUtil.getRangeBeginTime(time,Calendar.HOUR).getTime();
            return SDateUtil.format(time,"HH:mm");
        }else {
            throw new RuntimeException("不支持的时间单位");
        }
    }


    public static void main(String args[]){
//        System.out.println(genLastNTime(Calendar.MONTH,12));
//        System.out.println(genLastNTime(Calendar.YEAR,10));
//        System.out.println(genLastNTime(Calendar.DAY_OF_MONTH,10));
//        System.out.println(genLastNTime(Calendar.HOUR,24));

        System.out.println(genOneLat(1000000,10000000));
    }
}
