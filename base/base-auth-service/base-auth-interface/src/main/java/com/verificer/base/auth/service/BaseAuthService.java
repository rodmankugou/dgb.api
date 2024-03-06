package com.verificer.base.auth.service;

import com.verificer.beans.RoleVo;
import com.verificer.beans.AuthVo;
import com.verificer.beans.MenuVo;
import com.verificer.beans.TreeNodeVo;

import java.util.List;
import java.util.Set;

/**
 * Created by 35336 on 2021/1/8.
 */
public interface BaseAuthService {
    /**
     * 获取当前用户所拥有的权限
     * @param userId
     * @return
     */
    List<TreeNodeVo<MenuVo>> getPermissionMenus(Long userId);

    /**
     * 获取菜单树
     * @return
     */
    List<TreeNodeVo<MenuVo>> getAllMenus();

    /**
     * 获取所有菜单
     * @return
     */
    List<MenuVo> listAllMenus();

    /**
     * 更新菜单
     * @param code code
     * @param name 菜单名称
     */
    void updateMenu(String code, String name);

    Set<String> getUserAuthSet(Long userId);

    /**
     * 分页查询角色列表
     * @param name 名称
     * @param remark 描述
     * @param enable 是否启用
     * @param from 从第几条起
     * @param pageSize 一页多少条
     */
    List<RoleVo> listRole(String name, String remark, Boolean enable, int from, int pageSize);


    /**
     * 统计符合条件的角色数量
     * @param name 名称
     * @param remark 描述
     * @param enable 是否启用
     */
    int countRole(String name, String remark, Boolean enable);

    /**
     * 添加角色
     * @param creatorId
     * @param creatorName
     * @param name
     * @param remark
     * @param enable
     */
    void addRole(Long creatorId,String creatorName,String name, String remark, Boolean enable);

    /**
     * 修改角色
     * @param id
     * @param name
     * @param remark
     * @param enable
     */
    void updateRole(Long id,String name, String remark, Boolean enable);

   /**
     * 设置权限
     * @param roleId 角色id
     * @param authCodeList 权限code列表
     */
    void setupAuth(Long roleId, List<String> authCodeList);


    /**
     * 获取某个角色的权限树，如果auth的权限被当前role拥有，则authVo的permission属性值为true，否则为false
     * @param roleId
     */
    List<TreeNodeVo<AuthVo>> getRoleAuthTree(Long roleId);

    /**
     * 修改角色的可用状态
     * @param id
     * @param enable
     */
    void updateRoleEnableStatus(Long id,Boolean enable);

    /**
     * 获取某用户的角色
     * @param userId 用户id
     * @return
     */
    RoleVo getUserRole(Long userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     */
    void setUserRole(Long userId, Long roleId);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(Long id);

    /**
     * 通过id获取role
     * @param roleId
     * @return
     */
    RoleVo getRoleVo(Long roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteUserRole(Long userId);

}
