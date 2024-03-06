package com.verificer.utils.ipfs;

import com.mchange.v2.uid.UidUtils;
import com.verificer.utils.*;

import java.io.File;
import java.io.InputStream;

public class IpfsUtils {
    private static final String IPFS_PREFIX = "ipfs://";

    public static String getJson(String ipfsDownloadApi, String ipfsUri){

        return getJsonByHash(ipfsDownloadApi,getHashFromUri(ipfsUri));

    }

    private static String getHashFromUri(String ipfsUri){
        if(SStringUtils.isEmpty(ipfsUri))
            throw new RuntimeException("Parameter ipfsUri can not be empty");
        if(ipfsUri.startsWith(IPFS_PREFIX)){
            return ipfsUri.substring(IPFS_PREFIX.length());
        }else{
            return ipfsUri;
        }
    }

    public static String getJsonByHash(String ipfsDownloadApi,String hash){
        return HttpClientUtils.sendHttpPost(ipfsDownloadApi+hash);
    }

    public static File getFile(String ipfsDownloadApi,String ipfsUri) {
        return getFileByHash(ipfsDownloadApi,getHashFromUri(ipfsUri));
    }

    public static File getFileByHash(String ipfsDownloadApi,String hash) {
        File file = HttpClientUtils.postData2(ipfsDownloadApi+hash,"",HttpClientUtils.CONTENT_TYPE_FORM_URL);

        return file;
    }

    public static void main(String args[]) throws Exception {
//        String uri = "ipfs://QmYCYjSS9iH23jz1PSrXXUbEwj2AY5QBBjqkQ4tK9A1nVE";
//        File file = getFile("http://127.0.0.1:5001/api/v0/cat?arg=",uri);
//        System.out.println(file.getPath());

        String uri = "ipfs://QmagACXM7pukdukPSKvdNv6vscUMGFpVvdcmCZBZ76ZhJQ";
        String json = getJson("http://127.0.0.1:5001/api/v0/cat?arg=",uri);
        System.out.println(json);
    }
}
