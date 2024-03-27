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
    public static final String APP_NAME= JavaPropertiesUtil.getValue("global.properties", "app_name");

    public static final String ENV= JavaPropertiesUtil.getValue("global.properties", "env");
    /**
     * 价格精度
     */
    public static final int PREC= Integer.parseInt(JavaPropertiesUtil.getValue("global.properties", "precision"));
    /**
     * 重量精度
     */
    public static final int W_PREC= Integer.parseInt(JavaPropertiesUtil.getValue("global.properties", "weight_precision"));

    public static final int PERCENT_PREC= Integer.parseInt(JavaPropertiesUtil.getValue("global.properties", "percent_precision"));

    public static final long G_CACHE_REFRESH_PERIOD_MS= Long.parseLong(JavaPropertiesUtil.getValue("global.properties", "g_cache_refresh_period_ms"));

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
