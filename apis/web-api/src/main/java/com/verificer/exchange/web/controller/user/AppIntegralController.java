package com.verificer.exchange.web.controller.user;

import com.verificer.ErrCode;
import com.verificer.beans.EmptyVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.vo.integral.AppIntegralLogVo;
import com.verificer.biz.beans.vo.integral.IntegralListVo;
import com.verificer.biz.beans.vo.member.AppMemberVo;
import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BaseException;
import com.verificer.exchange.web.controller.FileUploadController;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.web.UserIdentityUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Api(tags = "积分")
@RestController
@RequestMapping("/integral")
public class AppIntegralController extends FileUploadController {


    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "获取用户积分余额",
            response = BigDecimal.class,
            httpMethod = "POST",
            notes = "直接返回数值"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    public Response balance(HttpServletRequest hReq,@RequestBody EmptyVo reqVo){
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();
        BigDecimal balance = bizService.integralBalance(new IdVo(userId));
        balance = balance.setScale(0,BigDecimal.ROUND_DOWN);
        return Response.dataSuccess(balance);
    }


    @ApiOperation(
            value = "积分记录",
            response = AppIntegralLogVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response balance(HttpServletRequest hReq,@RequestBody IntegralListVo reqVo){
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();
        reqVo.setUserId(userId);
        List<AppIntegralLogVo> list = bizService.integralList(reqVo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(list));
    }



}
