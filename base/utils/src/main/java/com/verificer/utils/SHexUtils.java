package com.verificer.utils;

import java.math.BigInteger;

public class SHexUtils {

    public static String toHex(Integer value){
        if(value == null)
            return null;

        return Integer.toHexString(value);
    }

    public static String to0xHex(Integer value){
        if(value == null)
            return null;
        return "0x"+toHex(value);
    }

    public static String toHex(BigInteger value){
        return value.toString(16);
    }

    public static BigInteger toBigInteger(String hex){
        if(hex.startsWith("0x"))
            hex = hex.substring(2);
        return new BigInteger(hex,16);
    }

    public static void main(String args[]){

        System.out.println(to0xHex(11155111));
    }
}
