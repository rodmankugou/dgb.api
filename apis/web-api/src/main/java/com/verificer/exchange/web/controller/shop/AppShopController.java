package com.verificer.exchange.web.controller.shop;

import com.verificer.ErrCode;
import com.verificer.beans.EmptyVo;
import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.cart.req.CartQryVo;
import com.verificer.biz.beans.vo.goods.AGoodsDtlVo;
import com.verificer.biz.beans.vo.goods.AGoodsVo;
import com.verificer.biz.beans.vo.goods.ASpecStockVo;
import com.verificer.biz.beans.vo.goods.ASpecVo;
import com.verificer.biz.beans.vo.goods.req.AIndexGoodsQryVo;
import com.verificer.biz.beans.vo.shop.AShopBaseVo;
import com.verificer.biz.beans.vo.shop.AShopDtlVo;
import com.verificer.biz.beans.vo.shop.ShopQueryVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BaseException;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.utils.FastJson;
import com.verificer.utils.SBeanUtils;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "店铺")
@RequestMapping("/shop")
@RestController
public class AppShopController extends BaseController {

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表-分页",
            response = AShopBaseVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Object plaList(HttpServletRequest hReq, @RequestBody ShopQueryVo qryVo) {
        List<AShopBaseVo> list = new LinkedList<>();
        AShopBaseVo shop = ShopTool.getShop();

        if(SStringUtils.isEmpty(qryVo.getsKey()) || shop.getName().contains(qryVo.getsKey()) )
            list.add(shop);
        return Response.listSuccess(list.size(),list);
    }

    @ApiOperation(
            value = "最近店铺",
            response = AShopBaseVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/nearest", method = RequestMethod.POST)
    public Object nearest(HttpServletRequest hReq, @RequestBody EmptyVo idVo) {

        return Response.dataSuccess(ShopTool.getShop());
    }

    @ApiOperation(
            value = "门店详情",
            response = AShopDtlVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Object detail(HttpServletRequest hReq, @RequestBody IdVo idVo) {

        return Response.dataSuccess(ShopTool.shopDtl());
    }









}
