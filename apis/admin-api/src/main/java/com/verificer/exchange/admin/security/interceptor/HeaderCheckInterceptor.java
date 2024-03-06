package com.verificer.exchange.admin.security.interceptor;


import com.verificer.ErrCode;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录拦截器
 */
public class HeaderCheckInterceptor extends BaseInterceptor {

    private static final Log logger = LogFactory.getLog(HeaderCheckInterceptor.class);


    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
     * (javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }
        String client = SecurityUtil.getClient();
        if(SStringUtils.isAnyEmpty(client)) {
            respAsJson(response, ErrCode.HTTP_HEAD_ERROR);
            return false;
        }
        return true;
    }



}
