package com.verificer.exchange.admin.controller.shop;

import com.verificer.biz.beans.vo.ShopVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@RequestMapping("/shop")
@RestController
public class ShopController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "列表（不分页）",
            response = ShopVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response list(@RequestBody ShopListVo qryVo) {
        List<ShopVo> list = bizService.shopList(qryVo);
        return Response.dataSuccess(list);
    }

    @ApiOperation(
            value = "列表（分页）",
            response = ShopVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody ShopPageVo qryVo) {
        List<ShopVo> list = bizService.shopPage(qryVo);
        int count = bizService.shopCount(qryVo);
        return Response.listSuccess(count,list);
    }


    @ApiOperation(
            value = "新增/修改",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Response submit(@RequestBody ShopFormVo formVo) {
        if(formVo.getId() == null){
            bizService.shopAdd(formVo);
        }else{
            bizService.shopUpd(formVo);
        }

        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "删除",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Response del(@RequestBody ShopDelVo delVo) {
        bizService.shopDel(delVo);

        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "冻结/解冻",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/updFrozenSta", method = RequestMethod.POST)
    public Response updFrozenSta(@RequestBody ShopFrozenVo reqVo) {
        bizService.shopUpdFrozenSta(reqVo);

        return Response.simpleSuccess();
    }

}
