package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.vo.adjust.AdjOrderVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrderQryVo;
import com.verificer.biz.beans.vo.revise.req.ReviseFromVo;
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

import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "补/减货")
@RequestMapping("/revise")
@RestController
public class BoReviseController extends BaseController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "增/减库存",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response page(@RequestBody ReviseFromVo reqVo) {
        bizService.reviseCreate(reqVo);
        return Response.simpleSuccess();
    }




}
