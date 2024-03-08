package com.verificer.biz.beans.exceptions;

import java.util.List;

public class YinBaoApiException extends Exception{
    public YinBaoApiException(String code, List<String> message) {
        super("YinBaoApiErr,code="+code+",message="+message);
    }
}
