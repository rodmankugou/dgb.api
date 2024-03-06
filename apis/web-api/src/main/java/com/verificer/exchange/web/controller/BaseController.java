package com.verificer.exchange.web.controller;

import com.alibaba.dubbo.remoting.TimeoutException;
import com.alibaba.dubbo.rpc.RpcException;
import com.amazonaws.services.s3.AmazonS3;
import com.google.common.collect.Lists;
import com.verificer.ErrCode;
import com.verificer.beans.UserIdentity;
import com.verificer.biz.beans.constants.BizConst;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.common.exception.UploadException;
import com.verificer.enums.ClientEnum;
import com.verificer.security.login.ILoginMonitor;
import com.verificer.utils.*;
import com.verificer.utils.aws.s3.S3Client;
import com.verificer.utils.aws.s3.S3Utils;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.web.common.response.Response;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by 35336 on 2021/1/22.
 */
public class BaseController {
    private static final int DEFAULT_PAGE_SIZE = 10;
    protected static final int DEFAULT_MAX_PAGE_SIZE = 1000;
    protected static final int DEFAULT_PRECISION = BizConst.DEFAULT_PRECISION;
    protected static final int DEFAULT_EXPORT_MAX_ROW = BizConst.DEFAULT_EXPORT_MAX_ROW;
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    public static final String[] PICTURE_SUFFIX = new String[]{"jpg","png","jpeg","gif"};
    protected static final int ETH_PRECISiON = 4;

    @Autowired
    ILoginMonitor myLoginMonitor;


    @Autowired
    private BizService myBizService;

    @Value("#{configProperties['HOST']}")
    protected String host;

    @Value("#{configProperties['CONTEXT_PATH']}")
    protected String contextPath;


    @Value("#{configProperties['OBS_END_POINT']}")
    protected String OBS_END_POINT;
    @Value("#{configProperties['OBS_API_KEY']}")
    protected String OBS_API_KEY;
    @Value("#{configProperties['OBS_API_SECRET']}")
    protected String OBS_API_SECRET;
    @Value("#{configProperties['OBS_BUCKET_NAME']}")
    protected String OBS_BUCKET_NAME;
    @Value("#{configProperties['OBS_CROSS_DOMAIN']}")
    protected String OBS_CROSS_DOMAIN;

    @Value("#{configProperties['AWS_S3_END_POINT']}")
    protected String AWS_S3_END_POINT;
    @Value("#{configProperties['AWS_S3_API_KEY']}")
    protected String AWS_S3_API_KEY;
    @Value("#{configProperties['AWS_S3_SECRET_KEY']}")
    protected String AWS_S3_SECRET_KEY;
    @Value("#{configProperties['AWS_S3_REGION']}")
    protected String AWS_S3_REGION;
    @Value("#{configProperties['AWS_S3_BUCKET_NAME']}")
    protected String AWS_S3_BUCKET_NAME;

    public static int getFrom(Integer pageNum, Integer pageSize, Integer defaultPageSize) {
        return null == pageNum ? 0 : getPageSize(defaultPageSize, pageSize) * (pageNum - 1);
    }


    public static int getFrom(Integer pageNum, Integer pageSize) {
        return null == pageNum ? 0 : getPageSize( pageSize) * (pageNum - 1);
    }

    public static int getPageSize(Integer defaultSize, Integer pageSize) {
        return null == pageSize ? defaultSize : pageSize;
    }

    public static int getPageSize( Integer pageSize) {
        return null == pageSize ? DEFAULT_PAGE_SIZE : pageSize;
    }


    public String getIp(){
        return "";
    }

    public String getHeader(HttpServletRequest request,String name){

        return request.getHeader(name);
    }



