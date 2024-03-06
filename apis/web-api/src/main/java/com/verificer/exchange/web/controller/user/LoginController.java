package com.verificer.exchange.web.controller.user;

import com.verificer.ErrCode;
import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.CustomerVo;
import com.verificer.beans.LoginRespVo;
import com.verificer.beans.LogoutReqVo;
import com.verificer.beans.UserIdentity;
import com.verificer.common.exception.BaseException;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.exchange.web.security.filter.EscapeWrapper;
import com.verificer.security.login.ILoginMonitor;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.web.common.enums.ClientType;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.verificer.enums.ClientEnum.APP;
import static com.verificer.enums.ClientEnum.WEB;

/**
 * 登录登出接口
 */

@Api(value = "/", description = "登陆登出", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    BaseCustomerService baseCustomerService;
    


    @Autowired
    ILoginMonitor loginMonitor;



    @ApiOperation(
            value = "获取服务器当前时间",
            response = LoginRespVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({

    })
    @RequestMapping(value = "/current/timestamp", method = RequestMethod.POST)
    @ResponseBody
    public Response curTimestamp(
            HttpServletRequest request) {

        return Response.dataSuccess(System.currentTimeMillis());

    }

    /**
     * 登录
     *
     * @return
     */
    @ApiOperation(
            value = "通过钱包地址登录",
            response = LoginRespVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "手机号码或邮箱",paramType = "form",required = true),
            @ApiImplicitParam(name = "timestamp", value = "时间戳，与当前服务时间偏差不能大于五秒",paramType = "form",required = true),
            @ApiImplicitParam(name = "signature", value = "签名,用户使用自己的私钥对address+timestamp进行签名",paramType = "form",required = true),
    })
    @RequestMapping(value = "/loginByAddress", method = RequestMethod.POST)
    @ResponseBody
    public Response loginByAddress(
            HttpServletRequest request) {

        EscapeWrapper wrapper = new EscapeWrapper(request);
        String address = wrapper.getParameter("address");
        String publicKey = wrapper.getParameter("publicKey");
        Long timestamp = Long.parseLong(wrapper.getParameter("timestamp"));
        String signature = wrapper.getParameter("signature");


        LoginRespVo resp = baseCustomerService.loginByAddress(getClient(),getIP(request),address,publicKey,timestamp,signature);
        if(!resp.isNeedGoogleAuth()){
            //如果登录成功，考虑到开启了谷歌安全验证的话，则需要通过谷歌验证后才算成功登录
            UserIdentity userIdentity = new UserIdentity(resp.getUserId());
            CustomerVo customerVo = baseCustomerService.getCustomerVo(userIdentity.getId());
            userIdentity.setHasActivation(customerVo.getIsActivation());
            String token = loginMonitor.login(getClient(),resp.getUserId().toString(),userIdentity,new UserIdentity());
            resp.setToken(token);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Response.dataSuccess(resp);

    }







    /**
     * 登出
     *
     * @return
     */
    @ApiOperation(
            value = "登出",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @NeedLogin
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        UserIdentity userIdentity = SecurityUtil.currentLogin();

        LogoutReqVo vo = new LogoutReqVo();
        vo.setUserId(userIdentity.getId());
        vo.setToken(SecurityUtil.getToken());
        baseCustomerService.logout(vo);
        loginMonitor.logout(SecurityUtil.getToken());
        return Response.simpleSuccess();
    }

    /**
     * 更换token
     *
     * @return
     */
    @ApiOperation(
            value = "更换token",
            response = String.class,
            httpMethod = "POST",
            notes = "如果旧token有效，则返回新token，否则返回null"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/token/replace", method = RequestMethod.POST)
    public Response replaceToken(HttpServletRequest request, HttpServletResponse response) {
        if(SStringUtils.isEmpty(SecurityUtil.getToken())){
            return Response.failed(ErrCode.PARAMS_ERR);
        }

        String token = loginMonitor.replaceToken(SecurityUtil.getClient(),SecurityUtil.getToken(),new UserIdentity());
        return Response.dataSuccess(token);
    }

}
