package com.verificer.exchange.admin.controller.test;

import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
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
@Api(tags = "品牌")
@RequestMapping("/ot")
@RestController
public class OrdTmpController extends BaseController {

    @Autowired
    BizService bizService;



    @ApiOperation(
            value = "新增测试订单",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Response submit(@RequestBody OrdFormVo2 formVo) {
        Long orderId = bizService.orderAdd(formVo);

        return Response.dataSuccess(orderId);
    }



}
