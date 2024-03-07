package com.verificer.exchange.admin.controller.common;

import com.google.common.collect.Lists;
import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.NationalVo;
import com.verificer.common.exception.UploadException;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.HwObsClientUtil;
import com.verificer.utils.OSSClientUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "上传图片")
@RequestMapping("/upload")
@RestController
public class FileUploadController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    public static final String[] PICTURE_SUFFIX = new String[]{"jpg","png","jpeg","gif"};

    @Autowired
    BaseCustomerService baseCustomerService;

    @ApiOperation(
            value = "上传图片",
            response = Response.class,
            httpMethod = "POST",
            notes = "按照文件上传的顺序返回上传后的文件url"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "file", value = "图片",paramType = "multi-part",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/image/upload2", method = RequestMethod.POST)
    public Object imageUpload2(HttpServletRequest request) {
        List<String> urls = ossfilesUpload(request,"");
        return Response.dataSuccess(urls);
    }





    @ApiOperation(
            value = "上传图片",
            response = Response.class,
            httpMethod = "POST",
            notes = "按照文件上传的顺序返回上传后的文件url"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "file", value = "图片",paramType = "multi-part",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public Object imageUpload(HttpServletRequest request) {
        List<String> urls = ossfilesUpload(request,"");
        return Response.dataSuccess(urls);
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
                            String path= HwObsClientUtil.uploadObject2OSS(file1,dir);
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
