package com.verificer.exchange.web.controller.user;

import com.verificer.ErrCode;
import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.*;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.beans.wxpay.WxPayReqVo;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.vo.member.AppMemberVo;
import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.CustomerVerifiedStatus;
import com.verificer.exchange.web.controller.FileUploadController;
import com.verificer.exchange.web.security.annotation.AllowUnActivation;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.exchange.web.vo.OtherInfoVo;
import com.verificer.exchange.web.vo.UserInforVo;
import com.verificer.utils.FastJson;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.web.SecurityUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "会员")

@RestController
@RequestMapping("/member")
public class AppMemberController extends FileUploadController {


    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "获取会员套餐列表-E1会员页面",
            response = MemberTypeVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/type/list", method = RequestMethod.POST)
    public Response memberTypeList(@RequestBody EmptyVo reqVo){
        List<MemberTypeVo> voList = bizService.memberTypeList();
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(voList));
    }


    @ApiOperation(
            value = "会员充值",
            response = PayReqVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public Response charge(HttpServletRequest req,@RequestBody MemberChargeVo reqVo){
        String ip = getIP(req);
        reqVo.setIp(ip);
        Long userId = UserIdentityUtils.getUserIdentity(req).getId();
        reqVo.setUserId(userId);
        PayReqVo vo = bizService.memberCharge(reqVo);

        SCheckUtil.notEmpty(reqVo.getReferrerType(),"referrerType");
        if(reqVo.getReferrerType() != MemberRefType.SHOP.getValue())
            throw new BaseException(ErrCode.ONLY_CAN_CHARGE_MEMBER_BY_SHOP_NOW);

        //TODO 暂时未对接支付，先完成所有充值
        PaySucVo psv = new PaySucVo();
        psv.setOrderId(vo.getOrderId());
        psv.setPayId(1L);
        psv.setAmount(new BigDecimal("388"));
        bizService.memberOnPaySuc(psv);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }



    @ApiOperation(
            value = "获取当前用户的会员信息",
            response = AppMemberVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @NeedLogin
    public Response info(HttpServletRequest hReq,@RequestBody EmptyVo reqVo){
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();
        IdVo idVo = new IdVo(userId);
        AppMemberVo member = bizService.memberInfo(idVo);

        return Response.dataSuccess(member);
    }



}
