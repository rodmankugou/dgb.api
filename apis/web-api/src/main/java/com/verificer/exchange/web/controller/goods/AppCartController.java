package com.verificer.exchange.web.controller.goods;

import com.verificer.beans.EmptyVo;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.cart.CartVo;
import com.verificer.biz.beans.vo.cart.ShopCartVo;
import com.verificer.biz.beans.vo.cart.req.CartAddVo;
import com.verificer.biz.beans.vo.cart.req.CartJoinVo;
import com.verificer.biz.beans.vo.cart.req.CartQryVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.utils.web.UserIdentityUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "购物车")
@RequestMapping("/cart")
@RestController
public class AppCartController extends BaseController {

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "购物车内容-云上商城",
            response = CartVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/pla/list", method = RequestMethod.POST)
    public Object plaList(HttpServletRequest hReq, @RequestBody EmptyVo qryVo) {
        CartQryVo reqVo = new CartQryVo();
        reqVo.setUserId(UserIdentityUtils.getUserIdentity(hReq).getId());
        List<CartVo> list = bizService.cartPlaList(reqVo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(list));
    }



    @ApiOperation(
            value = "购物车内容-门店",
            response = BrandVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/shop/list", method = RequestMethod.POST)
    public Object shopList(HttpServletRequest hReq, @RequestBody EmptyVo qryVo) {
        CartQryVo reqVo = new CartQryVo();
        reqVo.setUserId(UserIdentityUtils.getUserIdentity(hReq).getId());
        List<ShopCartVo> list = bizService.cartShopList(reqVo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(list));
    }



    @ApiOperation(
            value = "加入购物车",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Object join(HttpServletRequest hReq, @RequestBody CartJoinVo reqVo) {
        reqVo.setUserId(UserIdentityUtils.getUserIdentity(hReq).getId());
        bizService.cartJoin(reqVo);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "增减数量",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest hReq, @RequestBody CartAddVo reqVo) {
        reqVo.setUserId(UserIdentityUtils.getUserIdentity(hReq).getId());
        bizService.cartAdd(reqVo);
        return Response.simpleSuccess();
    }


}
