package com.verificer.exchange.admin.security.interceptor;


import com.verificer.ErrCode;
import com.verificer.beans.UserIdentity;
import com.verificer.enums.ClientEnum;
import com.verificer.exchange.admin.security.annotation.DebugController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.security.login.ILoginMonitor;
import com.verificer.security.login.LoginStat;
import com.verificer.security.login.LogoutType;
import com.verificer.utils.RedisUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityConf;
import com.verificer.utils.web.SecurityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录拦截器
 */

@Component
public class DebugInterceptor extends BaseInterceptor {

    private static final Log logger = LogFactory.getLog(DebugInterceptor.class);


    @Value("#{configProperties['IS_DEBUG']}")
    private boolean isDebug;

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

        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod mHandler = (HandlerMethod) handler;


        DebugController debugAnnotation = AnnotationUtils.findAnnotation(mHandler.getBeanType(), DebugController.class);

        if(debugAnnotation == null || isDebug){
            return true;
        }



        respAsJson(response,ErrCode.DEBUG_IS_NOT_SUPPORT);
        return false;



    }


}
