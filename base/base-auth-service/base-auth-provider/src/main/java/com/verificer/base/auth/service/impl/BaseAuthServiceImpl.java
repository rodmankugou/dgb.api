package com.verificer.base.auth.service.impl;

import com.verificer.beans.RoleVo;
import com.verificer.base.auth.service.AuthService;
import com.verificer.base.auth.service.BaseAuthService;
import com.verificer.base.auth.service.MenuService;
import com.verificer.base.auth.service.RoleService;
import com.verificer.beans.AuthVo;
import com.verificer.beans.MenuVo;
import com.verificer.beans.TreeNodeVo;
import com.verificer.dubbo.BaseDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by 35336 on 2021/1/8.
 */
@Service("baseAuthService")
public class BaseAuthServiceImpl extends BaseDubboService implements BaseAuthService {
    @Autowired
    MenuService menuService;

    @Autowired
    private AuthService authService;

    @Autowired
    RoleService roleService;

    @Override
    public List<TreeNodeVo<MenuVo>> getPermissionMenus(Long userId) {
        return menuService.getPermissionMenus(userId);
    }

    @Override
    public List<TreeNodeVo<MenuVo>> getAllMenus() {
        return menuService.getAllMenus();
    }

    @Override
    public List<MenuVo> listAllMenus() {
        return menuService.listAllMenus();
    }

    @Override
    public void updateMenu(String code, String name) {
        menuService.updateMenu(code,name);
    }

    @Override
    public Set<String> getUserAuthSet(Long userId) {
        return authService.getUserAuthSet(userId);
    }

    @Override
    public List<RoleVo> listRole(String name, String remark, Boolean enable, int from, int pageSize) {
        return roleService.listRole(name,remark,enable,from,pageSize);
    }

    @Override
    public int countRole(String name, String remark, Boolean enable) {
        return roleService.countRole(name,remark,enable);
    }

    @Override
    public void addRole(Long creatorId,String creatorName,String name, String remark, Boolean enable) {
        roleService.add(creatorId,creatorName,name,remark,enable);
    }

    @Override
    public void updateRole(Long id, String name, String remark, Boolean enable) {
        roleService.update(id,name,remark,enable);
    }

    @Override
    public void setupAuth(Long roleId, List<String> authCodeList) {
        authService.setupAuth(roleId,authCodeList);
    }

    @Override
    public List<TreeNodeVo<AuthVo>> getRoleAuthTree(Long roleId) {
        return authService.getRoleAuthTree(roleId);
    }

    @Override
    public void updateRoleEnableStatus(Long id,Boolean enable) {
        roleService.updateRoleEnableStatus(id,enable);
    }

    @Override
    public RoleVo getUserRole(Long userId) {
        return roleService.getUserRole(userId);
    }

    @Override
    public void setUserRole(Long userId, Long roleId) {
        roleService.setUserRole(userId,roleId);
    }

    @Override
    public void deleteRole(Long id) {
        roleService.deleteRole(id);
    }

    @Override
    public RoleVo getRoleVo(Long roleId) {
        return roleService.getRoleVo(roleId);
    }

    @Override
    public void deleteUserRole(Long userId) {
        roleService.deleteRole(userId);
    }
}
