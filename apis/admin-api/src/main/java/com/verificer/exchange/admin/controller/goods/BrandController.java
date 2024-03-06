package com.verificer.exchange.admin.controller.goods;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.AreaQryVo;
import com.verificer.beans.AreaVo;
import com.verificer.biz.beans.vo.BrandVo;
import com.verificer.biz.beans.vo.req.BrandDelVo;
import com.verificer.biz.beans.vo.req.BrandFormVo;
import com.verificer.biz.beans.vo.req.BrandPageQryVo;
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
@RequestMapping("/brand")
@RestController
public class BrandController extends BaseController{

    @Autowired
    BizService bizService;




    @ApiOperation(
            value = "列表（分页）",
            response = BrandVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody BrandPageQryVo qryVo) {
        List<BrandVo> list = bizService.brandPage(qryVo);
        int count = bizService.brandCount(qryVo);
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
    public Response submit(@RequestBody BrandFormVo formVo) {
        if(formVo.getId() == null){
            bizService.brandAdd(formVo);
        }else{
            bizService.brandUpd(formVo);
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
    public Response del(@RequestBody BrandDelVo delVo) {
        bizService.brandDel(delVo);

        return Response.simpleSuccess();
    }

}
