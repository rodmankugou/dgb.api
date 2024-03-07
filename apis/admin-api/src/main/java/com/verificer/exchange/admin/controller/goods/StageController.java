package com.verificer.exchange.admin.controller.goods;

import com.verificer.biz.beans.vo.StageVo;
import com.verificer.biz.beans.vo.req.*;
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
@Api(tags = "仓库")
@RequestMapping("/stage")
@RestController
public class StageController extends BaseController{

    @Autowired
    BizService bizService;




    @ApiOperation(
            value = "列表（分页）",
            response = StageVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(@RequestBody StagePageVo qryVo) {
        List<StageVo> list = bizService.stagePage(qryVo);
        int count = bizService.stageCount(qryVo);
        return Response.listSuccess(count,list);
    }

    @ApiOperation(
            value = "列表（不分页）",
            response = StageVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录凭证",paramType = "header",required = true),
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response list() {
        List<StageVo> list = bizService.stageList();
        return Response.dataSuccess(list);
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
    public Response submit(@RequestBody StageFormVo formVo) {
        if(formVo.getId() == null){
            bizService.stageAdd(formVo);
        }else{
            bizService.stageUpd(formVo);
        }

        return Response.simpleSuccess();
    }


   

}
