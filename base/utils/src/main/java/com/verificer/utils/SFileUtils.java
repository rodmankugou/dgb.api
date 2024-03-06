package com.verificer.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 35336 on 2021/2/25.
 */
public class SFileUtils {
    /**
     * 通过http通信协议从网络中下载图片
     * @param urlStr
     * @return
     */
    public static File downloadHttpFile(String urlStr,String fileSuffix){
        return downloadHttpFile(urlStr,fileSuffix,null);
    }

    /**
     * 通过http通信协议从网络中下载图片
     * @param urlStr
     * @return
     */
    public static File downloadHttpFile(String urlStr,String fileSuffix,Map<String,String> headers){

        // 文件保存位置
        if(fileSuffix == null){
            if(urlStr.lastIndexOf('.') != -1){
                fileSuffix = urlStr.substring(urlStr.lastIndexOf('.'));
            }
        }
        File file = new File(UuidUtils.newUuid()+fileSuffix);
        downloadHttpFile(urlStr,file,headers);
        return file;
    }


    public static File downloadHttpFileByPost(String urlStr,File file) {
        return downloadHttpFile(urlStr,file,null,"POST");
    }

    public static File downloadHttpFile(String urlStr,File file,Map<String,String> headers){
        return downloadHttpFile(urlStr,file,headers,"GET");
    }
    /**
     * 通过http通信协议从网络中下载图片
     * @param urlStr
     * @return
     */
    public static File downloadHttpFile(String urlStr,File file,Map<String,String> headers,String httpMethod){
        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = null;
            if(urlStr.startsWith("https")){
                conn = getHttpsURLConnection(urlStr);
            }else {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.setRequestMethod(httpMethod);
            // 设置超时间为30秒
            conn.setConnectTimeout(10 * 3000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if(headers != null){
                for (String key : headers.keySet()){
                    conn.setRequestProperty(key, headers.get(key));
                }
            }
            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            // 获取自己数组
            byte[] getData = readInputStream(inputStream);



            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream downloadHttpFile(String urlStr){
        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = null;
            if(urlStr.startsWith("https")){
                conn = getHttpsURLConnection(urlStr);
            }else {
                conn = (HttpURLConnection) url.openConnection();
            }
            // 设置超时间为10秒
            conn.setConnectTimeout(10 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            return inputStream;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接
     * @param uri
     * @return
     * @throws Exception
     */
    private static HttpsURLConnection getHttpsURLConnection(String uri) throws Exception {
        TrustManager[] trustManagers = null;

        trustManagers = new TrustManager[] { new DefaultTrustManager()};


        //设置服务端支持的协议
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        context.init(null, trustManagers, null);
        SSLSocketFactory sslFactory = context.getSocketFactory();

        URL url = new URL(uri);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslFactory);
        //验证URL的主机名和服务器的标识主机名是否匹配
        connection.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //if ("xxx".equals(hostname)) {
                //    return true;
                //} else {
                //    return false;
                //}
                return true;
            }
        });
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }

    /**
     * 默认的TrustManager实现，不安全
     */
    private static final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 下载本地主机中的文件
     * @param fileName
     * @return
     */
    public static ResponseEntity<byte[]> download(String fileName) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String rspName = fileName;
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            File file = new File(rspName);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            try {
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }


    /**
     * 将本地文件系统中的文件打包成zip并下载
     * @param files
     * @return
     */
    public static ResponseEntity<byte[]> zipDownload(File... files) {
        ZipOutputStream out = null;
        BufferedOutputStream bo = null;
        String zipFileName = new StringBuffer().append(UuidUtils.newUuid()).append('.').append("zip").toString();

        try {
            out = new ZipOutputStream(new FileOutputStream(zipFileName));
            bo = new BufferedOutputStream(out);
            for(File file : files){
                FileInputStream fis = null;
                BufferedInputStream bi = null;
                try {
                    String fileName = file.getName();
                    out.putNextEntry(new ZipEntry(fileName));
                    fis = new FileInputStream(file);
                    bi = new BufferedInputStream(fis);
                    int b;
                    while ((b=bi.read()) != -1){
                        bo.write(b);
                    }

                    bo.flush();
                    out.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(),e);
                }finally {
                    if(bi != null){
                        try {
                            bi.close();
                        } catch (IOException e) {
                        }
                    }
                    if(fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
            if(bo != null){
                try {
                    bo.close();
                } catch (IOException e) {
                }
            }

        }

        for(File file : files)
        {
            if(file.exists())
                file.delete();
        }
        return download(zipFileName);
    }

    /**
     * 将网络上通过http协议下载的文件打包下载
     * @param urls
     * @return
     */
    public static ResponseEntity<byte[]> zipDownloadHttpFiles(String... urls) {
        File[] files = new File[urls.length];
        for(int i = 0; i < urls.length; i++){
            File file = downloadHttpFile(urls[i],null);
            files[i] = file;
        }

        return zipDownload(files);
    }

    public static void copyInputStreamToFile(InputStream in,String targetPath) throws Exception {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetPath);

            byte[] b = new byte[1024];

            int length;

            while((length= in.read(b)) > 0){
                fos.write(b,0,length);
            }
        } finally {
            try {
                if(in != null)
                    in.close();
            } catch (IOException e) {
                //
            }

            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                //
            }
        }


    }

    public static boolean isPngFile(File file){
        String fileHeader = getFileHeader(file);
        if("89504E47".equals(fileHeader))
            return true;

        return false;
    }

    public static boolean isJpgFile(File file){
        String fileHeader = getFileHeader(file);

        if("FFD8FFE0".equals(fileHeader)
            || "FFD8FFE1".equals(fileHeader)
            || "FFD8FFE8".equals(fileHeader)
        )
            return true;
        return false;
    }

    public static String getFileHeader( File file) {
        InputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(file);
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static String getFileHeader( MultipartFile file) {
        InputStream is = null;
        String value = null;
        try {
            is = file.getInputStream();
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (byte b : src) {
            hv = Integer.toHexString(b & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    public static void main(String args[]){
        System.out.println(isJpgFile(new File("/Users/liujinhua/Desktop/avatars/1.png")));
        System.out.println(isPngFile(new File("/Users/liujinhua/Desktop/avatars/mtinft-logo.jpeg")));

        File file = new File("/Users/liujinhua/Desktop/temp/111.png");
        downloadHttpFileByPost("http://127.0.0.1:5001/api/v0/cat?arg=QmYCYjSS9iH23jz1PSrXXUbEwj2AY5QBBjqkQ4tK9A1nVE",file);
    }
}
