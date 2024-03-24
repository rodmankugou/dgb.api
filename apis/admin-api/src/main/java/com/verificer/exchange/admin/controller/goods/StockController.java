package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.constants.BizConst;
import com.verificer.biz.beans.vo.GoodsVo;
import com.verificer.biz.beans.vo.SpecVo;
import com.verificer.biz.beans.vo.stock.*;
import com.verificer.biz.beans.vo.stock.req.FromStockQryVo;
import com.verificer.biz.beans.vo.stock.req.StockMerQryVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "库存")
@RequestMapping("/stock")
@RestController
public class StockController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "店铺的库存概况",
            response = ShopStockStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/shop/stock/sta", method = RequestMethod.POST)
    public Response shopStockStaList( @RequestBody StockMerQryVo qryVo) {
        qryVo.setStageFlag(false);
        List<MerStockStaVo> list = bizService.merStockStaPage(qryVo);
        int count = bizService.merStockStaCount(qryVo);
        List<ShopStockStaVo> voList = new LinkedList<>();
        for(MerStockStaVo mvo : list){
            ShopStockStaVo svo = new ShopStockStaVo();
            SBeanUtils.copyProperties2(mvo,svo);
            voList.add(svo);
        }
        return Response.listSuccess(count,SBigDecimalUtils.lprcFormat2(voList));
    }

    @ApiOperation(
            value = "仓库的库存概况",
            response = StageStockStaVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/stage/stock/sta", method = RequestMethod.POST)
    public Response stageStockStaList( @RequestBody StockMerQryVo qryVo) {
        qryVo.setStageFlag(true);
        List<MerStockStaVo> list = bizService.merStockStaPage(qryVo);
        int count = bizService.merStockStaCount(qryVo);
        List<StageStockStaVo> voList = new LinkedList<>();
        for(MerStockStaVo mvo : list){
            StageStockStaVo svo = new StageStockStaVo();
            SBeanUtils.copyProperties2(mvo,svo);
            voList.add(svo);
        }
        return Response.listSuccess(count,SBigDecimalUtils.lprcFormat2(list));
    }

    @ApiOperation(
            value = "查询某个店铺/仓库库存",
            response = MerStockVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/mer/stock/list", method = RequestMethod.POST)
    public Response merStockPage( @RequestBody StockMerQryVo qryVo) {
        List<MerStockVo> list = bizService.merStockPage(qryVo);
        int count = bizService.merStockCount(qryVo);
        return Response.listSuccess(count,SBigDecimalUtils.lprcFormat2(list));
    }

    @ApiOperation(
            value = "发货方库存",
            response = FromStockVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/from/stock/list", method = RequestMethod.POST)
    public Response fromStockList( @RequestBody FromStockQryVo qryVo) {
        SCheckUtil.notEmpty(qryVo.getRelId(),"relId");
        if(BizConst.ADJ_OTHER_ID.equals(qryVo.getRelId())){
            List<GoodsVo> goodsList = bizService.goodsAll();

            List<FromStockVo> stockVoList = new LinkedList<>();
            for(GoodsVo g : goodsList){
                FromStockVo gs = new FromStockVo();
                stockVoList.add(gs);
                gs.setId(g.getId());
                gs.setName(g.getName());
                gs.setCount(BizConst.ADJ_OTHER_COUNT);
                gs.setImg(null);
                gs.setSpecList(new LinkedList<>());

                for(SpecVo s : g.getSpecList()){
                    FromStockVo ss = new FromStockVo();
                    gs.getSpecList().add(ss);
                    ss.setId(s.getId());
                    ss.setName(s.getName());
                    ss.setImg(s.getImg());
                    ss.setCount(BizConst.ADJ_OTHER_COUNT);
                }
            }
            return Response.dataSuccess(stockVoList);
        }

        StockMerQryVo reqVo = new StockMerQryVo();
        reqVo.setRelId(qryVo.getRelId());
        List<MerStockVo> list = bizService.merStockList(reqVo);
        List<FromStockVo> stockVoList = new LinkedList<>();
        for(MerStockVo g : list){
            FromStockVo gs = new FromStockVo();
            stockVoList.add(gs);
            gs.setId(g.getId());
            gs.setName(g.getName());
            gs.setCount(g.getCount().stripTrailingZeros().toPlainString());
            gs.setImg(null);
            gs.setSpecList(new LinkedList<>());

            for(MerStockVo s : g.getSpecList()){
                FromStockVo ss = new FromStockVo();
                gs.getSpecList().add(ss);
                ss.setId(s.getId());
                ss.setName(s.getName());
                ss.setImg(s.getImg());
                ss.setCount(s.getCount().stripTrailingZeros().toPlainString());
            }
        }

        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(list));
    }



}
