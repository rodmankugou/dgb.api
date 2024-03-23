package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.vo.evaluate.EvaluateQryVo;
import com.verificer.biz.beans.vo.evaluate.EvaluateReviewVo;
import com.verificer.biz.beans.vo.evaluate.EvaluateVo;
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

import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "商品评论")
@RequestMapping("/evaluate")
@RestController
public class BoEvaluateController extends BaseController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表",
            response = EvaluateVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody EvaluateQryVo reqVo) {
        List<EvaluateVo> list = bizService.evaluatePage(reqVo);
        int count = bizService.evaluateCount(reqVo);
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
    public Response review(@RequestBody EvaluateReviewVo reqVo) {
        bizService.evaluateReview(reqVo);
        return Response.simpleSuccess();
    }



}
