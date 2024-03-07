package com.verificer.exchange.admin.controller.system;

import com.verificer.beans.LoginRespVo;
import com.verificer.beans.UserIdentity;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.entity.Staff;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.exchange.admin.security.filter.EscapeWrapper;
import com.verificer.exchange.admin.service.StaffService;
import com.verificer.exchange.admin.vo.LoginVo;
import com.verificer.exchange.admin.vo.UpdPwdVo;
import com.verificer.security.login.ILoginMonitor;
import com.verificer.utils.IPUtil;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 35336 on 2021/1/22.
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
    @Autowired
    StaffService staffService;

    @Autowired
    ILoginMonitor<UserIdentity> loginMonitor;

    /**
     * 登录
     *
     * @return
     */
    @ApiOperation(
            value = "登录",
            response = LoginRespVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(
            HttpServletRequest request, @RequestBody LoginVo vo) {


        String ip = IPUtil.getIp(request);

        Staff staff = null;
        if((staff = staffService.login(vo.getUsername(),vo.getPassword(),"","","","")) != null){
            //登录成功
        }
        String token = loginMonitor.login(getClient(),staff.getId().toString(),new UserIdentity(staff.getId()),new UserIdentity());

        return Response.dataSuccess(token);

    }

    /**
     * 登出O
     *
     * @return
     */
    @ApiOperation(
            value = "登出",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "veri-ex-client", value = "app/web",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @NeedLogin
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        UserIdentity userIdentity = SecurityUtil.currentLogin();

        loginMonitor.logout(SecurityUtil.getToken());
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "修改密码",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "originPassword", value = "密码",paramType = "form",required = true),
            @ApiImplicitParam(name = "newPassword", value = "密码",paramType = "form",required = true),
    })
    @RequestMapping("/password/update")
    @NeedLogin
    public Response resetPassword(HttpServletRequest request,
                                  @RequestBody UpdPwdVo vo) {
        Long staffId = SecurityUtil.currentLogin().getId();
        staffService.updatePassword(staffId, vo.getOriginPassword(),vo.getNewPassword());
        return Response.simpleSuccess();
    }

}
