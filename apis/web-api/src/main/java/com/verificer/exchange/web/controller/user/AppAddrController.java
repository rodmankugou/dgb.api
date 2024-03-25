package com.verificer.exchange.web.controller.user;

import com.verificer.beans.EmptyVo;
import com.verificer.beans.AppReqVo;
import com.verificer.biz.beans.vo.AppIdVo;
import com.verificer.biz.beans.vo.addr.AddrVo;
import com.verificer.biz.beans.vo.addr.req.AddrFormVo;
import com.verificer.biz.beans.vo.addr.req.AddrQryVo;
import com.verificer.biz.beans.vo.integral.AppIntegralLogVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.BaseController;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.utils.decimal.SBigDecimalUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "收货地址")
@RestController
@RequestMapping("/addr")
public class AppAddrController extends BaseController {


    @Autowired
    BizService bizService;




    @ApiOperation(
            value = "地址列表-分页",
            response = AddrVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Response page(HttpServletRequest hReq,@RequestBody AddrQryVo reqVo){
        fillUserId(hReq,reqVo);
        List<AddrVo> list = bizService.addrPage(reqVo);
        return Response.dataSuccess(SBigDecimalUtils.prcFormat2(list));
    }


    @ApiOperation(
            value = "获取默认地址",
            response = AddrVo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/default/addr", method = RequestMethod.POST)
    public Response list(HttpServletRequest hReq,@RequestBody AppReqVo reqVo){
        fillUserId(hReq,reqVo);

        AddrVo vo  = bizService.addrDefault(reqVo);
        return Response.dataSuccess(vo);
    }



    @ApiOperation(
            value = "新增地址",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(HttpServletRequest hReq,@RequestBody AddrFormVo reqVo){

        fillUserId(hReq,reqVo);
        bizService.addrAdd(reqVo);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "修改地址",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Response update(HttpServletRequest hReq,@RequestBody AddrFormVo reqVo){
        fillUserId(hReq,reqVo);
        bizService.addrUpd(reqVo);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "删除地址",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
    })
    @ResponseBody
    @NeedLogin
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Response defaultAddr(HttpServletRequest hReq,@RequestBody AppIdVo reqVo){
        fillUserId(hReq,reqVo);
        bizService.addrDel(reqVo);
        return Response.simpleSuccess();
    }




}
