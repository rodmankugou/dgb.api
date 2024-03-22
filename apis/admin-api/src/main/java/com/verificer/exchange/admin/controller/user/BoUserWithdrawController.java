package com.verificer.exchange.admin.controller.user;

import com.verificer.biz.beans.vo.user.UserWithdrawVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerReviewVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerTransferVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerWithdrawPageVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerWithdrawVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.entity.Staff;
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
@Api(tags = "提现")
@RequestMapping("/referrer/withdraw")
@RestController
public class BoUserWithdrawController extends BaseController{

    @Autowired
    BizService bizService;




    @ApiOperation(
            value = "列表（分页）",
            response = ReferrerWithdrawVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody ReferrerWithdrawPageVo qryVo) {
        List<ReferrerWithdrawVo> list = bizService.referrerWithdrawPage(qryVo);
        int count = bizService.referrerWithdrawCount(qryVo);
        return Response.listSuccess(count,list);
    }



    @ApiOperation(
            value = "审核",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public Response review(HttpServletRequest hReq,@RequestBody ReferrerReviewVo reqVo) {
        Staff staff = getCurLoginStaff(hReq);
        reqVo.setStaffId(staff.getId());
        reqVo.setStaffName(staff.getRealName());
        bizService.referrerWithdrawReview(reqVo);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "确认转账",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public Response review(HttpServletRequest hReq, @RequestBody ReferrerTransferVo reqVo) {
        Staff staff = getCurLoginStaff(hReq);
        reqVo.setStaffId(staff.getId());
        reqVo.setStaffName(staff.getRealName());
        bizService.referrerWithdrawTransfer(reqVo);
        return Response.simpleSuccess();
    }



}
