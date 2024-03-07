package com.verificer.exchange.admin.controller.common;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.AreaQryVo;
import com.verificer.beans.AreaVo;
import com.verificer.beans.NationalVo;
import com.verificer.exchange.admin.controller.BaseController;
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
@Api(tags = "区域")
@RequestMapping("/area")
@RestController
public class AreaController extends BaseController{

    @Autowired
    BaseSupService baseSupService;




    @ApiOperation(
            value = "获取地区列表（省、市、区/县）",
            response = AreaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object getAllNational(@RequestBody AreaQryVo qryVo) {
        List<AreaVo> list = baseSupService.areaList(qryVo);
        return Response.dataSuccess(list);
    }






}
