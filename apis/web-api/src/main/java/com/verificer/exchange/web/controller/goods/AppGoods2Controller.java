package com.verificer.exchange.web.controller.goods;

import com.verificer.ErrCode;
import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.goods.AGoodsDtlVo;
import com.verificer.biz.beans.vo.goods.AGoodsVo;
import com.verificer.biz.beans.vo.goods.ASpecStockVo;
import com.verificer.biz.beans.vo.goods.ASpecVo;
import com.verificer.biz.beans.vo.goods.enums.GoodsSearchType;
import com.verificer.biz.beans.vo.goods.enums.GoodsSortType;
import com.verificer.biz.beans.vo.goods.req.*;
import com.verificer.biz.beans.vo.shop.AShopBaseVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BaseException;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.controller.shop.ShopTool;
import com.verificer.exchange.web.security.annotation.ProcessToken;
import com.verificer.utils.FastJson;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "商品2")
@RequestMapping("/goods2")
@RestController
public class AppGoods2Controller extends BaseController {

    @Autowired
    BizService bizService;



    @ApiOperation(
            value = "首页商品列表",
            response = AGoodsVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/index/page", method = RequestMethod.POST)
    @ProcessToken
    public Object plaList(HttpServletRequest hReq, @RequestBody AIndexGoodsQryVo qryVo) {

        SCheckUtil.notEmpty(qryVo.getType(),"type");
        AGoodsQryVo vo = new AGoodsQryVo();
        vo.setPage(qryVo.getPage());
        vo.setPageSize(qryVo.getPageSize());
        vo.setLongitude(qryVo.getLongitude());
        vo.setLatitude(qryVo.getLatitude());
        vo.setUserMemberFlag(isMember(hReq));

        Integer type = qryVo.getType();
        if(type == 1){
            vo.setSortType(GoodsSortType.MUL.getValue());
        }else if(type == 2){
            vo.setSortType(GoodsSortType.SALES.getValue());
        }else if(type == 3){
            vo.setSortType(GoodsSortType.MUL.getValue());
            vo.setNonMemberFlag(true);
        }else if(type == 4){
            vo.setSortType(GoodsSortType.MARKET_TIME.getValue());
        }else {
            throw new BizErrMsgException("非法的参数值type="+type);
        }
        vo.setExcludeSaleOutFlag(true);
        List<AGoodsVo> goodsVos = bizService.appGoodList(vo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(goodsVos));
    }

    @ApiOperation(
            value = "云上商城-商品分类-商品列表",
            response = AGoodsVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @ProcessToken
    @RequestMapping(value = "/pla/cat/goods", method = RequestMethod.POST)
    public Object plaCatGoods(HttpServletRequest hReq, @RequestBody APlaGoodsQryVo qryVo) {
        AGoodsQryVo vo = new AGoodsQryVo();
        vo.setPage(qryVo.getPage());
        vo.setPageSize(qryVo.getPageSize());
        vo.setLongitude(qryVo.getLongitude());
        vo.setLatitude(qryVo.getLatitude());
        vo.setUserMemberFlag(isMember(hReq));

        vo.setSortType(GoodsSortType.MUL.getValue());

        if(qryVo.getNonMemberFlag() != null && qryVo.getNonMemberFlag() ){
            vo.setNonMemberFlag(true);
        }
        vo.setCatId(qryVo.getCatId());
        vo.setExcludeSaleOutFlag(false);


        List<AGoodsVo> goodsVos = bizService.appGoodList(vo);

        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(goodsVos));
    }

    @ApiOperation(
            value = "店铺商城-商品分类-商品列表",
            response = AGoodsVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @ProcessToken
    @RequestMapping(value = "/shop/cat/goods", method = RequestMethod.POST)
    public Object shopCatGoods(HttpServletRequest hReq, @RequestBody AShopGoodsQryVo qryVo) {
        SCheckUtil.notEmpty(qryVo.getCatId(),"catId");
        SCheckUtil.notEmpty(qryVo.getShopId(),"shopId");
        AGoodsQryVo vo = new AGoodsQryVo();
        vo.setPage(qryVo.getPage());
        vo.setPageSize(qryVo.getPageSize());
        vo.setLongitude(qryVo.getLongitude());
        vo.setLatitude(qryVo.getLatitude());
        vo.setUserMemberFlag(isMember(hReq));

        vo.setSortType(GoodsSortType.MUL.getValue());
        vo.setShopId(qryVo.getShopId());
        vo.setCatId(qryVo.getCatId());
        vo.setExcludeSaleOutFlag(false);



        List<AGoodsVo> goodsVos = bizService.appGoodList(vo);

        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(goodsVos));
    }

    @ApiOperation(
            value = "云上商城-全部商品-排序商品",
            response = AGoodsVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @ProcessToken
    @RequestMapping(value = "/pla/rank/goods", method = RequestMethod.POST)
    public Object rankGoods(HttpServletRequest hReq, @RequestBody ARankGoodsQryVo qryVo) {
        SCheckUtil.notEmpty(qryVo.getType(),"type");
        AGoodsQryVo vo = new AGoodsQryVo();
        vo.setPage(qryVo.getPage());
        vo.setPageSize(qryVo.getPageSize());
        vo.setLongitude(qryVo.getLongitude());
        vo.setLatitude(qryVo.getLatitude());
        vo.setUserMemberFlag(isMember(hReq));
        vo.setExcludeSaleOutFlag(false);

        Integer type = qryVo.getType();
        if(type == 1){
            vo.setSortType(GoodsSortType.MUL.getValue());
        }else if(type == 2){
            vo.setSortType(GoodsSortType.SALES.getValue());
        }else if(type == 3){
            vo.setSortType(GoodsSortType.PRICE.getValue());
            vo.setNonMemberFlag(true);
        }else  {
            throw new BaseException("非法的参数值type="+type);
        }

        List<AGoodsVo> goodsVos = bizService.appGoodList(vo);

        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(goodsVos));
    }

    @ApiOperation(
            value = "搜索",
            response = AGoodsVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @ProcessToken
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Object search(HttpServletRequest hReq, @RequestBody AGoodsSearchVo qryVo) {
        SCheckUtil.notEmpty(qryVo.getType(),"type");
        SCheckUtil.notEmpty(qryVo.getsKey(),"sKey");
        AGoodsQryVo vo = new AGoodsQryVo();
        vo.setPage(qryVo.getPage());
        vo.setPageSize(qryVo.getPageSize());
        vo.setLongitude(qryVo.getLongitude());
        vo.setLatitude(qryVo.getLatitude());
        vo.setUserMemberFlag(isMember(hReq));
        vo.setExcludeSaleOutFlag(false);


        Integer type = qryVo.getType();
        if(type == 1){
            vo.setSearchType(GoodsSearchType.GOODS.getValue());
        }else if(type == 2){
            vo.setSearchType(GoodsSearchType.CAT.getValue());
        }else  {
            throw new BaseException("非法的参数值type="+type);
        }
        vo.setsKey(qryVo.getsKey());
        vo.setSortType(GoodsSortType.MUL.getValue());

        List<AGoodsVo> goodsVos = bizService.appGoodList(vo);

        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(goodsVos));
    }

    @ApiOperation(
            value = "商品详情",
            response = AGoodsDtlVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @ProcessToken
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Object detail(HttpServletRequest hReq, @RequestBody AGoodsDtlReqVo idVo) {
        SCheckUtil.notEmpty(idVo.getId(),"id");
        AGoodsQryVo vo = new AGoodsQryVo();
        vo.setPage(1);
        vo.setPageSize(1);
        vo.setGoodsId(idVo.getId());
        vo.setSortType(GoodsSortType.PRICE.getValue());
        vo.setLongitude(idVo.getLongitude());
        vo.setLatitude(idVo.getLatitude());
        vo.setUserMemberFlag(isMember(hReq));
        vo.setExcludeSaleOutFlag(false);

        List<AGoodsVo> list = bizService.appGoodList(vo);
        AGoodsDtlVo dtlVo = null;
        if(list.size() > 0){
            dtlVo = new AGoodsDtlVo();
            SBeanUtils.copyProperties2(list.get(0),dtlVo);
        }
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(dtlVo));
    }









}
