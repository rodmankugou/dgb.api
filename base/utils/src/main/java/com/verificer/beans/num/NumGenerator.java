package com.verificer.beans.num;

import com.verificer.utils.SStringUtils;

public abstract class NumGenerator {
    public abstract boolean isNumExist(String num);

    public String genNum(String suffix,int length,Long id){

        while (true){
            String num = genNum0(suffix,length,id);
            if(!isNumExist(num))
                return num;
        }
    }

    private String genNum0(String suffix,int length,Long id){
        String sId = id.toString();
        length = length-suffix.length()-sId.length();
        if(length < 2){
            throw new RuntimeException("Length is too small");
        }
        String randStr = SStringUtils.generateRandomNumSequence(length);
        return new StringBuilder(suffix).append(randStr).append(id).toString();
    }
}
