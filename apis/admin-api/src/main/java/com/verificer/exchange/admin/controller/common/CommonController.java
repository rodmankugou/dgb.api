package com.verificer.exchange.admin.controller.common;

import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.NationalVo;
import com.verificer.beans.rpc.RpcResponse;
import com.verificer.common.exception.BaseException;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.utils.SEnumUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "通用")
@RequestMapping("/common")
@RestController
public class CommonController extends BaseController{

    @Autowired
    BaseCustomerService baseCustomerService;




    @ApiOperation(
            value = "获取国家",
            response = NationalVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/national/list", method = RequestMethod.POST)
    public Object getAllNational() {
        List<NationalVo> list = baseCustomerService.queryEnableNational();
        return Response.dataSuccess(list);
    }






}
