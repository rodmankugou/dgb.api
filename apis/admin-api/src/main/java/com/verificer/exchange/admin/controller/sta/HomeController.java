package com.verificer.exchange.admin.controller.sta;

import com.verificer.beans.EmptyVo;
import com.verificer.biz.beans.vo.sta.common.ChartTools;
import com.verificer.biz.beans.vo.sta.common.OneLatChart;
import com.verificer.biz.beans.vo.sta.common.Percent;
import com.verificer.biz.beans.vo.sta.common.Rank;
import com.verificer.biz.beans.vo.sta.home.*;
import com.verificer.biz.beans.vo.sta.req.SalesRateReqVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.reflect.SBeanUtils;
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
@Api(tags = "首页概况")
@RequestMapping("/home")
@RestController
public class HomeController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "销售额统计",
            response = HomeSalesVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sales/sta/", method = RequestMethod.POST)
    public Response sales(@RequestBody EmptyVo qryVo) {
        HomeSalesVo vo = new HomeSalesVo();
        vo.setTotal(new BigDecimal("10384431"));
        vo.setDayAvg(new BigDecimal("23415"));
        vo.setDayRat(new BigDecimal("0.133"));
        vo.setWeekRat(new BigDecimal("-0.231"));
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }


    @ApiOperation(
            value = "访问量",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/visit/sta", method = RequestMethod.POST)
    public Response visit(@RequestBody EmptyVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(1000000,10000000));
    }

    @ApiOperation(
            value = "支付笔数",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/pay/sta", method = RequestMethod.POST)
    public Response pay(@RequestBody EmptyVo qryVo) {

        return Response.dataSuccess(ChartTools.genOneLat(100000,1000000));
    }

    @ApiOperation(
            value = "线上购物转化率",
            response = HomeConversionVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/online/conversion", method = RequestMethod.POST)
    public Response conversion(@RequestBody EmptyVo qryVo) {
        HomeConversionVo vo = new HomeConversionVo();
        vo.setAvg(new BigDecimal("0.663"));
        vo.setDayRise(new BigDecimal("0.392"));
        vo.setWeekRise(new BigDecimal("-0.381"));
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }


    @ApiOperation(
            value = "销售额/转化率",
            response = HomeSaleAndVisitVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sales/conversion", method = RequestMethod.POST)
    public Response salesAndVisit(@RequestBody EmptyVo qryVo) {
        HomeSaleAndVisitVo vo = new HomeSaleAndVisitVo();
        vo.setSales(ChartTools.genOneLat(1000000,10000000));
        vo.setVisit(ChartTools.genOneLat(1000000,10000000));
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }

    @ApiOperation(
            value = "销售额/转化率",
            response = Rank.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sales/rank", method = RequestMethod.POST)
    public Response salesRank(@RequestBody EmptyVo qryVo) {
        List<Rank> vo = new LinkedList<>();
        vo.add(new Rank("科兴园路店",new BigDecimal("323488823")));
        vo.add(new Rank("盐田店",new BigDecimal("28348488")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        vo.add(new Rank("福田总店",new BigDecimal("2588384838")));
        return Response.dataSuccess(SBigDecimalUtils.lprcFormat2(vo));
    }

    @ApiOperation(
            value = "热门搜索-搜索用户数",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/search/total", method = RequestMethod.POST)
    public Response searchTotal(@RequestBody EmptyVo qryVo) {
        OneLatChart vo = ChartTools.genOneLat(1000000,10000000);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }

    @ApiOperation(
            value = "热门搜索-人均",
            response = OneLatChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/search/avg", method = RequestMethod.POST)
    public Response searchAvg(@RequestBody EmptyVo qryVo) {
        OneLatChart vo = ChartTools.genOneLat(1000000,10000000);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }

    @ApiOperation(
            value = "热门搜索-排行榜",
            response = Rank.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/search/rank", method = RequestMethod.POST)
    public Response searchRank(@RequestBody EmptyVo qryVo) {
        List<Rank> vo = new LinkedList<>();
        vo.add(new Rank("猫山王",new BigDecimal("13884"),new BigDecimal("0.031")));
        vo.add(new Rank("金枕头",new BigDecimal("2334"),new BigDecimal("0.23")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        vo.add(new Rank("青尼",new BigDecimal("1038"),new BigDecimal("-0.308")));
        return Response.dataSuccess(SBigDecimalUtils.lprcFormat2(vo));
    }

    @ApiOperation(
            value = "销售额分类占比",
            response = Percent.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/sales/rate", method = RequestMethod.POST)
    public Response salesRate(@RequestBody SalesRateReqVo qryVo) {
        List<Percent> vo = new LinkedList<>();
        vo.add(new Percent("猫山王",new BigDecimal("13884"),new BigDecimal("0.17")));
        vo.add(new Percent("金枕头",new BigDecimal("2334"),new BigDecimal("0.13")));
        vo.add(new Percent("青尼",new BigDecimal("1038"),new BigDecimal("0.1")));
        vo.add(new Percent("青尼",new BigDecimal("1038"),new BigDecimal("0.1")));
        vo.add(new Percent("青尼",new BigDecimal("1038"),new BigDecimal("0.1")));
        vo.add(new Percent("青尼",new BigDecimal("1038"),new BigDecimal("0.1")));
        vo.add(new Percent("青尼",new BigDecimal("1038"),new BigDecimal("0.1")));
        vo.add(new Percent("青尼",new BigDecimal("1038"),new BigDecimal("0.1")));
        vo.add(new Percent("青尼",new BigDecimal("1038"),new BigDecimal("0.1")));
        return Response.dataSuccess(SBigDecimalUtils.lprcFormat2(vo));
    }

    @ApiOperation(
            value = "门店转化率",
            response = ShopConversionVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/shop/conversion", method = RequestMethod.POST)
    public Response shopConversion(@RequestBody EmptyVo qryVo) {
        List<ShopConversionVo> vo = new LinkedList<>();
        vo.add(new ShopConversionVo("科技园店",new BigDecimal("0.5")));
        vo.add(new ShopConversionVo("福田店",new BigDecimal("0.3")));
        vo.add(new ShopConversionVo("南山店",new BigDecimal("0.4")));
        return Response.dataSuccess(SBigDecimalUtils.lprcFormat2(vo));
    }

    @ApiOperation(
            value = "底部转化率",
            response = MulConversionChart.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/mul/conversion", method = RequestMethod.POST)
    public Response mulConversion(@RequestBody EmptyVo qryVo) {
        List<MulConChartItem> items = new LinkedList<>();
        for(int i = 0 ;i < 24; i++){
            MulConChartItem item = new MulConChartItem();
            item.setTrade(new BigDecimal(RandomUtils.getInt(100,300)));
            item.setVisit(new BigDecimal(RandomUtils.getInt(500,1000)));
            items.add(item);
        }
        MulConversionChart vo =  new MulConversionChart(ChartTools.genLastNTime(Calendar.HOUR,24),
                items);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }
}
