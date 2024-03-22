package com.verificer.exchange.admin.controller.settle;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.beans.EmptyVo;
import com.verificer.biz.beans.vo.settle.PlaIncomeLogVo;
import com.verificer.biz.beans.vo.settle.SettleStaVo;
import com.verificer.biz.beans.vo.settle.req.PlaIncomeLogQryVo;
import com.verificer.biz.beans.vo.settle.req.SettleStaQryVo;
import com.verificer.biz.beans.vo.user.member.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.ion.Decimal;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "结算概况")
@RequestMapping("/settle")
@RestController
public class BoSettleController extends BaseController{

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
    public Response sta(@RequestBody EmptyVo reqVo) {
        SettleStaVo vo = bizService.settleSta(new SettleStaQryVo());
        return Response.dataSuccess(vo);
    }

    @ApiOperation(
            value = "平台收支纪录",
            response = PlaIncomeLogVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/plaIncomeLog/page", method = RequestMethod.POST)
    public Response plaIncomeLogPage(@RequestBody PlaIncomeLogQryVo reqVo) {
        List<PlaIncomeLogVo> vo = bizService.plaIncomeLogPage(reqVo);
        int count = bizService.plaIncomeLogCount(reqVo);
        return Response.listSuccess(count,vo);
    }





}
