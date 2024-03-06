package com.verificer.utils;

import java.util.Random;

public class RandomUtils {
    /**
     * 生成区间范围[min，max]的随机值
     * @param min
     * @param max
     * @return
     */
    public static int getInt(int min,int max){
        Random random = new Random();
        int value = random.nextInt(max-min+1);
        return min+value;
    }



    public static void main(String args[]) throws InterruptedException {
        for(int i = 0 ; i < 100; i ++){
//            Thread.sleep(10);
            System.out.println(getInt(-4,4));
        }
    }
}
