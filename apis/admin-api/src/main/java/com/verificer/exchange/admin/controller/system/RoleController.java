package com.verificer.exchange.admin.controller.system;


import com.verificer.base.auth.service.BaseAuthService;
import com.verificer.beans.AuthVo;
import com.verificer.beans.RoleVo;
import com.verificer.beans.TreeNodeVo;
import com.verificer.beans.UserIdentity;
import com.verificer.exchange.admin.controller.BaseController;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
import com.verificer.exchange.admin.security.annotation.NeedPermission;
import com.verificer.exchange.admin.service.StaffService;
import com.verificer.utils.SStringUtils;
import com.verificer.utils.TreeUtils;
import com.verificer.utils.web.SecurityUtil;
import com.verificer.web.common.response.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 35336 on 2021/1/24.
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    BaseAuthService baseAuthService;

    @Autowired
    StaffService staffService;

    @ApiOperation(
            value = "获取角色列表",
            response = RoleVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "name", value = "角色名",paramType = "form",required = true),
//            @ApiImplicitParam(name = "remark", value = "第几页",paramType = "form",required = true),
            @ApiImplicitParam(name = "enable", value = "启用状态 0-禁用 1-启用",paramType = "form",required = true),
            @ApiImplicitParam(name = "page", value = "第几页",paramType = "form",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页几条",paramType = "form",required = true),
    })
    @RequestMapping("/list")
    @NeedLogin
//    @NeedPermission(needAuth = "RoleMana")
    public Response list( String name,String remark,Boolean enable,Integer page, Integer pageSize){
        List<RoleVo> roleList = baseAuthService.listRole(name,remark,enable,getFrom(page,pageSize),getPageSize(pageSize));
        int count = baseAuthService.countRole(name,remark,enable);
        return Response.listSuccess(count,roleList);
    }


    @ApiOperation(
            value = "获取角色列表-不分页",
            response = RoleVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "name", value = "角色名",paramType = "form",required = true),
//            @ApiImplicitParam(name = "remark", value = "第几页",paramType = "form",required = true),
            @ApiImplicitParam(name = "enable", value = "启用状态 0-禁用 1-启用",paramType = "form",required = true),
    })
    @RequestMapping("/drop/list")
    @NeedLogin
    public Response list( String name,String remark,Boolean enable){
        List<RoleVo> roleList = baseAuthService.listRole(name,remark,enable,getFrom(1,1000),1000);
        int count = baseAuthService.countRole(name,remark,enable);
        return Response.listSuccess(count,roleList);
    }

    @ApiOperation(
            value = "添加角色",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "name", value = "角色名",paramType = "form",required = true),
            @ApiImplicitParam(name = "enable", value = "是否启用，0-禁用 1-启用",paramType = "form",required = true),

    })
    @RequestMapping("/add")
    @NeedLogin
//    @NeedPermission(needAuth = "RoleMana")
    public Response add(@RequestParam("name") String name,
                        @RequestParam("enable") Boolean enable){
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        String staffRealName = staffService.getRealName(userIdentity.getId());
        baseAuthService.addRole(userIdentity.getId(),staffRealName,name,"",enable);
        return Response.simpleSuccess();
    }

    @ApiOperation(
            value = "修改角色",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "name", value = "角色名",paramType = "form",required = true),
            @ApiImplicitParam(name = "enable", value = "是否启用，0-禁用 1-启用",paramType = "form",required = true),

    })
    @RequestMapping("/update")
    @NeedLogin
//    @NeedPermission(needAuth = "RoleMana")
    public Response update(@RequestParam("id") Long id,
                           @RequestParam("name") String name,
                           @RequestParam("enable") Boolean enable){
        baseAuthService.updateRole(id,name,"",enable);
        return Response.simpleSuccess();
    }


    @ApiOperation(
            value = "设置权限",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "角色id",paramType = "form",required = true),
            @ApiImplicitParam(name = "authCodes", value = "权限代码，可以为多个，代码之前以符号'@'隔开，如a@b@c",paramType = "form",required = true),

    })
    @RequestMapping("/auth/setup")
    @NeedLogin
//    @NeedPermission(needAuth = "RoleMana")
    public Response update(@RequestParam("id") Long id,
                           @RequestParam("authCodes") String authCodes){
        List<String> authCodeList = SStringUtils.split(authCodes,"@");
        baseAuthService.setupAuth(id,authCodeList);
        return Response.simpleSuccess();
    }

    /**
     * 获取某个角色的权限树，如果auth的权限被当前role拥有，则authVo的permission属性值为true，否则为false
     * @param id 角色id
     * @return
     */
    @ApiOperation(
            value = "获取某个角色的权限树，如果auth的权限被当前role拥有，则authVo的permission属性值为true，否则为false",
            response = AuthVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "角色id",paramType = "form",required = true),

    })
    @RequestMapping("/auth/tree")
    @NeedLogin
//    @NeedPermission(needAuth = "RoleMana")
    public Response getRoleAuthList(@RequestParam("id") Long id){
        List<TreeNodeVo<AuthVo>> tree = baseAuthService.getRoleAuthTree(id);
        return Response.dataSuccess(TreeUtils.parseTreeNodes(tree));
    }

//    @ApiOperation(
//            value = "获取当前用户所拥有的权限",
//            response = AuthVo.class,
//            httpMethod = "POST"
//
//    )
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
//            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
//
//    })
//    @RequestMapping("/auth/set")
//    @NeedLogin
////    @NeedPermission(needAuth = "RoleMana")
//    public Response getMyAuthSet(){
//        Long curUserId = SecurityUtil.currentLogin().getId();
//
//        Set<String> authSet = baseAuthService.getUserAuthSet(curUserId);
//        return Response.dataSuccess(authSet);
//    }

    @ApiOperation(
            value = "获取当前用户所拥有的权限",
            response = AuthVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),

    })
    @RequestMapping("/auth/set")
    @NeedLogin
//    @NeedPermission(needAuth = "RoleMana")
    public Response getMyAuthSet(){

        Set<String> authSet = new HashSet<>();
        return Response.dataSuccess(authSet);
    }

    /**
     * 设置禁用/可用状态
     * @param id 角色id
     * @return
     */
    @ApiOperation(
            value = "禁用/启用",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "id", value = "角色id",paramType = "form",required = true),
            @ApiImplicitParam(name = "enable", value = "启用状态，0-禁用 1-启用",paramType = "form",required = true),

    })
    @RequestMapping("/enable/update")
    @NeedLogin
//    @NeedPermission(needAuth = "RoleMana")
    public Response updateEnableStatus(@RequestParam("id") Long id,
                                       @RequestParam("enable") Boolean enable){
        baseAuthService.updateRoleEnableStatus(id,enable);
        return Response.simpleSuccess();
    }
}
