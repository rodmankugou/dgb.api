package com.verificer.exchange.admin.controller.settle;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.EmptyVo;
import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.SettleItemVo;
import com.verificer.biz.beans.vo.settle.SettleStaVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;
import com.verificer.biz.beans.vo.settle.req.SettleItemQryVo;
import com.verificer.biz.beans.vo.settle.req.SettleStaQryVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.check.SCheckUtil;
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
@Api(tags = "结算明细")
@RequestMapping("/settle/item")
@RestController
public class BoSettleItemController extends BaseController{

    @Autowired
    BizService bizService;

    @Autowired
    BaseSupService baseSupService;

    @ApiOperation(
            value = "概况",
            response = SettleStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sta", method = RequestMethod.POST)
    public Response sta(@RequestBody SettleStaQryVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getShopId(),"Shop Id");
        SettleStaVo vo = bizService.settleSta(reqVo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }

    @ApiOperation(
            value = "结算明细",
            response = SettleItemVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody SettleItemQryVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getOrderId(),"OrderId");
        List<SettleItemVo> vo = bizService.settleItemPage(reqVo);
        int count = bizService.settleItemCount(reqVo);
        return Response.listSuccess(count, SBigDecimalUtils.lprcFormat2(vo));
    }





}
