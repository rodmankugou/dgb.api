package com.verificer.exchange.admin.controller.operation;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.HelpFormVo;
import com.verificer.beans.HelpPageVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.suportVo.HelpVo;
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
 * Created by 35336 on 2021/2/25.
 */
@RestController
@RequestMapping("/help")
public class HelpController extends BaseController {
    @Autowired
    BaseSupService baseSupService;

    @ApiOperation(
            value = "列表（分页）",
            httpMethod = "POST",
            response = HelpVo.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping("/page")
    @NeedLogin
    public Response list(@RequestBody HelpPageVo queryVo){

        List<HelpVo> voLIst = baseSupService.helpPage(getLanguage(),queryVo);
        int count = baseSupService.helpCount(queryVo);
        return Response.listSuccess(count,voLIst);
    }



    @ApiOperation(
            value = "添加/删除",
            httpMethod = "POST",
            response = Response.class
    )

    @RequestMapping("/submit")
    @NeedLogin
    public Response submit(@RequestBody HelpFormVo fVo){
        if(fVo.getId() == null){
            baseSupService.helpAdd(fVo);
        }else {
            baseSupService.helpUpd(fVo);
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
    public Response del(@RequestBody IdVo idVo) {
        baseSupService.helpDel(idVo);

        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "详情",
            response = HelpVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Response detail(@RequestBody IdVo idVo) {
        HelpVo vo =  baseSupService.helpDetail(idVo);

        return Response.dataSuccess(vo);
    }

}
