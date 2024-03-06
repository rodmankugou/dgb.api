package com.verificer.base.auth.service;

import com.verificer.base.auth.entity.Auth;
import com.verificer.beans.AuthVo;
import com.verificer.beans.TreeNodeVo;

import java.util.List;
import java.util.Set;

/**
 * Created by 35336 on 2021/1/22.
 */
public interface AuthService {
    /**
     * 获取用户所具备的权限
     * @return
     */
    public Set<String> getUserAuthSet(Long userId);

    /**
     * 更新某个角色的权限缓存
     */
    public void refreshRoleAuthCache(Long roleId);

    /**
     * 设置某个角色的权限
     * @param roleId 角色id
     * @param authCodeList 权限code列表
     */
    void setupAuth(Long roleId, List<String> authCodeList);

    /**
     * 获取某个角色的权限树，如果auth的权限被当前role拥有，则authVo的permission属性值为true，否则为false
     * @param roleId
     */
    List<TreeNodeVo<AuthVo>> getRoleAuthTree(Long roleId);

}
