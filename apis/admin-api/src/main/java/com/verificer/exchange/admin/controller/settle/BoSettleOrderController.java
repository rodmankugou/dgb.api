package com.verificer.exchange.admin.controller.settle;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.EmptyVo;
import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.SettleOrdVo;
import com.verificer.biz.beans.vo.settle.SettleStaVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;
import com.verificer.biz.beans.vo.settle.req.SettleOrdQryVo;
import com.verificer.biz.beans.vo.settle.req.SettleTransferVo;
import com.verificer.biz.beans.vo.user.member.MemberStaVo;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "结算单")
@RequestMapping("/settle/order")
@RestController
public class BoSettleOrderController extends BaseController{

    @Autowired
    BizService bizService;

    @Autowired
    BaseSupService baseSupService;

    @ApiOperation(
            value = "列表(分页)",
            response = SettleOrdVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody SettleOrdQryVo reqVo) {
        List<SettleOrdVo> list = bizService.settleOrdPage(reqVo);
        int count = bizService.settleOrdCount(reqVo);
        return Response.listSuccess(count,list);
    }

    @ApiOperation(
            value = "详情",
            response = SettleOrdVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Response detail(@RequestBody IdVo reqVo) {
        SettleOrdVo vo = bizService.settleOrdDetail(reqVo);
        return Response.dataSuccess(vo);
    }

    @ApiOperation(
            value = "结算",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Response settleOrdTransfer(HttpServletRequest hReq, @RequestBody SettleTransferVo reqVo) {
       bizService.settleOrdTransfer(reqVo);
        return Response.simpleSuccess();
    }





}
