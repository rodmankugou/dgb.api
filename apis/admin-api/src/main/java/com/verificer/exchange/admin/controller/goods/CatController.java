package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.vo.CatVo;
import com.verificer.biz.beans.vo.req.CatDelVo;
import com.verificer.biz.beans.vo.req.CatFormVo;
import com.verificer.biz.beans.vo.req.CatListQryVo;
import com.verificer.biz.beans.vo.req.CatPageQryVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
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
@Api(tags = "分类")
@RequestMapping("/cat")
@RestController
public class CatController extends BaseController{

    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "列表（不分页）",
            response = CatVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response list( @RequestBody CatListQryVo qryVo) {
        List<CatVo> list = bizService.catList(qryVo);
        return Response.dataSuccess(list);
    }


    @ApiOperation(
            value = "列表（分页）",
            response = CatVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page( @RequestBody CatPageQryVo qryVo) {
        List<CatVo> list = bizService.catPage(qryVo);
        int count = bizService.catCount(qryVo);
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
    public Response submit(@RequestBody  CatFormVo formVo) {
        if(formVo.getId() == null){
            bizService.catAdd(formVo);
        }else{
            bizService.catUpd(formVo);
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
    public Response del(@RequestBody  CatDelVo delVo) {
        bizService.catDel(delVo);

        return Response.simpleSuccess();
    }

}
