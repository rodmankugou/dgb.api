package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.enums.AdjShortType;
import com.verificer.biz.beans.vo.AdjustVo;
import com.verificer.biz.beans.vo.adjust.AdjOrderVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrderQryVo;
import com.verificer.biz.beans.vo.req.AdjustPageVo;
import com.verificer.biz.beans.vo.req.adjust.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "调货单")
@RequestMapping("/adjust/order")
@RestController
public class AdjustOrderController extends BaseController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表（分页）",
            response = AdjOrderVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody AdjOrderQryVo qryVo) {
        List<AdjOrderVo> list = bizService.adjOrdPage(qryVo);
        int count = bizService.adjOrdCount(qryVo);
        return Response.listSuccess(count, SBigDecimalUtils.prcFormat2(list));
    }



    @ApiOperation(
            value = "创建配货单",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response create(@RequestBody AdjOrdFormVo formVo) {

        bizService.adjOrdCreate(formVo);
        return Response.simpleSuccess();
    }



    @ApiOperation(
            value = "确认配货单",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public Response confirm(@RequestBody AdjOrdConfirmVo formVo) {

        bizService.adjOrdConfirm(formVo);
        return Response.simpleSuccess();
    }

}
