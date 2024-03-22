package com.verificer.utils;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class IpUtils {
    private static final String baseUrl ="https://api.map.baidu.com";

    static class IpLocationResp {
        private Integer status;
        private String address;
        private IpLocationContent content;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public IpLocationContent getContent() {
            return content;
        }

        public void setContent(IpLocationContent content) {
            this.content = content;
        }
    }


    static class IpLocationContent{
        @JSONField(name = "address_detail")
        IpLocationAddrDetail addressDetail;

        public IpLocationAddrDetail getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(IpLocationAddrDetail addressDetail) {
            this.addressDetail = addressDetail;
        }
    }

    static class IpLocationAddrDetail{
        private String adcode;

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }
    }



    public static String getIpProvinceCode(String ip,String ak,String sk) throws Exception{
        Map<String,String> pMap = new HashMap<>();
        pMap.put("ip","119.123.134.180");
        String resp = get("/location/ip",pMap,ak,sk);
        IpLocationResp ipResp = FastJson.fromJson(resp,IpLocationResp.class);
        if(ipResp.getStatus() == null || ipResp.getStatus() != 0 || SStringUtils.isEmpty(ipResp.getAddress()))
            throw new RuntimeException("百度地图的获取ip定位接口调用失败：返回内容"+resp);

        //ip的定位是国外的，暂时不考虑国外的用户
        if(!ipResp.getAddress().startsWith("CN"))
            throw new RuntimeException("ip定位是国外城市，接口范围内容如下："+resp);

        if(ipResp.getContent() == null || ipResp.getContent().addressDetail == null || SStringUtils.isEmpty(ipResp.getContent().getAddressDetail().getAdcode()))
            throw new RuntimeException("百度地图的获取ip定位接口调用失败,缺少content字段：返回内容"+resp);

        return ipResp.getContent().getAddressDetail().getAdcode().substring(0,2);


    }

    private static String get(String url,Map<String,String> params,String ak,String sk) throws UnsupportedEncodingException {
        Map<String,String> pMap = new LinkedHashMap<String, String>();
        pMap.putAll(params);
        pMap.put("ak",ak);

        String paramsStr = toQueryString(pMap);

        url = url+"?"+paramsStr;
        String sign = sing0(url,sk);

        url = url+"&sn="+sign;
        System.out.println(url);

        String resp = HttpsUtils.getJson(baseUrl+url,null);
//        resp  = URLDecoder.decode(resp,"UTF-8");
        resp = StringEscapeUtils.unescapeJava(resp);
//        System.out.println(resp);
        return resp;
    }

    private static String sing0(String url,String sk) throws UnsupportedEncodingException {
        url = URLEncoder.encode(url, "UTF-8");
        String txt = url+sk;
        return MD5(txt);

    }


    // 对Map内所有value作utf8编码，拼接返回结果
    private static String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }



    public static void main(String args[]) throws Exception {

        String code = getIpProvinceCode("119.123.134.180","PBxE0YMGel5p191KTdogE3xt0wzx....","2c2SlHhdYPSmx5PVZ5Z3Stu2ZyNV....");

        System.out.println(code);
    }

}
