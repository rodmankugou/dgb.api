package com.verificer.exchange.admin.controller.user;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.base.sup.itf.CfgCodes;
import com.verificer.beans.EmptyVo;
import com.verificer.biz.beans.vo.user.member.*;
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
import software.amazon.ion.Decimal;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "会员")
@RequestMapping("/member")
@RestController
public class BoMemberController extends BaseController{

    @Autowired
    BizService bizService;

    @Autowired
    BaseSupService baseSupService;




    @ApiOperation(
            value = "会员概况",
            response = MemberStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sta", method = RequestMethod.POST)
    public Response sta(@RequestBody EmptyVo reqVo) {
        MemberStaVo vo = bizService.memberSta();
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }

    @ApiOperation(
            value = "会员排行榜（按省份）",
            response = MemberRankVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public Response rank(@RequestBody EmptyVo reqVo) {
        List<MemberRankVo> vo = bizService.memberRank();
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }

    @ApiOperation(
            value = "会员列表（分页）",
            response = MemberVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody MemberPageVo reqVo) {
        List<MemberVo> list =bizService.memberPage(reqVo);
        int count = bizService.memberCount(reqVo);
        return Response.listSuccess(count,SBigDecimalUtils.lprcFormat2(list));
    }


    @ApiOperation(
            value = "积分管理-获取积分汇率",
            response = Decimal.class,
            httpMethod = "POST",
            notes = "返回值为浮点数类型"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/integral/getRate", method = RequestMethod.POST)
    public Response getIntegralRate(@RequestBody EmptyVo reqVo) {
        BigDecimal rate = new BigDecimal(baseSupService.getCfg(CfgCodes.INTEGRAL_PER_ORD_AMOUNT));
        return Response.dataSuccess(rate);
    }




    @ApiOperation(
            value = "积分管理-设置积分汇率",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/integral/setRate", method = RequestMethod.POST)
    public Response setIntegralRate(@RequestBody UpdIntegralRateVo reqVo) {
        SCheckUtil.lgThanAndNotNull(reqVo.getRate(),true,BigDecimal.ZERO,"Rate");
        baseSupService.updCfg(CfgCodes.INTEGRAL_PER_ORD_AMOUNT,reqVo.getRate().toPlainString());
        return Response.simpleSuccess();
    }



}
