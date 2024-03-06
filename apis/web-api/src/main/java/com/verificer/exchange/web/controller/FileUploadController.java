package com.verificer.exchange.web.controller;


import com.google.common.collect.Lists;
import com.verificer.common.exception.UploadException;
import com.verificer.utils.OSSClientUtil;
import com.verificer.utils.SFileUtils;
import com.verificer.utils.SImageUtils;
import com.verificer.utils.SStringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 */
public class FileUploadController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    public static final String[] PICTURE_SUFFIX = new String[]{"jpg","png","jpeg"};

    @Value("#{configProperties['IS_LOCAL_STORAGE']}")
    private boolean isLocalStorage;

    @Value("#{configProperties['LOCAL_STORAGE_PATH']}")
    private String localStoragePath;

    @Value("#{configProperties['HOST']}")
    private String host;

    private String FILE_URL_PATH = "/image/get/";


    /**
     *
     * @param request
     * @param dir 临时文件目录
     * @param start 文件前缀
     * @return
     */
    protected List<String> filesUpload(HttpServletRequest request, String dir, String start){
        return filesUpload(request,dir,start,false,0);
    }
    /**
     *
     * @param request
     * @param dir OSS文件目录
     * @param start 文件前缀
     * @param compressionFlag 是否同时保存缩略图
     * @param maxWidth 缩略图宽度，仅当compressionFlag值为true时生效
     * @return
     */
    protected List<String> filesUpload(HttpServletRequest request, String dir, String start,boolean compressionFlag,int maxWidth) {
        List<String> filePathList = null;
        try {
            filePathList = new ArrayList<String>();
            // 创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    // 取得上传文件

                    MultipartFile file = multiRequest.getFile(iter.next());

                    String fileTmpName = file.getName();

                    logger.info("upload file:" + fileTmpName);
                    if (file != null && fileTmpName.startsWith(start)) {
                        // 取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (!SStringUtils.isEmpty(myFileName)) {
                            String suffix = file.getOriginalFilename().substring(
                                    file.getOriginalFilename().lastIndexOf("."));
                            // 重命名上传后的文件名
                            boolean isOk = checkSuffix(suffix, PICTURE_SUFFIX);
                            if (!isOk) {
                                logger.error("非法文件后缀");
                                break;
                            }

                            if(isLocalStorage){
                                String fileId = UUID.randomUUID().toString();
                                String destFilePath = localStoragePath+fileId ;
                                SFileUtils.copyInputStreamToFile(file.getInputStream(), destFilePath);
                                String url = host + FILE_URL_PATH + fileId;
                                filePathList.add(url);

                                if(compressionFlag){
                                    //如果要保存缩略图
                                    File miniDestFile = new File(destFilePath+"_Mini");
                                    SImageUtils.scaleForceWidth(new File(destFilePath),miniDestFile,290);
                                }
                            }else {
                                // 定义上传路径
                                String uploadDir = dir + (SStringUtils.isEmpty(dir) ? "" : (dir.endsWith("/") ? "" : "/"));
                                String random = UUID.randomUUID().toString();
                                String fileName = random + suffix;
                                //FTPUtil.putFile(serverIp, username, password, file.getInputStream(), fileName, uploadDir);
                                //filePathList.add(uploadDir + fileName);
                                File file1=  OSSClientUtil.multipartFileToFile(file);
                                String path= OSSClientUtil.uploadObject2OSS(file1,uploadDir+fileName);
                                OSSClientUtil.delteTempFile(file1);
                                filePathList.add(path);
                            }


                        } else {
                            logger.error("文件为空");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件上传失败" + e.getMessage());
            throw new UploadException("文件上传失败");
        }
        return filePathList;
    }

    private boolean checkSuffix(String suffix, String... okSuffix) {
        suffix = suffix.toLowerCase();
        logger.info(" suffix:" + okSuffix + "   act:" + suffix);
        List<String> okList = Lists.newArrayList(okSuffix);
        if (okList.contains(suffix.replace(".",""))) {
            return true;
        } else {
            return false;
        }
    }


}
