package com.verificer.utils.ipfs;

import com.verificer.ErrCode;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.SellStatusEmun;
import com.verificer.utils.FastJson;
import com.verificer.utils.HttpsUtils;
import com.verificer.utils.SStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PinataUtils {
    static class PinataResp {
        String IpfsHash;
        String PinSize;
        String Timestamp;

        public String getIpfsHash() {
            return IpfsHash;
        }

        public void setIpfsHash(String ipfsHash) {
            IpfsHash = ipfsHash;
        }

        public String getPinSize() {
            return PinSize;
        }

        public void setPinSize(String pinSize) {
            PinSize = pinSize;
        }

        public String getTimestamp() {
            return Timestamp;
        }

        public void setTimestamp(String timestamp) {
            Timestamp = timestamp;
        }
    }

    public static Map<String,String> createHeader(String apiKey,String apiSecret) throws IOException {
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("pinata_api_key",apiKey);
        headerMap.put("pinata_secret_api_key",apiSecret);
        return headerMap;
    }

    public static String postFile(String apiKey,String apiSecret,String filePath)throws IOException{
        Map<String,String> headerMap = createHeader(apiKey,apiSecret);
        String respStr = HttpsUtils.postFile("https://api.pinata.cloud/pinning/pinFileToIPFS","file",new FileInputStream(new File(filePath)),headerMap);
        PinataResp resp = FastJson.fromJson(respStr,PinataResp.class);
        if(SStringUtils.isEmpty(resp.getIpfsHash())){
            throw new BaseException(ErrCode.SERVER_ERROR,"Upload file to IPFS failed,the response is :" + respStr);
        }
        return resp.getIpfsHash();
    }

    public static String postFile(String apiKey, String apiSecret, InputStream fileInputStream)throws IOException{
        Map<String,String> headerMap = createHeader(apiKey,apiSecret);
        String respStr = HttpsUtils.postFile("https://api.pinata.cloud/pinning/pinFileToIPFS","file",fileInputStream,headerMap);
        PinataResp resp = FastJson.fromJson(respStr,PinataResp.class);
        if(SStringUtils.isEmpty(resp.getIpfsHash())){
            throw new BaseException(ErrCode.SERVER_ERROR,"Upload file to IPFS failed,the response is :" + respStr);
        }
        return resp.getIpfsHash();
    }

    public static String postJson(String apiKey,String apiSecret,String json) throws IOException {
        Map<String,String> headerMap = createHeader(apiKey,apiSecret);
        String respStr = HttpsUtils.postJson("https://api.pinata.cloud/pinning/pinJSONToIPFS",json,headerMap);
        PinataResp resp = FastJson.fromJson(respStr,PinataResp.class);
        if(SStringUtils.isEmpty(resp.getIpfsHash())){
            throw new BaseException(ErrCode.SERVER_ERROR,"Upload file to IPFS failed,the response is :" + respStr);
        }
        return resp.getIpfsHash();
    }

    public static void main(String[] args) throws Exception {
        String filePath = "/Users/liujinhua/Desktop/avatars/2.png";
//        String apiKey = "37b18518b0e23b691dcb";
//        String apiSecret = "878a4381aa0bf56f8cd136396aa7cf7b1c66aacb14b5cb292eac10b74f2b3cde";
        String apiKey = "39e4d46672cff36a39d0";
        String apiSecret = "71710260cf96985f82797745d9f19a5f87254f41b615dcaaa0e2afe7ddd54343";
        Map<String,String> headers = new HashMap<>();


        String json = "{\"name\":\"Rodman\"}";
        System.out.println(postFile(apiKey,apiSecret,filePath));

//        System.out.println("result:"+postJson(apiKey,apiSecret,json));
    }
}
