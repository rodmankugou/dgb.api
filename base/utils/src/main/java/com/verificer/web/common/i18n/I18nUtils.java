package com.verificer.web.common.i18n;

import com.verificer.UtilConstants;
import com.verificer.utils.ThreadLocalUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by 35336 on 2020/12/24.
 */
public class I18nUtils {
    private static final Log logger = LogFactory.getLog(I18nUtils.class);

    private static ReloadableResourceBundleMessageSource msgSource = null;
    private static final String PREFFIX = "classpath:";
    private static final String DIR = "i18n";

    static {
        msgSource = new ReloadableResourceBundleMessageSource();
        msgSource.setDefaultEncoding("UTF-8");
        msgSource.setUseCodeAsDefaultMessage(true);
        msgSource.setCacheSeconds(60);

        List<String> fileNames = new LinkedList<>();
        URL url =  ClassLoader.getSystemClassLoader().getResource("");
        if(url != null){
            //本地ide运行环境下遍历classpath:i18n/目录下的资源文件
            String path = url.getPath();
            File parent = new File(path+DIR);
            if(parent != null){
                File[] files = parent.listFiles();
                if(files != null){
                    for(File file : files){
                        fileNames.add(file.getName());
                    }
                }
            }
        }else {
            //spring-boot的jar包运行方式下遍历classpath:i18n/目录下的资源文件
            Resource[]  resources= null;
            try {
                resources = new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + DIR +  "/*.properties");
            } catch (IOException e) {
                logger.error("加载国际化资源失败........");
            }

            if(resources != null){
                for(Resource res : resources){
                    fileNames.add(res.getFilename());
                }
            }
        }


        List<String> baseNames = new ArrayList<String>();
        Map<String,Object> baseNameMap = new HashMap();

        for (String fileName : fileNames) {
            String baseName = getI18nFileBaseName(fileName);
            if(baseName != null){
                baseName = PREFFIX + DIR + "/" + baseName;
                if(!baseNameMap.containsKey(baseName)){
                    baseNames.add(baseName);
                    baseNameMap.put(baseName,new Object());
                }
            }
        }

        String[] names = new String[baseNames.size()];
        for(int i = 0; i < baseNames.size(); i++){
            names[i] = baseNames.get(i);
        }
        msgSource.setBasenames(names);
    }

    /**
     * 根据code获取当前请求所属的语言环境下对应的文本信息
     * @param code
     * @return
     */
    public static final String getMessage(String code){
        String language = ThreadLocalUtil.get("LOCAL_LANGUAGE");

        return getMessage(code,null,language);
    }

    /**
     * 根据code获取当前请求所属的语言环境下对应的文本信息
     * @param code
     * @return
     */
    public static final String getMessage(String code,Object[] params){
        String language = ThreadLocalUtil.get("LOCAL_LANGUAGE");

        return getMessage(code,params,language);
    }

    /**
     * 根据code获取指定语言环境下对应的文本信息
     * @param code
     * @param language
     * @return
     */
    public static final String getMessage(String code,Object[] params,String language){
        if(language == null){
            language = UtilConstants.DEFAULT_LANGUAGE;
        }
        String[] lang = language.split("_");
        if (null != lang && lang.length > 1) {
            Locale locale = new Locale(lang[0], lang[1]);
            return msgSource.getMessage(code,params,locale);
        }else {
            throw new IllegalArgumentException("parameter language error,value = "+ language);
        }
    }


    public static String getI18nFileBaseName(String fileName){
        String suffix = ".properties";
        if(!fileName.endsWith(suffix)){
            return null;
        }
        fileName = fileName.substring(0,fileName.length() - suffix.length());
        int lastIndex = 0;
        for(int i = 0; i < 2; i++){
            lastIndex = fileName.lastIndexOf('_');
            if(lastIndex == -1){
                return null;
            }
            fileName = fileName.substring(0,lastIndex);
        }
        return fileName;
    }

    public static String getLanguageByNationCode(String code){
        if("CHN".equals(code))
            return "zh_CN";
        if("TWN".equals(code) || "HKG".equals(code) || "MAC".equals(code))
            return "zh_TW";
        else
            return "en_US";
    }

}
