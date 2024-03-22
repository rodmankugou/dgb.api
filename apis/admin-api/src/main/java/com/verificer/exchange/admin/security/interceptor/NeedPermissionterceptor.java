package com.verificer.exchange.admin.security.interceptor;


import com.verificer.ErrCode;
import com.verificer.base.auth.service.BaseAuthService;
import com.verificer.beans.UserIdentity;
import com.verificer.exchange.admin.security.annotation.NeedPermission;
import com.verificer.utils.RedisUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;


/**
 * 登录拦截器
 */
@Component
public class NeedPermissionterceptor extends BaseInterceptor {

    private static final Log logger = LogFactory.getLog(NeedPermissionterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BaseAuthService baseAuthService;

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

        NeedPermission needPermission = mHandler.getMethodAnnotation(NeedPermission.class);
        if(needPermission == null)
            return true;


        String needAuthStr = needPermission.needAuth();
        if(!SStringUtils.isEmpty(needAuthStr)){
            UserIdentity userIdentity = SecurityUtil.currentLogin();
            Set<String> authSet = baseAuthService.getUserAuthSet(userIdentity.getId());

            boolean isPermission = false;
            List<String> needs = SStringUtils.split(needAuthStr,"\\|\\|");
            for(String need : needs){
                if(SStringUtils.isEmpty(need))
                    continue;
                if(authSet.contains(need)){
                    isPermission = true;
                    break;
                }
            }

            if(!isPermission)
                respAsJson(response,ErrCode.PERMISSION_DENIED);

        }


        return true;
    }


}