    public String getLanguage() {
        String language = ThreadLocalUtil.get("LOCAL_LANGUAGE");

        if (SStringUtils.isEmpty(language)) {
            language = "en_US";
        }
        return language;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest req, Exception e) {
        System.out.println(e.getClass().getName() + "==================================" + (e instanceof BaseException) + "----" + e.getClass().getName().equals("BaseException"));
        logger.error("请求链接【" + req.getRequestURI() + "】错误", e);


        Response response = null;

        if(e instanceof BaseException){
            response = Response.failed((((BaseException) e).getErrCode()),((BaseException) e).getErrParams());
        }else if(e instanceof BizErrMsgException){
            response = Response.paramFailed((((BizErrMsgException) e).getMsgTemplate()),((BizErrMsgException) e).getErrParams());
        }else if(e instanceof RpcException && e.getCause() instanceof TimeoutException){
            response = Response.failed(ErrCode.SERVICE_TIME_OUT);
        }else {
            response = Response.failed(ErrCode.SERVER_ERROR);
        }
        return response;
    }

    public Integer getExportMaxCount(){
        return 100000;
    }


    public String getExcelFileType(){
        return "xlsx";
    }
    /**
     * 获取client
     * @return
     */
    public String getClient(){
        return ClientEnum.WEB.getName();
    }



    protected List<String> ossfilesUpload(HttpServletRequest request, String dir) {
        List<String> rst = new LinkedList<>();
        try {
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
                    if (file != null ) {
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
                            // 定义上传路径
                            File file1=  OSSClientUtil.multipartFileToFile(file);
                            String path= upToAws(file1,null);
                            OSSClientUtil.delteTempFile(file1);
                            rst.add(path);


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
        return rst;
    }



    protected String upToAws(File file,String dir){
        AmazonS3 client = S3Client.getInstance(AWS_S3_API_KEY,AWS_S3_SECRET_KEY,AWS_S3_REGION);
        return S3Utils.up(client,AWS_S3_BUCKET_NAME,file,AWS_S3_END_POINT);

    }

    /**
     * 获取hdfs图片的前缀
     * @return
     */
    protected String getImageUrlPrefix(){
        String cPath = host + contextPath ;
        cPath = (cPath.endsWith("/") ? cPath : cPath+"/") + "image/";
        return cPath;
    }

    /**
     * 获取图片访问token
     * @return
     */
    protected String getImageToken(){
        String token = SecurityUtil.getToken();
        return myLoginMonitor.getSubToken(ClientEnum.WEB.getName(), token,BizConst.IMAGE_SUB_TOKE_NAME,new UserIdentity());
    }

    /**
     * 暂存到数据库，最后保存在hdfs
     * @param request
     * @param scaleMaxSize  压缩后的图片最大值，单位为k
     * @param start
     * @param postFix
     * @return
     */
    protected List<String> hdfsFileUpload(HttpServletRequest request, Integer scaleMaxSize, String start, String... postFix) {
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
                            boolean isOk = checkSuffix(suffix, postFix);
                            if (!isOk) {
                                logger.error("非法文件后缀");
                                break;
                            }

                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            //强制将图片压缩成1M以下
                            SImageUtils.scaleForceSize(file.getInputStream(),outputStream,scaleMaxSize);
//                            String id = myBizService.imageAdd(ImageTypeEnum.HDFS.getValue(),null, ImageFormatTypeEnum.JPG.getValue(),outputStream.toByteArray());


//                            filePathList.add(HdfsImageUtils.parseImageUrl(myBizService.imageGetUrlById(getImageUrlPrefix(),id),getImageUrlPrefix(),getImageToken()));
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

    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");
        if (!checkIP(ip)) {
            ip = request.getHeader("X-Real-Ip");
        }if (!checkIP(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }if (!checkIP(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split("\\.").length != 4) {
            return false;
        }
        return true;
    }


    // 进行下载的处理
    public static ResponseEntity<byte[]> download(String fileName) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String rspName = fileName;
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            File file = new File(rspName);
            byte[] bytes = FileUtils.readFileToByteArray(file);

            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
