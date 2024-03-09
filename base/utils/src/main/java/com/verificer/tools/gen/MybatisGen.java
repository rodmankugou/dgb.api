package com.verificer.tools.gen;

import com.verificer.tools.ScannerUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.SYamlUtils;
import org.apache.commons.io.FileUtils;

import javax.management.relation.RelationNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MybatisGen {

    /**
     * 以oriDir文件夹中的fileName文件的内容覆盖dest文件夹中的同名文件内容
     * @param oriDir
     * @param destDir
     * @param fileName
     */
    public static void replaceOrCopy(String oriDir, String destDir,String fileName){
        if(SStringUtils.isEmpty(oriDir))
            throw new IllegalArgumentException("parameter oriDir can not be null!");
        if(SStringUtils.isEmpty(destDir))
            throw new IllegalArgumentException("parameter destDir can not be null!");
        if(SStringUtils.isEmpty(fileName))
            throw new IllegalArgumentException("parameter fileName can not be null!");

        oriDir = preHandleDir(oriDir);
        destDir = preHandleDir(destDir);

        assertFileExist(oriDir+fileName);

        try {
            List<String> oriLines = FileUtils.readLines(new File(oriDir+fileName),"utf-8");
            FileUtils.writeLines(new File(destDir + fileName),oriLines,false);
        } catch (IOException e) {
            throw new RuntimeException("copy file content from "+ oriDir + fileName +" to "+ destDir+fileName +"failed");
        }
    }

    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    private static void assertFileExist(String path){
        File file = new File(path);
        if(!file.exists())
            throw new RuntimeException("file:" + path +" not exist!");
    }

    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    private static boolean isFileExists(String path){
        File file = new File(path);
        return file.exists();
    }

    /**
     * 如果dest目录下的fileName文件存在则不做任何处理，否则将oriDir文件夹里的fileName文件复制到destDir文件夹
     * @param oriDir
     * @param destDir
     * @param fileName
     */
    private static void skipOrCopyMapperJavaFile(String oriDir,String destDir, String fileName){
        if(SStringUtils.isEmpty(oriDir))
            throw new IllegalArgumentException("parameter oriDir can not be null!");
        if(SStringUtils.isEmpty(destDir))
            throw new IllegalArgumentException("parameter destDir can not be null!");
        if(SStringUtils.isEmpty(fileName))
            throw new IllegalArgumentException("parameter fileName can not be null!");

        oriDir = preHandleDir(oriDir);
        destDir = preHandleDir(destDir);

        String oriFilePath = oriDir + fileName;
        String destFilePath = destDir + fileName;

        assertFileExist(oriFilePath);
        if(!isFileExists(destFilePath)){
            replaceOrCopy(oriDir,destDir,fileName);
        }
    }

    /**
     * 如果dest目录下存在fileName文件，则将该目录下的文件与oriDir目录下的同名文件合并
     * @param oriDir
     * @param destDir
     * @param fileName
     */
    private static void mergeOrCopyMapperXmlFile(String oriDir,String destDir,String fileName){
        if(SStringUtils.isEmpty(oriDir))
            throw new IllegalArgumentException("parameter oriDir can not be null!");
        if(SStringUtils.isEmpty(destDir))
            throw new IllegalArgumentException("parameter destDir can not be null!");
        if(SStringUtils.isEmpty(fileName))
            throw new IllegalArgumentException("parameter fileName can not be null!");

        oriDir = preHandleDir(oriDir);
        destDir = preHandleDir(destDir);

        String oriFilePath = oriDir + fileName;
        String destFilePath = destDir + fileName;

        assertFileExist(oriFilePath);

        if(!isFileExists(destFilePath)){
            replaceOrCopy(oriDir,destDir,fileName);
        }else {
            //提取需要预留的内容
            List<String> retainLines = extractRetainCodeForMapperXml(destFilePath);
            if(retainLines.isEmpty())
                replaceOrCopy(oriDir,destDir,fileName);
            else {
                try {
                    List<String> genXml = FileUtils.readLines(new File(oriFilePath),"utf-8");
                    Integer endIndex = null;
                    for(int i = genXml.size() - 1 ; i >= 0; i--){
                        String line = genXml.get(i);
                        line = line.trim();
                        if(line.startsWith("</mapper>")){
                            endIndex = i-1;
                            break;
                        }
                    }
                    if(endIndex == null)
                        throw new RuntimeException(oriFilePath +"missing close tab </mapper> ");

                    List<String> result = new LinkedList<>();
                    for(int i = 0; i <= endIndex;i++)
                        result.add(genXml.get(i));
                    for(String line : retainLines)
                        result.add(line);

                    FileUtils.writeLines(new File(destFilePath),result,false);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage(),e);
                }

            }

        }
    }

    /**
     * 从mapper的xml文件中提取非生成的代码
     * @return
     */
    private static List<String> extractRetainCodeForMapperXml(String path){
        if(SStringUtils.isEmpty(path))
            throw new IllegalArgumentException("parameter path can not be null!");

        assertFileExist(path);

        List<String> xml = null;
        try {
            xml = FileUtils.readLines(new File(path),"utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        List<String> result = new LinkedList<>();
        boolean flag = false;
        for(String line : xml){
            String tempLine = line.trim();
            if(flag)
                result.add(line);
            else if(tempLine.startsWith("<!--")){
                tempLine = tempLine.substring(4);
                if(tempLine.trim().startsWith("以上内容自动生成") || tempLine.trim().startsWith("以上代码自动生成") ){
                    flag = true;
                    result.add(line);

                }
            }

        }
        return result;
    }



    private static String preHandleDir(String dirPath){
        if(SStringUtils.isEmpty(dirPath))
            throw new IllegalArgumentException("parameter dirPath can not be null");
        return dirPath.endsWith("/") || dirPath.endsWith("\\") ? dirPath : dirPath+"/";
    }



    /**
     * 将mybatis生成工具生成的代码，合并到当前代码中
     * @param cfgFile
     * @param module
     * @param model
     */
    public static void updateMapperAndModelFiles(File cfgFile,String module,String model){

        String oriModelDir = SYamlUtils.getConfig(module+".gen.entityDir", cfgFile);
        String oriMapperDir = SYamlUtils.getConfig(module+".gen.mapperDir", cfgFile);
        String destModelDir = SYamlUtils.getConfig(module+".code.entityDir", cfgFile);
        String destMapperDir = SYamlUtils.getConfig(module+".code.mapperDir", cfgFile);

        //替换模型的java文件
        replaceOrCopy(oriModelDir,destModelDir,model+".java");
        //如果mapper.java文件不存在则复制，否则跳过
        skipOrCopyMapperJavaFile(oriMapperDir,destMapperDir,model+"Mapper.java");
        //如果mapper.xlm存在，则复制出其中非自动生成的部分代码并放入到最新的生成代码中，否则直接覆盖
        mergeOrCopyMapperXmlFile(oriMapperDir,destMapperDir,model+"Mapper.xml");

    }

    /**
     * 将mybatis生成工具生成的代码，合并到当前代码中
     * @param cfgFile
     * @param module
     * @param models
     */
    public static void updateMapperAndModelFiles(boolean forceFlag,File cfgFile,String module,String... models){

        for(String model : models){
            String destMapperDir = SYamlUtils.getConfig(module+".code.mapperDir", cfgFile);
            String xmlPath = preHandleDir(destMapperDir)+model+"Mapper.xml";
            if(!isFileExists(xmlPath) || extractRetainCodeForMapperXml(xmlPath).isEmpty()){
                System.out.println("copy：即将copy新生成的xml代码文件到["+xmlPath+"]");
            }else {
                System.out.println("merge：即将merge新生成到xml代码到["+xmlPath+"]。。。");
            }


            if(forceFlag){
                if(!ScannerUtils.chosenByYOrN()){
                    System.out.println("停止后续到代码更新操作");
                    System.exit(1);
                }
            }
            updateMapperAndModelFiles(cfgFile,module,model);

        }

    }




    public static void main(String args[]){
        String cfgFilePath = "/Users/liujinhua/dev/workspace/dgb.api/gen_cfg/mybatis_gen_cfg.yml";

//        updateMapperAndModelFiles(true,new File(cfgFilePath),"biz","Transfer");
//        updateMapperAndModelFiles(true,new File(cfgFilePath),"account","AccountLog");
//        updateMapperAndModelFiles(true,new File(cfgFilePath),"user","Avatar");
        updateMapperAndModelFiles(true,new File(cfgFilePath),"biz","Goods","Spec");
//        updateMapperAndModelFiles(true,new File(cfgFilePath),"biz","Shop");
    }
}
