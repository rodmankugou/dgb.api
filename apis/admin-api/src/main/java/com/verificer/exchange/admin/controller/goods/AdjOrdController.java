package com.verificer.exchange.admin.controller.goods;

import com.verificer.beans.DropListVo;
import com.verificer.biz.beans.constants.BizConst;
import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.beans.vo.shop.ShopVo;
import com.verificer.biz.beans.vo.stage.StageVo;
import com.verificer.biz.beans.vo.adjust.AdjOrderVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdConfirmVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrdFormVo;
import com.verificer.biz.beans.vo.adjust.req.AdjOrderQryVo;
import com.verificer.biz.beans.vo.adjust.req.BoAdjDLToReqVo;
import com.verificer.biz.beans.vo.req.ShopListVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.SDropListUtils;
import com.verificer.utils.check.SCheckUtil;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@Api(tags = "调货单")
@RequestMapping("/adjust/order")
@RestController
public class AdjOrdController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "发货方下拉列表-不分页",
            response = DropListVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/dropList/from", method = RequestMethod.POST)
    public Response adjDropListFrom(@RequestBody BoAdjDLToReqVo reqVo) {

        List<StageVo> voList = bizService.stageList();
        List<DropListVo> list = SDropListUtils.toDropList(voList,"id","name");
        list.add(0,new DropListVo(BizConst.ADJ_OTHER_ID,BizConst.ADJ_OTHER_NAME));
        return Response.dataSuccess(list);
    }


    @ApiOperation(
            value = "收货方下拉列表-不分页",
            response = DropListVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/dropList/to", method = RequestMethod.POST)
    public Response adjDropListTo(@RequestBody BoAdjDLToReqVo reqVo) {
        SCheckUtil.notEmpty(reqVo.getToType(),"To Type");

        List list = null;
        if(MerType.STAGE.getValue() == reqVo.getToType() ){
            List<StageVo> voList = bizService.stageList();
            list = SDropListUtils.toDropList(voList,"id","name");
        }else if(MerType.SHOP.getValue() == reqVo.getToType() ){
            List<ShopVo> voList = bizService.shopList(new ShopListVo());
            list = SDropListUtils.toDropList(voList,"id","name");
        }else{
            list = new LinkedList<>();
        }
        return Response.dataSuccess(list);
    }



    @ApiOperation(
            value = "列表（分页）",
            response = AdjOrderVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody AdjOrderQryVo qryVo) {
        List<AdjOrderVo> list = bizService.adjOrdPage(qryVo);
        int count = bizService.adjOrdCount(qryVo);
        return Response.listSuccess(count, SBigDecimalUtils.lprcFormat2(list));
    }



    @ApiOperation(
            value = "创建配货单",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response create(@RequestBody AdjOrdFormVo formVo) {

        bizService.adjOrdCreate(formVo);
        return Response.simpleSuccess();
    }



    @ApiOperation(
            value = "确认配货单",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public Response confirm(@RequestBody AdjOrdConfirmVo formVo) {

        bizService.adjOrdConfirm(formVo);
        return Response.simpleSuccess();
    }

}
