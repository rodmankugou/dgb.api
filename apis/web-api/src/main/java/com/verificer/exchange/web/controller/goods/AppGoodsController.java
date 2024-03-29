package com.verificer.exchange.web.controller.goods;

import com.verificer.ErrCode;
import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.goods.AGoodsDtlVo;
import com.verificer.biz.beans.vo.goods.AGoodsVo;
import com.verificer.biz.beans.vo.goods.ASpecStockVo;
import com.verificer.biz.beans.vo.goods.ASpecVo;
import com.verificer.biz.beans.vo.goods.req.*;
import com.verificer.biz.beans.vo.shop.AShopBaseVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BaseException;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.controller.shop.ShopTool;
import com.verificer.exchange.web.security.annotation.ProcessToken;
import com.verificer.utils.FastJson;
import com.verificer.utils.RandomUtils;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
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

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "商品")
@RequestMapping("/goods")
@RestController
public class AppGoodsController extends BaseController {

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
    @ProcessToken
    @RequestMapping(value = "/index/page", method = RequestMethod.POST)
    public Object plaList(HttpServletRequest hReq, @RequestBody AIndexGoodsQryVo qryVo) {
        List<AGoodsVo> goodsVos = loadGoods(isMember(hReq));
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
        List<AGoodsVo> goodsVos = loadGoods(isMember(hReq));
        Object obj = SBigDecimalUtils.prcFormat2(goodsVos);
        return Response.dataSuccess(obj);
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
        List<AGoodsVo> goodsVos = loadGoods(isMember(hReq));
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
        List<AGoodsVo> goodsVos = loadGoods(isMember(hReq));
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
        SCheckUtil.notEmpty(qryVo.getType(),"Type");
        List<AGoodsVo> goodsVos = loadGoods(isMember(hReq));
        List<AGoodsVo> voList = new LinkedList<>();
        for(AGoodsVo vo : goodsVos){
            if(SStringUtils.isEmpty(qryVo.getsKey()))
                voList.add(vo);
            else {
                if(qryVo.getType() == 1){
                    if(vo.getName().contains(qryVo.getsKey()))
                        voList.add(vo);
                }else {
                    if("猫山王".contains(qryVo.getsKey()))
                        voList.add(vo);
                }
            }

        }

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
        AGoodsDtlVo vo = loadGoods(idVo.getId(),isMember(hReq));
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(vo));
    }







    public List<AGoodsVo> loadGoods(boolean umFlag)  {
        try {
            InputStream io = Thread.currentThread().getContextClassLoader().getResourceAsStream("goods.json");
            String json = IOUtils.toString(io,"utf-8");
            List<AGoodsDtlVo> list =  FastJson.parseArray(json,AGoodsDtlVo.class);
            for (AGoodsDtlVo goods : list)
                fillGoods(goods,umFlag);

            List<AGoodsVo> voList = new LinkedList<>();
            for(AGoodsDtlVo vo : list){
                AGoodsVo a = new AGoodsVo();
                SBeanUtils.copyProperties2(vo,a);
                voList.add(a);
            }
            return voList;
        } catch (IOException e) {
            throw new BaseException(ErrCode.SERVER_ERROR);
        }
    }

    public AGoodsDtlVo loadGoods(Long id,boolean umFlag)  {
        try {
            SCheckUtil.notEmpty(id,"id");
            InputStream io = Thread.currentThread().getContextClassLoader().getResourceAsStream("goods.json");
            String json = IOUtils.toString(io,"utf-8");
            List<AGoodsDtlVo> list =  FastJson.parseArray(json,AGoodsDtlVo.class);
            for(AGoodsDtlVo goods : list){
                if(goods.getId().equals(id)){
                    fillGoods(goods,umFlag);
                    return goods;

                }
            }
            return null;
        } catch (IOException e) {
            throw new BaseException(ErrCode.SERVER_ERROR);
        }
    }

    private void fillGoods(AGoodsDtlVo goods,Boolean umFlag){
        goods.setSubTitle("马来进口，口感一绝");
        goods.setStageCount(1005);
        goods.setBrandName("彭亨州1");
        goods.setImg0(goods.getImgList().split("@")[0]);
        goods.setPrice(goods.getSpecList().get(0).getPrice());
        goods.setOriPrice(goods.getSpecList().get(0).getOriPrice());
        List<ASpecVo> specVos = goods.getSpecList();
        for(ASpecVo spec : specVos){
            ASpecStockVo plaStock = new ASpecStockVo();
            plaStock.setStock(new BigDecimal(RandomUtils.getInt(1,100)));
            plaStock.setShopFlag(false);

            AShopBaseVo shop = ShopTool.getShop();
            ASpecStockVo shopStock = new ASpecStockVo();
            shopStock.setStock(new BigDecimal(RandomUtils.getInt(1,100)));
            shopStock.setShopFlag(true);
            shopStock.setShopId(shop.getId());
            List<ASpecStockVo> stockVos = new LinkedList<>();
            stockVos.add(plaStock);
            stockVos.add(shopStock);
            spec.setStocks(stockVos);
        }
        goods.setUserMemberFlag(umFlag);

        goods.setNearestShop(ShopTool.getShop());
    }


}
