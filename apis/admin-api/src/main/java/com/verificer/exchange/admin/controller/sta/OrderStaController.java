package com.verificer.exchange.admin.controller.sta;

import com.verificer.beans.TimeRangeVo;
import com.verificer.biz.beans.vo.sta.common.ChartTools;
import com.verificer.biz.beans.vo.sta.common.KvStaTool;
import com.verificer.biz.beans.vo.sta.common.KvStaVo;
import com.verificer.biz.beans.vo.sta.common.OneLatChart;
import com.verificer.biz.beans.vo.sta.sta.GoodsOverviewStaVo;
import com.verificer.biz.beans.vo.sta.sta.MerAndTimeVo;
import com.verificer.biz.beans.vo.sta.sta.OrderOverviewStaVo;
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

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "交易统计")
@RequestMapping("/order/sta")
@RestController
public class OrderStaController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "概况",
            response = OrderOverviewStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/overview", method = RequestMethod.POST)
    public Response overview(@RequestBody TimeRangeVo qryVo) {
        OrderOverviewStaVo vo = new OrderOverviewStaVo();
        vo.setTotal(new BigDecimal("1"));
        vo.setCount(new BigDecimal("2"));
        vo.setGoods(new BigDecimal("3"));
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }


    @ApiOperation(
            value = "订单趋势分析",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/trend", method = RequestMethod.POST)
    public Response visitTrend(@RequestBody TimeRangeVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(1000000,10000000));
    }


    @ApiOperation(
            value = "门店交易",
            response = KvStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/visit/sta", method = RequestMethod.POST)
    public Response salesSta(@RequestBody MerAndTimeVo qryVo) {

        return Response.dataSuccess(KvStaTool.genGoodsSta(12));
    }


}
