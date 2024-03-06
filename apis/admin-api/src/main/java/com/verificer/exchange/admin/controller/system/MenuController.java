package com.verificer.exchange.admin.controller.system;

import com.verificer.base.auth.service.BaseAuthService;
import com.verificer.beans.MenuVo;
import com.verificer.beans.TreeNodeVo;
import com.verificer.beans.UserIdentity;
import com.verificer.exchange.admin.security.annotation.NeedLogin;
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

import java.util.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    BaseAuthService baseAuthService;


//    @ApiOperation(
//            value = "我的菜单树",
//            response = MenuVo.class,
//            httpMethod = "POST"
//
//    )
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
//            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
//
//    })
//    @NeedLogin
//    @RequestMapping("/my/tree")
//    public Response myMenuTree(){
//        UserIdentity userIdentity = SecurityUtil.currentLogin();
//        List<TreeNodeVo<MenuVo>> menuTree = baseAuthService.getPermissionMenus(userIdentity.getId());
//
//        List<Map> menus = TreeUtils.parseTreeNodes(menuTree);
//        Map home = new HashMap();
//        home.put("name","Home");
//        home.put("path","/home");
//        menus.add(0,home);
//
//        return Response.dataSuccess(menus);
//    }

    @ApiOperation(
            value = "我的菜单树",
            response = MenuVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),

    })
    @NeedLogin
    @RequestMapping("/my/tree")
    public Response myMenuTree(){
        UserIdentity userIdentity = SecurityUtil.currentLogin();
        MenuVo kycAudit = new MenuVo();
        kycAudit.setCode("KYCAudit");
        kycAudit.setName("KYC Audit");
        kycAudit.setUrl("/kYCAudit");

        MenuVo proAudit = new MenuVo();
        proAudit.setCode("ProductAudit");
        proAudit.setName("Product Audit");
        proAudit.setUrl("/productAudit");

        MenuVo staff = new MenuVo();
        staff.setCode("Staff");
        staff.setName("Administrator");
        staff.setUrl("/staff");


        MenuVo auctionMana = new MenuVo();
        auctionMana.setCode("AuctionMana");
        auctionMana.setName("Auction management");
        auctionMana.setUrl("/auctionMana");

        List<MenuVo> voList = new LinkedList<>();
        voList.add(kycAudit);
        voList.add(proAudit);
        voList.add(staff);
        voList.add(auctionMana);

        List<TreeNodeVo<MenuVo>> menuTree = TreeUtils.buildTreeDatas(voList,"code","parentCode",null);
        List<Map> menus = TreeUtils.parseTreeNodes(menuTree);
//        Map home = new HashMap();
//        home.put("name","Home");
//        home.put("path","/home");
//        menus.add(0,home);
        for(Map m : menus){
            m.put("children",null);
        }

        return Response.dataSuccess(menus);
    }


    @ApiOperation(
            value = "菜单树",
            response = MenuVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),

    })
    @NeedLogin
    @RequestMapping("/tree")
//    @NeedPermission(needAuth = "MenuMana")
    public Response menuTree(){
        List<TreeNodeVo<MenuVo>> menuTree = baseAuthService.getAllMenus();
        return Response.dataSuccess(TreeUtils.parseTreeNodes(menuTree));
    }

    @ApiOperation(
            value = "导航页面搜索接口",
            response = MenuVo.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "key", value = "搜索关键字",paramType = "form",required = false),

    })
    @NeedLogin
    @RequestMapping("/navigate/search")
    public Response getNavigateList(String key){
        Long userId = SecurityUtil.currentLogin().getId();
        List<MenuVo> menuList = baseAuthService.listAllMenus();
        List<Map<String,String>> rstList = new LinkedList<>();

        Set<String> authSet = baseAuthService.getUserAuthSet(userId);
        for(MenuVo vo : menuList){
            if(SStringUtils.isEmpty(vo.getUrl()))
                continue;

            //如果搜索关键字不为空，并且对应的菜单名字中不包含搜索关键字
            if(!SStringUtils.isEmpty(key) && !vo.getName().contains(key))
                continue;

            //如果没有权限
            if(!authSet.contains(vo.getCode()))
                continue;

            Map<String,String> map = new HashMap<>();
            map.put("value",vo.getName());
            map.put("address",vo.getUrl());
            rstList.add(map);
        }
        return Response.dataSuccess(rstList);
    }


    @ApiOperation(
            value = "修改菜单",
            response = Response.class,
            httpMethod = "POST"

    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",paramType = "header",required = true),
            @ApiImplicitParam(name = "language", value = "语言",paramType = "header",required = true),
            @ApiImplicitParam(name = "code", value = "菜单唯一标识",paramType = "form",required = true),
            @ApiImplicitParam(name = "name", value = "菜单名称",paramType = "form",required = true),

    })
    @NeedLogin
    @RequestMapping("/update")
//    @NeedPermission(needAuth = "MenuMana")
    public Response updateMenu(@RequestParam String code, @RequestParam String name){
        baseAuthService.updateMenu(code,name);
        return Response.simpleSuccess();
    }
}
