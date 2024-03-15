package com.verificer.exchange.admin.controller.operation;

import com.verificer.base.sup.itf.BaseSupService;
import com.verificer.beans.AdminBannerVo;
import com.verificer.beans.BannerQueryVo;
import com.verificer.beans.BannerFormVo;
import com.verificer.beans.IdVo;
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
 * Created by 35336 on 2021/2/25.
 */
@Api(tags = "Banner")
@RestController
@RequestMapping("/banner")
public class BannerController extends BaseController {
    @Autowired
    BaseSupService baseSupService;

    @ApiOperation(
            value = "列表（分页）",
            httpMethod = "POST",
            response = AdminBannerVo.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping("/page")
    @NeedLogin
    public Response list(@RequestBody BannerQueryVo queryVo){

        List<AdminBannerVo> voLIst = baseSupService.bannerPage(getLanguage(),queryVo);
        int count = baseSupService.bannerCount(queryVo);
        return Response.listSuccess(count,voLIst);
    }



    @ApiOperation(
            value = "添加/修改",
            httpMethod = "POST",
            response = Response.class
    )

    @RequestMapping("/submit")
    @NeedLogin
    public Response submit(@RequestBody BannerFormVo fVo){
        if(fVo.getId() == null){
            baseSupService.bannerAdd(fVo);
        }else {
            baseSupService.bannerUpd(fVo);
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
        baseSupService.bannerDel(idVo);

        return Response.simpleSuccess();
    }

}
