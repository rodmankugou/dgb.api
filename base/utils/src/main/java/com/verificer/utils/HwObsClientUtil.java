package com.verificer.utils;



import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 使用华为云的obs上传文件
 * @author MrXiao
 * @version 1.0.0
 */
public class HwObsClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HwObsClientUtil.class);



    /**
     * 上传图片至OSS
     *
     * @param file       上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadObject2OSS(String endPoint,String ak,String sk,String bucketName, String crossDomain,File file, String uploadDir) {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        String fileSubffix = file.getName().lastIndexOf(".") == -1 ? "" : file.getName().substring(file.getName().lastIndexOf("."));
        String objectKey = UuidUtils.newUuid()+fileSubffix;
        try {
            PutObjectRequest request = new PutObjectRequest();
            request.setBucketName(bucketName);
            request.setFile(file);
            request.setObjectKey(objectKey);
            request.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
            PutObjectResult result = obsClient.putObject(request);
            return crossDomain+objectKey;
        } catch (Exception e) {
            logger.error("上传华为云OBS，服务器异常." + e.getMessage(), e);
            if(obsClient != null){
                try {
                    obsClient.close();
                } catch (IOException ioException) {
                }
            }

            throw new RuntimeException(e.getMessage(),e);

        }
    }

    public static void createBucket(String bucketname ){
        ObsClient obsClient = new ObsClient("9R1SGUTMVFNUZIGZWPUV", "jRja8jCcRtSYkcV1jUKWI7CnRaswAcdtSnbR0Tow", "obs.cn-south-1.myhuaweicloud.com");

        try {
            obsClient.createBucket(bucketname);
        } catch (ObsException e) {
            logger.error("创建华为云OBS bucket，服务器异常." + e.getMessage(), e);
            if(obsClient != null){
                try {
                    obsClient.close();
                } catch (IOException ioException) {
                }
            }
        }
    }

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    public static File inputStreamToTempFile(InputStream ins) throws Exception{
        File toFile = null;
        toFile = new File(UuidUtils.newUuid());
        inputStreamToFile(ins, toFile);
        ins.close();
        return toFile;
    }

    public static File createTempFile(String suffix){
        return new File(UuidUtils.newUuid()+"."+suffix);
    }

    public static String getSubfix(String fileName){
        if(fileName.lastIndexOf(".") == -1)
            return "";
        return fileName.substring(fileName.lastIndexOf("."));
    }
    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }


    public static void main(String[] args) {

        File file=new File("/Users/liujinhua/Desktop/avatars/bsc.png");
//        System.out.println(uploadObject2OSS(file,"temp"));

//        createBucket(bucketName);

    }



}