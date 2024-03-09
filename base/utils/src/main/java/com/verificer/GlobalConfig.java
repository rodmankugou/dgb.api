package com.verificer;

import com.verificer.utils.JavaPropertiesUtil;
import com.verificer.utils.SStringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GlobalConfig {
    private static final Logger logger = LoggerFactory.getLogger(GlobalConfig.class);

    public static final String ENV= JavaPropertiesUtil.getValue("global.properties", "env");
    public static final int PREC= Integer.parseInt(JavaPropertiesUtil.getValue("global.properties", "precision"));

    public static String AES_SEED = null;



    static {
        String AES_SEED_FILE = JavaPropertiesUtil.getValue("global.properties", "AES_SEED_FILE");
        try {
            List<String> lines = FileUtils.readLines(new File(AES_SEED_FILE));
            AES_SEED = lines.get(0).trim();
            if(SStringUtils.isEmpty(AES_SEED))
                throw new RuntimeException("AES_SEED load failed");

        } catch (Exception e) {
            logger.error("加载配置失败，退出系统,错误信息："+e,e);
            System.exit(-1);
        }




    }

    public static void init(){

    }

    public static void main(String args[])  {
        System.out.println(AES_SEED);
    }

}
