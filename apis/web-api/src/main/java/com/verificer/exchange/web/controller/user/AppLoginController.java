package com.verificer.exchange.web.controller.user;

import com.verificer.ErrCode;
import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.*;
import com.verificer.biz.beans.vo.user.AppLoginResp;
import com.verificer.biz.beans.vo.user.AppUserInfo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.security.login.ILoginMonitor;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.zookeeper.data.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录登出接口
 */

@Api(value = "/", description = "登陆登出", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value = "/login")
public class AppLoginController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AppLoginController.class);


    @Value("#{configProperties['LOGIN_ALIVE_TIME']}")
    private Integer LOGIN_ALIVE_TIME_MINUTE;

    @Autowired
    ILoginMonitor loginMonitor;

    @Autowired
    BizService bizService;



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
            value = "小程序登录",
            response = AppLoginResp.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/wx/login", method = RequestMethod.POST)
    @ResponseBody
    public Response loginByWx(
            @RequestBody WxLoginReqVo reqVo) {

        Long userId = bizService.wxLogin(reqVo);


        UserVo userVo = bizService.userDetail(new IdVo(userId));
        AppUserInfo ui = new AppUserInfo();
        SBeanUtils.copyProperties2(userVo,ui);

        String token = loginMonitor.login(getClient(),userId.toString(),new UserIdentity(userId),new UserIdentity());

        WxTokenVo tvo = new WxTokenVo();
        tvo.setToken(token);
        tvo.setExpireTime(System.currentTimeMillis()+(LOGIN_ALIVE_TIME_MINUTE/2)* SDateUtil.MS_PER_MINUTE);

        AppLoginResp resp = new AppLoginResp();
        resp.setUserInfo(ui);
        resp.setToken(tvo);
        return Response.dataSuccess(resp);
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
