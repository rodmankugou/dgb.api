package com.verificer.tools;

import com.verificer.ErrCode;
import com.verificer.I18nCode;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.excel.ExcelField;
import com.verificer.utils.excel.ExcelUtil;
import com.verificer.web.common.i18n.I18nUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 * 用于检查i18n的国际化是否有遗漏
 * Created by 35336 on 2020/12/28.
 */
public class TI18nTools {

    /**
     * 获取java文件中的国际化key列表
     * @return
     */
    private static List<String> getCodeList(Class clazz){
        List<String> codeList = new ArrayList<>();
        Field[] fields = clazz.getFields();
        for(Field field : fields){
            if(!Modifier.isStatic(field.getModifiers())){
                //如果不是静态变量，忽略
                continue;
            }
            codeList.add(field.getName());
        }
        return codeList;
    }

    private static Set<String> getKeysInI18nFile(String fileName) throws IOException {
        File file = new File(fileName);
        Set<String> codeSet = new HashSet<>();
        List<String> lines = FileUtils.readLines(file,"utf-8");
        for(String line : lines){
            line = line.trim();
            if (line.startsWith("#") || SStringUtils.isEmpty(line)){
                continue;
            }
            if(line.indexOf('=') == -1){
                throw new RuntimeException("错误的数据行："+line);
            }
            String code = line.split("=")[0];
            if(codeSet.contains(code)){
                throw new RuntimeException("重复的code："+code);
            }
            codeSet.add(code);
        }

        return codeSet;
    }

    private static Map<String,String> loadI18nFile(String fileName) throws IOException {
        File file = new File(fileName);
        Map<String,String> map = new HashMap<>();
        List<String> lines = FileUtils.readLines(file,"utf-8");
        for(String line : lines){
            line = line.trim();
            if (line.startsWith("#") || SStringUtils.isEmpty(line)){
                continue;
            }
            if(line.indexOf('=') == -1){
                throw new RuntimeException("错误的数据行："+line);
            }
            String code = line.split("=",2)[0];
            String value = line.split("=",2)[1];
            if(map.containsKey(code)){
                throw new RuntimeException("重复的code："+code);
            }
            map.put(code,value);
        }

        return map;
    }

    /**
     * 判断集合i18nSet是否全包含javaCodeSet中的元素
     * @param i18nSet
     * @param javaCodeSet
     * @param tag
     * @return
     */
    public static boolean containAll(Set<String> i18nSet,List<String> javaCodeSet,String tag){
        boolean flag = true;
        for(String code : javaCodeSet){
            if(!i18nSet.contains(code)){
                System.out.println("Tag:"+tag+",缺少了code:"+code);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 判断i18n文件中是否全包含javaFile中定义的i18nCode
     * @param i18nFiles
     * @param javaFile
     */
    public static void compare(Class javaFile,String tag,String... i18nFiles) throws IOException {
        List<String> keyList = getCodeList(javaFile);
        Set<String> set = new HashSet<>();
        for(String i18nFile : i18nFiles){
            set.addAll(getKeysInI18nFile(i18nFile));
        }
        if(!containAll(set,keyList,tag)){
            throw new RuntimeException("Tag:"+tag+",文件缺少了国际化配置");
        }
    }

    static class ExEntity {
        @ExcelField("zh_CN")
        private String lang2Value;
        @ExcelField("en_US")
        private String langValue;
        @ExcelField("key")
        private String key;
        public String getLang2Value() {
            return lang2Value;
        }

        public void setLang2Value(String lang2Value) {
            this.lang2Value = lang2Value;
        }

        public String getLangValue() {
            return langValue;
        }

        public void setLangValue(String langValue) {
            this.langValue = langValue;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static void export(String[] files ,String[] file2s,String outPutFile) throws IOException {
        Map<String,String>  map1 = new HashMap();
        Map<String,String>  map2 = new HashMap();
        for(String file : files)
            map1.putAll(loadI18nFile(file));

        for(String file2 : file2s)
            map2.putAll(loadI18nFile(file2));



        String[] headers = new String[]{"zh_CN","en_US"};
        List<ExEntity> rows = new LinkedList<>();
        for(String key : map1.keySet()){
            ExEntity row = new ExEntity();
            row.langValue = map1.get(key);
            row.lang2Value = I18nUtils.getMessage(key,null,"zh_CN");
            row.key=key;
            rows.add(row);
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(outPutFile));
            ExcelUtil.exportExcel("transfer",ExEntity.class,rows,out);
        } catch (IOException e) {
            if(out != null)
                out.close();
        }

    }

    private static void compare() throws Exception{
        String twFile = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/base_zh_TW.properties";
        String cnFile = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/base_zh_CN.properties";
        String usFile = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/base_en_US.properties";
        String cnFile2 = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/biz_zh_CN.properties";
        String twFile2 = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/biz_zh_TW.properties";
        String usFile2 = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/biz_en_US.properties";

        compare(ErrCode.class,"zh_CN",cnFile,cnFile2);
        compare(I18nCode.class,"zh_CN",cnFile,cnFile2);
//        compare(ErrCode.class,"zh_TW",twFile,twFile2);
//        compare(I18nCode.class,"zh_TW",twFile,twFile2);
//        compare(ErrCode.class,"en_US",usFile,usFile2);
//        compare(I18nCode.class,"en_US",usFile,usFile2);
    }



    public static void export() throws IOException {
        String usFile = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/base_en_US.properties";
        String usFile2 = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/biz_en_US.properties";
        String cnFile = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/base_zh_CN.properties";
        String cnFile2 = "/Users/liujinhua/dev/ideaWorkSpace/bitver.api/i18n/biz_zh_CN.properties";

        String exportFile = "/Users/liujinhua/Desktop/bitver-server-transfer.xlsx";
        export(new String[]{usFile,usFile2}, new String[] {cnFile,cnFile2},exportFile);
    }

    public static void main(String args[]) throws IOException {
        export();
    }


}
