package com.verificer.exchange.web.controller.goods;

import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.req.BrandListQryVo;
import com.verificer.biz.beans.vo.req.BrandPageQryVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.security.annotation.NeedLogin;
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
@RequestMapping("/brand")
@RestController
public class BrandController extends BaseController {

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "品牌列表（分页）",
            response = BrandVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object page(@RequestBody BrandListQryVo qryVo) {
        List<BrandVo> list = bizService.brandList(qryVo);
        return Response.dataSuccess(list);
    }





}
