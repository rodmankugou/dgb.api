package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.enums.AdjShortType;
import com.verificer.biz.beans.enums.AdjType;
import com.verificer.biz.beans.vo.AdjustVo;
import com.verificer.biz.beans.vo.req.AdjustPageVo;
import com.verificer.biz.beans.vo.req.adjust.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.common.exception.BizErrMsgException;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "调货")
@RequestMapping("/adjust")
@RestController
public class AdjustController extends BaseController{

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表（分页）",
            response = AdjustVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody AdjustPageVo qryVo) {
        qryVo.setMerId("d000e3c443794213a92d61a9c6f6f6fe");
        List<AdjustVo> list = bizService.adjustPage(qryVo);
        int count = bizService.adjustCount(qryVo);
        return Response.listSuccess(count,list);
    }



    @ApiOperation(
            value = "仓库补货，页面BO-B13",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/stage/supply", method = RequestMethod.POST)
    public Response stageSupply(@RequestBody AdjStageInFormVo formVo) {
        AdjFormVo a = new AdjFormVo();
        a.setShortType(AdjShortType.STAGE_SUPPLY.getValue());
        a.setFromId(null);
        a.setToId(formVo.getStageId());
        a.setGoodsId(formVo.getGoodsId());
        a.setSpecId(formVo.getSpecId());
        a.setCount(formVo.getCount());
        a.setRealCount(formVo.getCount());
        a.setRemark(null);
        bizService.adjust(a);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "批量配货，页面BO-B4",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/stage/to/shop/batch", method = RequestMethod.POST)
    public Response batchAdjust(@RequestBody AdjShopBatchInVo formVo) {
        if(formVo.getItems() == null || formVo.getItems().size() == 0)
            throw new BizErrMsgException("Parameter items can not be empty");

        List<AdjFormVo> formList = new LinkedList<>();
        for(AdjShopBatchItemVo item : formVo.getItems()){
            AdjFormVo a = new AdjFormVo();
            a.setShortType(AdjShortType.STAGE_TO_SHOP.getValue());
            a.setFromId(formVo.getStageId());
            a.setToId(formVo.getShopId());
            a.setGoodsId(item.getGoodsId());
            a.setSpecId(item.getSpecId());
            a.setCount(item.getCount());
            a.setRealCount(item.getCount());   //到时候需要确认到货，取消这句
            a.setRemark(null);
            formList.add(a);
        }
        bizService.adjustBatch(formList);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "补货，页面BO-B11",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/stage/to/shop", method = RequestMethod.POST)
    public Response adjust(@RequestBody AdjShopInFormVo formVo) {
        AdjFormVo a = new AdjFormVo();
        a.setShortType(AdjShortType.STAGE_TO_SHOP.getValue());
        a.setFromId(formVo.getStageId());
        a.setToId(formVo.getShopId());
        a.setGoodsId(formVo.getGoodsId());
        a.setSpecId(formVo.getSpecId());
        a.setCount(formVo.getCount());
        a.setRealCount(formVo.getCount()); //到时候需要确认到货，取消这句
        a.setRemark(null);
        bizService.adjust(a);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "退货，页面BO-B11",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/shop/to/stage", method = RequestMethod.POST)
    public Response adjust(@RequestBody AdjShopOutFormVo formVo) {
        AdjFormVo a = new AdjFormVo();
        a.setShortType(AdjShortType.SHOP_TO_STAGE.getValue());
        a.setFromId(formVo.getShopId());
        a.setToId(formVo.getStageId());
        a.setGoodsId(formVo.getGoodsId());
        a.setSpecId(formVo.getSpecId());
        a.setCount(formVo.getCount());
        a.setRealCount(formVo.getCount()); //到时候需要确认到货，取消这句
        a.setRemark(null);
        bizService.adjust(a);
        return Response.simpleSuccess();
    }

}
