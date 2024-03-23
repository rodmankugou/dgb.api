package com.verificer.exchange.admin.controller.user;

import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.user.RefereeVo;
import com.verificer.biz.beans.vo.user.ReferrerStaVo;
import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.beans.vo.user.req.RefereeListReqVo;
import com.verificer.biz.beans.vo.user.req.RefereeStaReqVo;
import com.verificer.biz.beans.vo.user.req.ReferrerWithdrawPageReqVo;
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
@Api(tags = "用户")
@RequestMapping("/user")
@RestController
public class BoUserController extends BaseController{

    @Autowired
    BizService bizService;




    @ApiOperation(
            value = "列表（分页）",
            response = UserVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody UserPageVo qryVo) {
        List<UserVo> list = bizService.userPage(qryVo);
        int count = bizService.userCount(qryVo);
        return Response.listSuccess(count,list);
    }

    @ApiOperation(
            value = "详情",
            response = UserVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Response detail(@RequestBody IdVo idVo) {
        UserVo vo = bizService.userDetail(idVo);
        return Response.dataSuccess(vo);
    }

    @ApiOperation(
            value = "设置为引荐人",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/setReferrer", method = RequestMethod.POST)
    public Response setReferrer(@RequestBody UserSetRefVo reqVo) {
        bizService.userSetReferrer(reqVo);
        return Response.simpleSuccess();
    }




    @ApiOperation(
            value = "引荐人下线列表",
            response = RefereeVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/refereePage", method = RequestMethod.POST)
    public Response refereePage(@RequestBody RefereeListReqVo reqVo) {
        List<RefereeVo> voList = bizService.userRefereeList(reqVo);
        int count = bizService.userRefereeCount(reqVo);
        return Response.listSuccess(count, SBigDecimalUtils.prcFormat2(voList));
    }

    @ApiOperation(
            value = "引荐人提现记录",
            response = UserWithdrawVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/referrer/withdrawPage", method = RequestMethod.POST)
    public Response referrerWithdrawPage(@RequestBody ReferrerWithdrawPageReqVo reqVo) {
        List<UserWithdrawVo> voList = bizService.userWithdrawPage(reqVo);
        int count = bizService.userWithdrawCount(reqVo);
        return Response.listSuccess(count,SBigDecimalUtils.prcFormat2(voList));
    }


    @ApiOperation(
            value = "引荐人统计",
            response = ReferrerStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/refereeSta", method = RequestMethod.POST)
    public Response refereeSta(@RequestBody RefereeStaReqVo reqVo) {
        ReferrerStaVo vo = bizService.userRefereeSta(reqVo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }

}
