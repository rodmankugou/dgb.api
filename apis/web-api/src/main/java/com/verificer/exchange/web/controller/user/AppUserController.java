package com.verificer.exchange.web.controller.user;

import com.verificer.biz.beans.vo.user.req.BindMobileVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.FileUploadController;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.utils.web.UserIdentityUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class AppUserController extends FileUploadController {
    @Autowired
    BizService bizService;


    /**
     * 登录
     *
     * @return
     */
    @ApiOperation(
            value = "绑定手机号码",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public Response setMobile(
            HttpServletRequest hReq,
            @RequestBody BindMobileVo reqVo) {
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();
        reqVo.setUserId(userId);

        bizService.userBindMobile(reqVo);
        return Response.simpleSuccess();
    }



}
