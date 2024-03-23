package com.verificer.exchange.web.controller.user;

import com.verificer.beans.EmptyVo;
import com.verificer.beans.IdVo;
import com.verificer.biz.beans.vo.user.AppUserInfo;
import com.verificer.biz.beans.vo.user.UserVo;
import com.verificer.biz.beans.vo.user.req.BindMobileVo;
import com.verificer.biz.beans.vo.user.req.SetAvatarVo;
import com.verificer.biz.beans.vo.user.req.SetNicknameVo;
import com.verificer.biz.biz.service.BizService;
import com.verificer.exchange.web.controller.FileUploadController;
import com.verificer.exchange.web.security.annotation.NeedLogin;
import com.verificer.utils.reflect.SBeanUtils;
import com.verificer.utils.web.UserIdentityUtils;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户")

@RestController
@RequestMapping("/user")
public class AppUserController extends FileUploadController {
    @Autowired
    BizService bizService;

    @ApiOperation(
            value = "获取当前登录用户的信息",
            response = AppUserInfo.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping(value = "/cur/userInfo", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public Response getUserInfo(
            HttpServletRequest hReq,
            @RequestBody EmptyVo reqVo) {
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();

        UserVo userVo = bizService.userDetail(new IdVo(userId));
        AppUserInfo ui = new AppUserInfo();
        SBeanUtils.copyProperties2(userVo,ui);

        return Response.dataSuccess(ui);
    }


    @ApiOperation(
            value = "绑定手机号码",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public Response setMobile(
            HttpServletRequest hReq,
            @RequestBody BindMobileVo reqVo) {
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();
        reqVo.setUserId(userId);

        bizService.userBindMobile(reqVo);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "修改头像",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping(value = "/setAvatar", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public Response setAvatar(
            HttpServletRequest hReq,
            @RequestBody SetAvatarVo reqVo) {
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();
        reqVo.setUserId(userId);

        bizService.userSetAvatar(reqVo);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "修改昵称",
            response = Response.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
    })
    @RequestMapping(value = "/setNickname", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public Response setNickname(
            HttpServletRequest hReq,
            @RequestBody SetNicknameVo reqVo) {
        Long userId = UserIdentityUtils.getUserIdentity(hReq).getId();
        reqVo.setUserId(userId);

        bizService.userSetNickname(reqVo);
        return Response.simpleSuccess();
    }



}
