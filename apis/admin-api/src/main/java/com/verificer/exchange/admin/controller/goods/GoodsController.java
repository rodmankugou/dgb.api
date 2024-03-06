package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.vo.GoodsVo;
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
@RequestMapping("/goods")
@RestController
public class GoodsController extends BaseController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表（分页）",
            response = GoodsVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody GoodsQryVo qryVo) {
        List<GoodsVo> list = bizService.goodsPage(qryVo);
        int count = bizService.goodsCount(qryVo);
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
    public Response submit(@RequestBody GoodsFormVo formVo) {
        if(formVo.getId() == null){
            bizService.goodsAdd(formVo);
        }else{
            bizService.goodsUpd(formVo);
        }

        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "删除（放入回收站），商品列表的删除功能使用该接口，商品被删除后将被放入回收站",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/rubbish", method = RequestMethod.POST)
    public Response rubbish(@RequestBody GoodsRubbishVo reqVo) {
        bizService.goodsRubbish(reqVo);

        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "恢复，将回收站中的商品恢复",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    public Response recover(@RequestBody GoodsRecoverVo reqVo) {
        bizService.goodsRecover(reqVo);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "删除，删除回收站中的商品，回收站中的商品被删除后将不可恢复",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Response del(@RequestBody GoodsDelVo reqVo) {
        bizService.goodsDel(reqVo);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "上架/下架",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/updSaleFlag", method = RequestMethod.POST)
    public Response updSaleFlag(@RequestBody GoodsUpdSaleFlagVo reqVo) {
        bizService.goodsUpdSaleFlag(reqVo);
        return Response.simpleSuccess();
    }


}
