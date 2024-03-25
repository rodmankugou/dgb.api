package com.verificer.exchange.admin.controller.debug;

import com.verificer.biz.beans.vo.req.OrdFormVo2;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.DebugController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "common")
@RequestMapping("/common")
@RestController
@DebugController
public class CommonDebugController extends BaseController {

    @Autowired
    BizService bizService;



    @ApiOperation(
            value = "判断是否登录",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    public String sync(@RequestBody OrdFormVo2 formVo) {
        return "Hello, Welcome";
    }



}
