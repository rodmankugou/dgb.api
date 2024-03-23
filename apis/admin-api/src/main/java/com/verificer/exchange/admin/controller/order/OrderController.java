package com.verificer.exchange.admin.controller.order;

import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.DbgOrderVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.decimal.SBigDecimalUtils;
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
@Api(tags = "订单")
@RequestMapping("/order")
@RestController
public class OrderController extends BaseController{

    @Autowired
    BizService bizService;




    @ApiOperation(
            value = "列表（分页）",
            response = DbgOrderVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody OrderPageVo qryVo) {
        List<DbgOrderVo> list = bizService.orderPage(qryVo);
        int count = bizService.orderCount(qryVo);
        return Response.listSuccess(count, SBigDecimalUtils.prcFormat2(list));
    }


    @ApiOperation(
            value = "详情",
            response = DbgOrderVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Response detail(@RequestBody IdVo qryVo) {
        DbgOrderVo vo = bizService.orderDetail(qryVo.getId());
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }


    @ApiOperation(
            value = "发货备注",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/transit/remark", method = RequestMethod.POST)
    public Response transitRemark(@RequestBody TransitRemarkVo formVo) {
//        if(formVo.getId() == null){
//            bizService.brandAdd(formVo);
//        }else{
//            bizService.brandUpd(formVo);
//        }

        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "发货",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/transit", method = RequestMethod.POST)
    public Response transit(@RequestBody IdVo formVo) {
//        if(formVo.getId() == null){
//            bizService.brandAdd(formVo);
//        }else{
//            bizService.brandUpd(formVo);
//        }

        return Response.simpleSuccess();
    }



}
