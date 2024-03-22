package com.verificer.exchange.admin.controller;

import com.alibaba.dubbo.remoting.TimeoutException;
import com.alibaba.dubbo.rpc.RpcException;
import com.verificer.ErrCode;
import com.verificer.beans.UserIdentity;
import com.verificer.biz.beans.constants.BizConst;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.enums.ClientEnum;
import com.verificer.exchange.admin.entity.Staff;
import com.verificer.exchange.admin.service.StaffService;
import com.verificer.security.login.ILoginMonitor;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.ThreadLocalUtil;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.utils.web.UserIdentityUtils;
import com.verificer.web.common.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 35336 on 2021/1/22.
 */
public class BaseController {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PRECISION = BizConst.DEFAULT_PRECISION;
    protected static final int DEFAULT_EXPORT_MAX_ROW = BizConst.DEFAULT_EXPORT_MAX_ROW;
    protected static final int DEFAULT_MAX_PAGE_SIZE = 1000;

    @Autowired
    ILoginMonitor myLoginMonitor;

    @Autowired
    StaffService _staffService;


    @Value("#{configProperties['HOST']}")
    protected String host;

    @Value("#{configProperties['CONTEXT_PATH']}")
    protected String contextPath;

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


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


    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split("\\.").length != 4) {
            return false;
        }
        return true;
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

        e.printStackTrace();

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

    public  Staff getCurLoginStaff(HttpServletRequest hReq){
        Long id = UserIdentityUtils.getUserIdentity(hReq).getId();
        Staff staff = _staffService.getById(id);
        return staff;
    }
}
