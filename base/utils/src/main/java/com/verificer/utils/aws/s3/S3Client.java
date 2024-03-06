package com.verificer.utils.aws.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.HashMap;
import java.util.Map;

public class S3Client {
    private static final Map<String,AmazonS3> insMap = new HashMap();

    public synchronized static  AmazonS3 getInstance(String accessKey,String secretKey,String region){
        String spilt = "@!@-@!@";
        String key = new StringBuffer(accessKey).append(spilt).append(spilt).append(region).toString();
        if(!insMap.containsKey(key)){
            AmazonS3 client = getAmazonS3(accessKey,secretKey,region);
            insMap.put(key,client);
        }
        return insMap.get(key);
    }
    private static AmazonS3 getAmazonS3(String accessKey,String secretKey,String region) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration baseOpts = new ClientConfiguration();
        //
        baseOpts.setSignerOverride("S3SignerType");
        baseOpts.setProtocol(Protocol.HTTPS);
        //
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(hostName, region))  // 如果有endpoint，可以用这个，这个和withRegion(Region)不能一起使用
//                .withPathStyleAccessEnabled(true)  // 如果配置了S3域名，就需要加这个进行路径访问，要不然会报AccessKey不存在的问题
                .withClientConfiguration(baseOpts)
                .build();
        return amazonS3;
    }

}
