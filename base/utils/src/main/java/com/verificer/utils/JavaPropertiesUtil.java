package com.verificer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JavaPropertiesUtil {
    public static void main(String[] args) throws IOException {
        System.out.println(getValue("conf/config.properties", "TOKEN"));
    }

    public static String getValue(String fileNamePath, String key) {
        Properties props = new Properties();
        InputStream in = null;

        String var5;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileNamePath);
            props.load(in);
            String value = props.getProperty(key);
            var5 = value;
            return var5;
        } catch (IOException var9) {
            var9.printStackTrace();
            var5 = null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (var5 != null) {
            return var5.trim();
        }
        return var5;
    }
    public static boolean getBoolValue(String fileNamePath, String key) {
        String content = getValue(fileNamePath,key);
        return Boolean.parseBoolean(content);
    }
}
