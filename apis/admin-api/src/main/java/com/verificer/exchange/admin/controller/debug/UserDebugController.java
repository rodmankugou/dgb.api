package com.verificer.exchange.admin.controller.debug;

import com.verificer.beans.EmptyVo;
import com.verificer.beans.WxLoginReqVo;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.beans.vo.user.req.BindMobileVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerFormVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerReviewVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerTransferVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.controller.debug.vo.ShopMemberVo;
import com.verificer.exchange.admin.security.annotation.DebugController;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.UuidUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "用户Debug")
@RequestMapping("/debug/user")
@RestController
@DebugController
public class UserDebugController extends BaseDebugController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "新增用户",
            response = Response.class,
            httpMethod = "POST",
            notes = "响应报文的data字段为订单ID"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response createUser() {

        return Response.dataSuccess(registerUser());

    }

    @ApiOperation(
            value = "新增会员用户",
            response = Response.class,
            httpMethod = "POST",
            notes = "响应报文的data字段为订单ID"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/createMember", method = RequestMethod.POST)
    public Response createMember(HttpServletRequest hReq) {
        createMember(hReq,MemberRefType.USER);

        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "新增门店会员用户",
            response = Response.class,
            httpMethod = "POST",
            notes = "响应报文的data字段为订单ID"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/createShopMember", method = RequestMethod.POST)
    public Response createShopMember(HttpServletRequest hReq) {
        createMember(hReq,MemberRefType.SHOP);

        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "新增门店会员用户,可以指定年份和月份",
            response = Response.class,
            httpMethod = "POST",
            notes = "响应报文的data字段为订单ID"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/createShopMember2", method = RequestMethod.POST)
    public Response createShopMember2(HttpServletRequest hReq,@RequestBody ShopMemberVo reqVo) throws SQLException {
        SCheckUtil.notEmpty(reqVo.getYear(),"Year");
        SCheckUtil.notEmpty(reqVo.getMonth(),"Month");

        createMember(hReq,MemberRefType.SHOP);
        Long id = Tools.getTableMaxId("dbg","settle");
        Long time = System.currentTimeMillis();
        time = SDateUtil.setYear(time,reqVo.getYear());
        time = SDateUtil.setNatureMonth(time,reqVo.getMonth()-1);
        time = SDateUtil.getMonthSTime(time);
        String sql = "update settle set next_cal_time = "+time+" where id = " +id;
        Tools.executeUpdate("dbg",sql);

        return Response.simpleSuccess();
    }

    private void createMember(HttpServletRequest hReq,MemberRefType refType){
        Long userId = registerUser();

        MemberChargeVo chargeVo = new MemberChargeVo();
        chargeVo.setUserId(userId);
        chargeVo.setIp(getIP(hReq));
        chargeVo.setMemberTypeId(getUserId());
        chargeVo.setReferrerId(MemberRefType.USER.getValue() == refType.getValue() ? getUserUid() : getShopId());
        chargeVo.setReferrerType(refType.getValue());
        PayReqVo payVo = bizService.memberCharge(chargeVo);

        PaySucVo paySucVo = new PaySucVo();
        paySucVo.setPayId(1L);
        paySucVo.setOrderId(payVo.getOrderId());
        paySucVo.setAmount(new BigDecimal(payVo.getAmount()));
        bizService.memberOnPaySuc(paySucVo);
    }





    private Long registerUser(){
        WxLoginReqVo regVo = new WxLoginReqVo();
        regVo.setCode(UuidUtils.newUuid());
        Long userId = bizService.wxLogin(regVo);

        BindMobileVo mobileVo = new BindMobileVo();
        mobileVo.setMobile("159"+ SStringUtils.generateRandomNumSequence(8));
        mobileVo.setUserId(userId);
        bizService.userBindMobile(mobileVo);
        return userId;
    }


    @ApiOperation(
            value = "提现",
            response = Response.class,
            httpMethod = "POST",
            notes = "响应报文的data字段为订单ID"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/withdrawApply", method = RequestMethod.POST)
    public Response withdrawApply(HttpServletRequest hReq) {
        withdrawApply0(hReq);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "提现并审核通过",
            response = Response.class,
            httpMethod = "POST",
            notes = "响应报文的data字段为订单ID"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/creatFinishWithdraw", method = RequestMethod.POST)
    public Response creatFinishWithdraw(HttpServletRequest hReq) {
        long id = withdrawApply0(hReq);
        ReferrerReviewVo reviewVo = new ReferrerReviewVo();
        reviewVo.setPassFlag(true);
        reviewVo.setStaffId(getStaffId());
        reviewVo.setStaffName(getStaffName());
        reviewVo.setId(id);
        bizService.referrerWithdrawReview(reviewVo);

        ReferrerTransferVo transferVo = new ReferrerTransferVo();
        transferVo.setId(id);
        transferVo.setStaffId(getStaffId());
        transferVo.setStaffName(getStaffName());
        transferVo.setCertificateImg(getImg());
        bizService.referrerWithdrawTransfer(transferVo);
        return Response.simpleSuccess();
    }

    private long  withdrawApply0(HttpServletRequest hReq){
        createMember(hReq,MemberRefType.USER);
        ReferrerFormVo f = new ReferrerFormVo();
        f.setUserId(getUserId());
        f.setAmount(new BigDecimal(2));
        return bizService.referrerWithdrawApply(f);
    }


}
