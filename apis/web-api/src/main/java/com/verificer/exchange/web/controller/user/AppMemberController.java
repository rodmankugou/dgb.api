package com.verificer.exchange.web.controller.user;

import com.verificer.base_user.service.BaseCustomerService;
import com.verificer.beans.*;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.wxpay.WxPayReqVo;
import com.verificer.biz.beans.vo.member.MemberTypeVo;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.enums.CustomerVerifiedStatus;
import com.verificer.exchange.web.controller.FileUploadController;
import com.verificer.exchange.web.security.annotation.AllowUnActivation;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.exchange.web.vo.OtherInfoVo;
import com.verificer.exchange.web.vo.UserInforVo;
import com.verificer.utils.FastJson;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.utils.web.UserIdentityUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/memberType/list", method = RequestMethod.POST)
    public Response memberTypeList(@RequestBody EmptyVo reqVo){
        List<MemberTypeVo> voList = bizService.memberTypeList();
        return Response.dataSuccess(voList);
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
        return Response.dataSuccess(vo);
    }






}
