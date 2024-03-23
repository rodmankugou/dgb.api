package com.verificer.exchange.admin.controller.debug;

import com.verificer.beans.WxLoginReqVo;
import com.verificer.beans.pay.PayReqVo;
import com.verificer.beans.pay.PaySucVo;
import com.verificer.biz.beans.enums.EvaluateSta;
import com.verificer.biz.beans.enums.MemberRefType;
import com.verificer.biz.beans.vo.member.req.MemberChargeVo;
import com.verificer.biz.beans.vo.user.req.BindMobileVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerFormVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerReviewVo;
import com.verificer.biz.beans.vo.user.withdraw.ReferrerTransferVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.debug.vo.ShopMemberVo;
import com.verificer.exchange.admin.controller.debug.vo.SimEvaVo;
import com.verificer.exchange.admin.security.annotation.DebugController;
import com.verificer.exchange.admin.unittest.Tools;
import com.verificer.tools.db.DbTools;
import com.verificer.utils.RandomUtils;
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

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "评论Debug")
@RequestMapping("/debug/evaluate")
@RestController
@DebugController
public class EvaluateDebugController extends BaseDebugController{

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
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response create() throws SQLException {

        SimEvaVo e = new SimEvaVo();
        e.setStatus(EvaluateSta.WAIT_REVIEW.getValue());
        e.setUserId(1L);
        e.setUserName("Jordan");
        e.setOrderId(2L);
        e.setOrderDetailId(3L);
        e.setScore(RandomUtils.getInt(1,5));
        e.setGoodsId(4L);
        e.setGoodsName("新的测试榴莲");
        e.setSpecId(5L);
        e.setSpecName("一人享用");
        e.setSpecImg("https://dbg.obs.cn-south-1.myhuaweicloud.com/temp/goods/1.jpg");
        if(e.getScore() >= 4)
            e.setComment("不错的，给"+e.getScore()+"分");
        else
            e.setComment("不行，给"+e.getScore()+"分");
        e.setImgList(getImg());
        e.setCreateTime(System.currentTimeMillis());
        DbTools.insertSelective("dbg","evaluate",e);

        return Response.simpleSuccess();

    }



}
