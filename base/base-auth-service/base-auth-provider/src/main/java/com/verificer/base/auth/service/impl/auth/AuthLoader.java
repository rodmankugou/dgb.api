package com.verificer.base.auth.service.impl.auth;

import com.verificer.base.auth.entity.Auth;
import com.verificer.base.auth.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by 35336 on 2021/1/22.
 */
public interface AuthLoader {
    /**
     * 加载每个角色所拥有的权限
     * @return
     */
    Map<Long,List<Auth>> loadRoleAuthMap();

    /**
     * 加载某个角色所拥有的权限
     * @return
     */
    List<Auth> loadRoleAuthList(Long roleId);

    /**
     * 获取用户的角色列表
     * @param userId
     * @return
     */
    List<Role> loadUserRoleList(Long userId);
}
