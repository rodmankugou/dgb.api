package com.verificer.utils;

public class ThreadUtils {

    public static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
