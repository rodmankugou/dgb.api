package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.vo.AdjustVo;
import com.verificer.biz.beans.vo.req.AdjustBatchVo;
import com.verificer.biz.beans.vo.req.AdjustFormVo;
import com.verificer.biz.beans.vo.req.AdjustPageVo;
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
@Api(tags = "调货")
@RequestMapping("/adjust")
@RestController
public class AdjustController extends BaseController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表（分页）",
            response = AdjustVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody AdjustPageVo qryVo) {
        List<AdjustVo> list = bizService.adjustPage(qryVo);
        int count = bizService.adjustCount(qryVo);
        return Response.listSuccess(count,list);
    }


    @ApiOperation(
            value = "批量配货，页面BO-B4",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/batchAdjust", method = RequestMethod.POST)
    public Response batchAdjust(@RequestBody AdjustBatchVo formVo) {
        bizService.adjustBatch(formVo);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "补货/退货，页面BO-B11",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/adjust", method = RequestMethod.POST)
    public Response adjust(@RequestBody AdjustFormVo delVo) {
        bizService.adjust(delVo);
        return Response.simpleSuccess();
    }

}
