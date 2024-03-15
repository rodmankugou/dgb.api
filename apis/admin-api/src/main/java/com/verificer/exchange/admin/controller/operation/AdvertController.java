package com.verificer.exchange.admin.controller.operation;

import com.verificer.beans.AdvertPageVo;
import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.AdvertFormVo;
import com.verificer.beans.IdVo;
import com.verificer.beans.suportVo.AdvertVo;
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
@RequestMapping("/advert")
public class AdvertController extends BaseController {
    @Autowired
    BaseSupService baseSupService;

    @ApiOperation(
            value = "列表（分页）",
            httpMethod = "POST",
            response = AdvertVo.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping("/page")
    @NeedLogin
    public Response list(@RequestBody AdvertPageVo queryVo){

        List<AdvertVo> voLIst = baseSupService.advertPage(getLanguage(),queryVo);
        int count = baseSupService.advertCount(queryVo);
        return Response.listSuccess(count,voLIst);
    }



    @ApiOperation(
            value = "添加/删除",
            httpMethod = "POST",
            response = Response.class
    )

    @RequestMapping("/submit")
    @NeedLogin
    public Response submit(@RequestBody AdvertFormVo fVo){
        if(fVo.getId() == null){
            baseSupService.advertAdd(fVo);
        }else {
            baseSupService.advertUpd(fVo);
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
        baseSupService.advertDel(idVo);

        return Response.simpleSuccess();
    }

}
