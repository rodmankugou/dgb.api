package com.verificer.utils.aws.s3;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.verificer.utils.FastJson;
import com.verificer.utils.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
public class S3Utils {
    private static final Logger logger = LoggerFactory.getLogger(S3Utils.class);


    public static String up(AmazonS3 client,String bucketName,MultipartFile multipartFile,String endPoint){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        String fileName = multipartFile.getOriginalFilename();
        String fileExtension = "";
        if(fileName.indexOf(".") != -1)
            fileExtension = fileName.substring(fileName.lastIndexOf("."));


        //  桶名，文件夹名，本地文件路径
        String key = endPoint+UuidUtils.newUuid()+fileExtension;
        System.out.println(key);
        try {

            PutObjectRequest por = new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), objectMetadata);
            por.withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult rst = client.putObject(por);
            return key;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static String up(AmazonS3 client,String bucketName,InputStream inputStream,String fileName,String endPoint){
        ObjectMetadata objectMetadata = new ObjectMetadata();


        String fileExtension = "";
        if(fileName.indexOf(".") != -1)
            fileExtension = fileName.substring(fileName.lastIndexOf("."));


        //  桶名，文件夹名，本地文件路径
        String key = UuidUtils.newUuid()+fileExtension;
        try {


            PutObjectRequest por =new PutObjectRequest(bucketName, key, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            por.withCannedAcl(CannedAccessControlList.PublicRead);
            PutObjectResult rst = client.putObject(por);
            return endPoint+key;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }


    public static String up(AmazonS3 client,String bucketName,File file,String endPoint){
        try {
            return up(client,bucketName,new FileInputStream(file),file.getName(),endPoint);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static void main(String args[]){


        File file = new File("/Users/liujinhua/Desktop/切图/Treta/banner.png");
        String apiKey = "AKIAYGLA52E4QMOATUGA";
        String secretKey = "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";
        String region = "ap-east-1";
        String bucketName = "sevenseas-test-acls";
        String endPoint = "https://sevenseas-test-acls.s3.ap-east-1.amazonaws.com/";
        String path = up(S3Client.getInstance(apiKey,secretKey,region),bucketName,file,endPoint);
        System.out.println("path= "+path);
    }

}



