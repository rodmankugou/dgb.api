package com.verificer.account.provider;

import com.verificer.utils.SStringUtils;

public class BaseAccountUtils {

    public static String getCurrencyName(String subName){
        if(SStringUtils.isEmpty(subName))
            return null;

        int index = subName.indexOf('-');
        if(index > 0)
            return subName.substring(index+1);

        return null;
    }
}
