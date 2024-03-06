package com.verificer.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SYamlUtils {

    /**
     * 解释的文件内容
     * @return
     */
    public static Map<String, Object> parseYmlByFile(File file){
        if(file == null)
            throw new IllegalArgumentException("parameter file can not be null!");
        InputStream in = null;
        try{
            Yaml props = new Yaml();
            in = new FileInputStream(file);
            Object obj = props.loadAs(in,Map.class);

            return (Map<String, Object>)obj;
        }catch (Exception e){
            try {
                if(in != null)
                    in.close();
            } catch (IOException ioException) {
            }
            throw new RuntimeException("can not open file "+ file.getPath(),e);
        }
    }

    /**
     * 从yaml配置中获取名为$keyPath的配置项的值,支持多级路径，以逗号'.'隔开
     * @param keyPath
     * @param config
     * @return
     */
    private static String getConfig(String keyPath,Map<String,Object> config){
        if(SStringUtils.isEmpty(keyPath))
            throw new IllegalArgumentException("parameter keyPath can not be null!");
        if(config == null)
            throw new IllegalArgumentException("parameter config can not be null!");

        List<String> paths = SStringUtils.split(keyPath,"\\.");
        Object value = config;
        for(int i = 0 ; i < paths.size(); i++){
            String path = paths.get(i);
            value = ((Map<String, Object>) value).get(path);
            if(i == paths.size()-1){
               //最后一个路径
               if(value != null && value instanceof String)
                   return (String) value;
               else return null;
            }else {
                if(value == null || value instanceof String)
                    return null;
            }
        }
        return null;
    }

    /**
     * 从yaml配置中获取名为$keyPath的配置项的值,支持多级路径，以逗号'.'隔开
     * @param keyPath
     * @param configFile
     * @return
     */
    public static String getConfig(String keyPath,File configFile){
        Map<String,Object> config = parseYmlByFile(configFile);
        return getConfig(keyPath,config);
    }

    public static void main(String args[]){
        System.out.println(getConfig("spring.application.name",new File("/Users/liujinhua/Desktop/application.yml")));
    }
}
