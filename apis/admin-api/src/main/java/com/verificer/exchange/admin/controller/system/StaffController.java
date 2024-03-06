package com.verificer.exchange.admin.controller.system;

import com.verificer.beans.UserIdentity;
import com.verificer.exchange.admin.AdminApiConstants;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.exchange.admin.security.filter.EscapeWrapper;
import com.verificer.exchange.admin.service.StaffService;
import com.verificer.exchange.admin.vo.StaffVo;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 35336 on 2021/1/22.
 */
@RestController
@RequestMapping("/staff")
public class StaffController extends BaseController {
    @Autowired
    StaffService staffService;

    /**
     * 登出
     *
     * @return
     */
    @ApiOperation(
            value = "获取当前登录用户信息",
            response = StaffVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/user/info", method = RequestMethod.POST)
    @NeedLogin
    public Response getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        StaffVo staffVo = staffService.detail(userIdentity.getId());
        return Response.dataSuccess(staffVo);
    }


    @ApiOperation(
            value = "获取员工详情",
            response = StaffVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "系统用户id",paramType = "query",required = true)
    })
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @NeedLogin
//    @NeedPermission(needAuth = "StaffMana2")
    public Response detail(HttpServletRequest request, @RequestParam Long id) {
        StaffVo staffVo = staffService.detail(id);
        return Response.dataSuccess(staffVo);
    }


    @ApiOperation(
            value = "获取员工列表",
            response = StaffVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "page", value = "第几页",paramType = "form",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页几条",paramType = "form",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @NeedLogin
//    @NeedPermission(needAuth = "StaffMana2")
    public Response list(HttpServletRequest request,
                         Integer page,
                         Integer pageSize) {
        Integer from = getFrom(page,pageSize);
        pageSize = getPageSize(pageSize);
        List<StaffVo> staffVoList = staffService.page(from,pageSize);
        int count = staffService.count();
        return Response.listSuccess(count,staffVoList);
    }



    /**
     * 添加员工
     *
     * @return
     */
    @ApiOperation(
            value = "添加员工",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "username", value = "用户名",paramType = "form",required = true),
            @ApiImplicitParam(name = "realName", value = "真实姓名",paramType = "form",required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号码",paramType = "form",required = false),
            @ApiImplicitParam(name = "password", value = "密码",paramType = "form",required = true),
            @ApiImplicitParam(name = "roleId", value = "角色id",paramType = "form",required = false),
            @ApiImplicitParam(name = "enable", value = "是否可用，0-禁用 1-启用",paramType = "form",required = true),

    })
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @NeedLogin
//    @NeedPermission(needAuth = "StaffMana2")
    public Response add(HttpServletRequest request,
                           String username,
                           String realName,
                           String mobile,
                           String password,
                           Long roleId,
                           Integer enable) {
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        EscapeWrapper wrapper = new EscapeWrapper(request);
        password = wrapper.getParameter("password");
        staffService.add(userIdentity.getId(),username,realName,mobile,password,roleId,enable);
        return Response.simpleSuccess();
    }

    /**
     * 修改员工
     *
     * @return
     */
    @ApiOperation(
            value = "修改员工",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "用功id",paramType = "form",required = true),
            @ApiImplicitParam(name = "realName", value = "真是姓名",paramType = "form",required = true),
//            @ApiImplicitParam(name = "roleId", value = "角色id",paramType = "form",required = true),
            @ApiImplicitParam(name = "enable", value = "是否可用，0-禁用 1-启用",paramType = "form",required = true),
    })
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @NeedLogin
//    @NeedPermission(needAuth = "StaffMana2")
    public Response update(HttpServletRequest request,
                           Long id,
                           String realName,
                           Long roleId,
                           Integer enable) {
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        staffService.update(id,realName,roleId,enable);
        return Response.simpleSuccess();
    }


    /**
     * 修改可用状态
     * @param request
     * @param id
     * @param enable
     * @return
     */
    @ApiOperation(
            value = "启用/禁用",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "用户id",paramType = "form",required = true),
            @ApiImplicitParam(name = "enable", value = "是否可用，0-禁用 1-启用",paramType = "form",required = true),
    })
    @RequestMapping("/enable/status/update")
    @NeedLogin
//    @NeedPermission(needAuth = "StaffMana2")
    public Response updateEnableStatus(HttpServletRequest request,
                           Long id,
                           Integer enable) {
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        staffService.updateEnableStatus(id,enable);
        return Response.simpleSuccess();
    }

    /**
     * 解锁
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(
            value = "解锁",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "用户id",paramType = "form",required = true),
    })
    @RequestMapping("/unlock")
    @NeedLogin
//    @NeedPermission(needAuth = "StaffMana2")
    public Response updateEnableStatus(HttpServletRequest request,
                                       Long id) {
        staffService.updateLockStatus(id,0);
        return Response.simpleSuccess();
    }


    /**
     * 重置密码
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(
            value = "重置密码",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "用户id",paramType = "form",required = true),
    })
    @RequestMapping("/password/reset")
    @NeedLogin
    public Response resetPassword(HttpServletRequest request,
                                       Long id) {
        staffService.resetPassword(id, AdminApiConstants.DEFAULT_RESET_PASSWORD);
        return Response.simpleSuccess();
    }
}
