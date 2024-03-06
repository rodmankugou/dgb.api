package com.verificer.base.auth.service;

import com.verificer.beans.RoleVo;
import com.verificer.base.auth.entity.Role;

import java.util.List;

/**
 * Created by 35336 on 2021/1/22.
 */
public interface RoleService {
    /**
     * 获取某用户的所有角色（含被禁用的）
     * @param userId
     * @return
     */
    List<Role> getUserRoleList(Long userId);

    /**
     * 获取某用户的所有角色（不含被禁用的）
     * @param userId
     * @return
     */
    List<Role> getEnableUserRoleList(Long userId);

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
    void add(Long creatorId,String creatorName,String name,String remark,Boolean enable);

    /**
     * 修改角色
     * @param id
     * @param name
     * @param remark
     * @param enable
     */
    void update(Long id,String name,String remark,Boolean enable);

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
