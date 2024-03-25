package com.verificer.exchange.admin.controller.sta;

import com.verificer.beans.EmptyVo;
import com.verificer.beans.TimeRangeVo;
import com.verificer.biz.beans.vo.sta.common.ChartTools;
import com.verificer.biz.beans.vo.sta.common.OneLatChart;
import com.verificer.biz.beans.vo.sta.common.Percent;
import com.verificer.biz.beans.vo.sta.common.Rank;
import com.verificer.biz.beans.vo.sta.home.*;
import com.verificer.biz.beans.vo.sta.req.SalesRateReqVo;
import com.verificer.biz.beans.vo.sta.sta.MerAndTimeVo;
import com.verificer.biz.beans.vo.sta.sta.RealTimeSta;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "平台统计")
@RequestMapping("/pla/sta")
@RestController
public class PlaStaController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "实时概况",
            response = RealTimeSta.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/realtime/sta", method = RequestMethod.POST)
    public Response sales(@RequestBody TimeRangeVo qryVo) {
        RealTimeSta vo = new RealTimeSta();
        vo.setTotalSku(new BigDecimal("1"));
        vo.setGoodsView(new BigDecimal("2"));
        vo.setGoodsCollect(new BigDecimal("3"));
        vo.setSalesGoods(new BigDecimal("4"));
        vo.setShopCollect(new BigDecimal("5"));
        vo.setOrderAmount(new BigDecimal("6"));
        vo.setOrderCount(new BigDecimal("7"));
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }


    @ApiOperation(
            value = "订单趋势",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/order/trend", method = RequestMethod.POST)
    public Response order(@RequestBody MerAndTimeVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(1000000,10000000));
    }


    @ApiOperation(
            value = "销售额",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sales/sta", method = RequestMethod.POST)
    public Response salesSta(@RequestBody MerAndTimeVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(1000000,10000000));
    }

    @ApiOperation(
            value = "销售额排行榜",
            response = Rank.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sales/rank", method = RequestMethod.POST)
    public Response salesRank(@RequestBody TimeRangeVo qryVo) {
        List<Rank> vo = new LinkedList<>();
        vo.add(new Rank("科兴园路店",new BigDecimal("523488823")));
        vo.add(new Rank("盐田店",new BigDecimal("48348488")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("3588384838")));
        return Response.dataSuccess(SBigDecimalUtils.lprcFormat2(vo));
    }



    @ApiOperation(
            value = "分类销售额",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/category/sta", method = RequestMethod.POST)
    public Response categorySta(@RequestBody TimeRangeVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(1000000,10000000));
    }

    @ApiOperation(
            value = "分类排行榜",
            response = Rank.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/category/rank", method = RequestMethod.POST)
    public Response categoryRank(@RequestBody TimeRangeVo qryVo) {
        List<Rank> vo = new LinkedList<>();
        vo.add(new Rank("猫山王",new BigDecimal("523488823")));
        vo.add(new Rank("青尼",new BigDecimal("48348488")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        vo.add(new Rank("金枕头",new BigDecimal("3588384838")));
        return Response.dataSuccess(SBigDecimalUtils.lprcFormat2(vo));
    }



    @ApiOperation(
            value = "品牌销售额",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/brand/sta", method = RequestMethod.POST)
    public Response brandSta(@RequestBody TimeRangeVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(1000000,10000000));
    }

    @ApiOperation(
            value = "品牌排行榜",
            response = Rank.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/brand/rank", method = RequestMethod.POST)
    public Response brandRank(@RequestBody TimeRangeVo qryVo) {
        List<Rank> vo = new LinkedList<>();
        vo.add(new Rank("彭亨州",new BigDecimal("523488823")));
        vo.add(new Rank("A",new BigDecimal("48348488")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        vo.add(new Rank("B",new BigDecimal("3588384838")));
        return Response.dataSuccess(SBigDecimalUtils.lprcFormat2(vo));
    }

}
