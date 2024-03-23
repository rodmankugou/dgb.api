package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.MerStockVo;
import com.verificer.biz.beans.vo.req.*;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            value = "获取某个仓库/店铺所有的商品库存",
            response = MerStockVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/mer/stock/list", method = RequestMethod.POST)
    public Response merStocklist( @RequestBody StockMerQryVo qryVo) {
        List<MerStockVo> list = bizService.merStockList(qryVo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(list));
    }



}
