package com.verificer.utils;

import com.verificer.utils.FastJson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import  org.apache.http.entity.ContentType;
public class HttpsUtils {

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();

    }

    /**
     * 发送https请求
     *
     * @throws Exception
     */
    public static String getJson(String url, Map<String, String> headMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            List<NameValuePair> listNVP = new ArrayList<NameValuePair>();

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(listNVP, "UTF-8");
            httpClient = HttpsUtils.createSSLClientDefault();
            setHead(httpGet, headMap);

            httpResponse = httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                throw new RuntimeException("https请问返回异常，statusLine:" + FastJson.toJson(statusLine));
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, "UTF-8");
                return jsObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送post请求
     *
     * @throws Exception
     */
    public static String postJson(String url, String jsonReqBody, Map<String, String> headMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            HttpPost httpReq = new HttpPost(url);

            //设置请求报文
            StringEntity stringEntity = new StringEntity(jsonReqBody,"utf-8");
            stringEntity.setContentType("application/json;charset=utf-8");
            httpReq.setEntity(stringEntity);

            if (headMap != null && headMap.size() > 0) {
                Set<String> keySet = headMap.keySet();
                for (String key : keySet) {
                    httpReq.addHeader(key, headMap.get(key));
                }
            }
            httpClient = HttpsUtils.createSSLClientDefault();

            httpResponse = httpClient.execute(httpReq);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                throw new RuntimeException("https请问返回异常，statusLine:" + FastJson.toJson(statusLine));
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, "UTF-8");
                return jsObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送post请求
     *
     * @throws Exception
     */
    public static String postFile(String url, String fileName, InputStream in, Map<String, String> headMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            HttpPost httpReq = new HttpPost(url);

            //设置请求报文
            HttpEntity entity =MultipartEntityBuilder.
                    create()
                    .addBinaryBody(fileName,in,ContentType.create("image/png"),"2.png").build();

            httpReq.setEntity(entity);

            if (headMap != null && headMap.size() > 0) {
                Set<String> keySet = headMap.keySet();
                for (String key : keySet) {
                    httpReq.addHeader(key, headMap.get(key));
                }
            }

            httpClient = HttpsUtils.createSSLClientDefault();

            httpResponse = httpClient.execute(httpReq);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                throw new RuntimeException("https请问返回异常，statusLine:" + FastJson.toJson(statusLine));
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, "UTF-8");
                return jsObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    //
                }
            }
            try {
                if(httpResponse != null)
                  httpResponse.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送post请求
     *
     * @throws Exception
     */
    public static String deleteJson(String url, String jsonReqBody, Map<String, String> headMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            HttpDelete httpReq = new HttpDelete(url);



            httpClient = HttpsUtils.createSSLClientDefault();
            setHead(httpReq, headMap);

            httpResponse = httpClient.execute(httpReq);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                throw new RuntimeException("https请问返回异常，statusLine:" + FastJson.toJson(statusLine));
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, "UTF-8");
                return jsObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setHead(HttpRequestBase httpReq, Map<String, String> headMap) {
        if (headMap != null && headMap.size() > 0) {
            Set<String> keySet = headMap.keySet();
            for (String key : keySet) {
                httpReq.addHeader(key, headMap.get(key));
            }
        }

    }

    public static void main(String[] args) throws Exception {
//        String filePath = "/Users/liujinhua/Desktop/avatars/2.png";
//        String apiKey = "39e4d46672cff36a39d0";
//        String apiSecret = "71710260cf96985f82797745d9f19a5f87254f41b615dcaaa0e2afe7ddd54343";
//        Map<String,String> headers = new HashMap<>();
//        headers.put("pinata_api_key",apiKey);
//        headers.put("pinata_secret_api_key",apiSecret);
//
//        String respStr = postFile("https://api.pinata.cloud/pinning/pinFileToIPFS","file",new FileInputStream(new File(filePath)),headers);
//        System.out.println(respStr);
//        Map<String,String> headers = new HashMap<>();
//        headers.put("pinata_api_key","39e4d46672cff36a39d0");
//        headers.put("pinata_secret_api_key","71710260cf96985f82797745d9f19a5f87254f41b615dcaaa0e2afe7ddd54343");
//       String resp =  getJson("https://gateway.pinata.cloud/ipfs/QmagACXM7pukdukPSKvdNv6vscUMGFpVvdcmCZBZ76ZhJQ",headers);
//       System.out.println(resp);


    }

}

