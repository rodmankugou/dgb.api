package com.verificer.exchange.web.security.interceptor;


import com.verificer.ErrCode;
import com.verificer.UtilConstants;
import com.verificer.beans.UserIdentity;
import com.verificer.enums.ClientEnum;
import com.verificer.exchange.web.security.annotation.AllowUnActivation;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.exchange.web.security.annotation.ProcessToken;
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
 * 登录拦截器
 */
public class NeedLoginInterceptor extends BaseInterceptor {

    private static final Log logger = LogFactory.getLog(NeedLoginInterceptor.class);

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

        NeedLogin needLogin = mHandler.getMethodAnnotation(NeedLogin.class);
        ProcessToken processToken = mHandler.getMethodAnnotation(ProcessToken.class);


        // 勿删，插入校验是否处于更新状态
        if(redisUtil.exists(UtilConstants.SYSTEM_UPDATE_KEY)){
            System.out.println(request.getRemoteHost() + request.getRequestURI() + "系统维护中");
            respAsJson(response,ErrCode.SYSTEM_UPDATING,(String)redisUtil.get(UtilConstants.SYSTEM_UPDATE_KEY));
            return false;
        }

        boolean loginRequired = null != needLogin;

        if (loginRequired || (processToken != null)) {
            String requestToken = SecurityUtil.getToken();
            String client = SecurityUtil.getClient();
            if(SStringUtils.isAnyEmpty(client)){
                respAsJson(response,ErrCode.HTTP_HEAD_ERROR);
                return false;
            }
            if(SStringUtils.isAnyEmpty(requestToken)){
                if(loginRequired){
                    respAsJson(response,ErrCode.NEED_LOGIN);
                    return false;
                }else {
                    return true;
                }

            }


            LoginStat<UserIdentity> loginStat = loginMonitor.isLogin(client,requestToken,new UserIdentity());
            if(loginStat.isLogin()){
                request.setAttribute(SecurityConf.USER_IDENTITY_KEY, loginStat.getUserInfo());
                return true;
            }else {
                if(!loginStat.isLogin()){
                    if(LogoutType.OTHER_LOGIN.equals(loginStat.getLogoutType())){
                        if(loginRequired){
                            respAsJson(response,ErrCode.OTHER_LOGIN);
                            return false;
                        }else {
                            return true;
                        }

                    }else {
                        if(loginRequired){
                            respAsJson(response,ErrCode.NEED_LOGIN);
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
            }
        }

        return true;
    }


}
