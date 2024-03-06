package com.verificer.utils;

import java.util.UUID;

public class UuidUtils {
    public static String newUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }


    public static String newUnit256Id(){
        String str = UUID.randomUUID().toString().replace("-","");
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ;i < str.length();i++){
            sb.append(new Integer(str.charAt(i)));
        }
        return sb.toString();
    }

    public static  void main(String args[]){


        System.out.println("115792089237316195423570985008687907853269984665640564039457584007913129639936".length());
        for(int i = 0 ; i < 100 ; i++){
            String str = newUnit256Id();
            System.out.println(str);
            if(str.length() >= 78){
                System.out.println("error:"+str);
                break;
            }

        }
    }
}
