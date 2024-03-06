package com.verificer.exchange.web.security.interceptor;


import com.verificer.ErrCode;
import com.verificer.beans.UserIdentity;
import com.verificer.biz.beans.enums.CompanyRoleEnum;
import com.verificer.enums.ClientEnum;
import com.verificer.exchange.web.security.annotation.AllowUnActivation;
import com.verificer.exchange.web.security.annotation.CheckRole;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.security.login.ILoginMonitor;
import com.verificer.security.login.LoginStat;
import com.verificer.security.login.LogoutType;
import com.verificer.utils.RedisUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityConf;
import com.verificer.utils.web.SecurityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 角色检查拦截器
 */
public class CheckRoleInterceptor extends BaseInterceptor {

    private static final Log logger = LogFactory.getLog(CheckRoleInterceptor.class);

    private RedisUtil redisUtil;
    private ILoginMonitor<UserIdentity> loginMonitor;

    public void setLoginMonitor(ILoginMonitor loginMonitor) {
        this.loginMonitor = loginMonitor;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }


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

        CheckRole checkRole = mHandler.getMethodAnnotation(CheckRole.class);
        boolean  isCheckRole = null != checkRole;

        if (isCheckRole) {
            UserIdentity userIdentity = SecurityUtil.currentLogin();
            if(userIdentity.getRole() == null)
                respAsJson(response,ErrCode.PERMISSION_DENIED);

            CompanyRoleEnum[] allowRoles = checkRole.allowRoles();

            boolean authFlag = false;
            for(CompanyRoleEnum allowRole : allowRoles){
                if(allowRole.getValue() == userIdentity.getRole()){
                    authFlag = true;
                    break;
                }
            }

            if(!authFlag)
                respAsJson(response,ErrCode.PERMISSION_DENIED);
        }

        return true;
    }


}
