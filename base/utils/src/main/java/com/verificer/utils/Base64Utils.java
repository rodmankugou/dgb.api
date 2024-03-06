package com.verificer.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

public class Base64Utils {
    private static final Base64 BASE64 = new Base64();
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final String PREFIX = "";

    public static String encode(String source) {
        if (!isEmpty(source)) {
            new Base64();
            String target = PREFIX + source;
            byte[] bytes = BASE64.encode(target.getBytes(DEFAULT_CHARSET));
            return new String(bytes, DEFAULT_CHARSET);
        }

        return source;
    }

    public static String decode(String source) {
        if (!isEmpty(source)) {
            byte[] bytes = BASE64.decode(source.getBytes(DEFAULT_CHARSET));
            String target = new String(bytes, DEFAULT_CHARSET);
            return target.startsWith(PREFIX) ? target.substring(PREFIX.length()) : target;
        }

        return source;
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static void main(String[] args) {
        String target = Base64Utils.encode("服务失败,失败信息如下:当前无记录");
        System.out.println("encode : " + target);

        String source = Base64Utils.decode("WFhYWFgg5pyN5Yqh5aSx6LSlLOWksei0peS/oeaBr+WmguS4izrlvZPliY3ml6DorrDlvZU=");
        System.out.println("source : " + source);
    }
}
