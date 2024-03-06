package com.verificer.exchange.web.controller.goods;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.req.CatFormVo;
import com.verificer.biz.beans.vo.req.CatListQryVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 35336 on 2021/2/26.
 */
@RequestMapping("/category")
@RestController
public class CategoryController extends BaseController {

    @Autowired
    BizService bizService;


    @ApiOperation(
            value = "列表（分页）",
            response = CatVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object page(CatListQryVo qryVo) {
        List<CatVo> list = bizService.catList(qryVo);
        return Response.dataSuccess(list);
    }





}
