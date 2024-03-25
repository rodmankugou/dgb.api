package com.verificer.exchange.admin.controller.sta;

import com.verificer.beans.TimeRangeVo;
import com.verificer.biz.beans.vo.sta.common.*;
import com.verificer.biz.beans.vo.sta.sta.GoodsOverviewStaVo;
import com.verificer.biz.beans.vo.sta.sta.MerAndTimeVo;
import com.verificer.biz.beans.vo.sta.sta.RealTimeSta;
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

import java.math.BigDecimal;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "商品统计")
@RequestMapping("/goods/sta")
@RestController
public class GoodsStaController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "概况",
            response = GoodsOverviewStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/overview", method = RequestMethod.POST)
    public Response sales(@RequestBody TimeRangeVo qryVo) {
        GoodsOverviewStaVo vo = new GoodsOverviewStaVo();
        vo.setTotal(new BigDecimal("1"));
        vo.setVisit(new BigDecimal("2"));
        vo.setCart(new BigDecimal("3"));
        vo.setSalesGoods(new BigDecimal("4"));
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }


    @ApiOperation(
            value = "商品浏览趋势",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/visit/trend", method = RequestMethod.POST)
    public Response visitTrand(@RequestBody TimeRangeVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(1000000,10000000));
    }


    @ApiOperation(
            value = "销售额",
            response = KvStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sales/sta", method = RequestMethod.POST)
    public Response salesSta(@RequestBody MerAndTimeVo qryVo) {

        return Response.dataSuccess(KvStaTool.genGoodsSta(12));
    }


}
