package com.verificer.base.auth.service.impl.auth;

import com.verificer.base.auth.entity.Auth;
import com.verificer.base.auth.entity.Role;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by 35336 on 2021/1/22.
 */
public class AuthCache {
    private static final Object LOCK = new Object();
    private static final String ROLE_AUTH_CACHE_KEY = "auth:role_auth_cache";
    private static final String USER_ROLE_KEY_PREFIX = "auth:user_roles:";
    private static final long USER_ROLE_CACHE_SECONDS = new Long(1800);
    private AuthLoader  authLoader;
    private RedisUtil redisUtil;

    public AuthCache(AuthLoader authLoader,RedisUtil redisUtil) {
        this.authLoader = authLoader;
        this.redisUtil = redisUtil;
    }

    public void init(){
        reload();
    }

    private void reload(){
        Map<Long,List<Auth>> roleAuthListMap = authLoader.loadRoleAuthMap();
        Map<String,List<Auth>> map = new HashMap<>();
        for(Long key : roleAuthListMap.keySet()){
            map.put(key.toString(),roleAuthListMap.get(key));
        }
        synchronized (LOCK){
            redisUtil.putAllMapValue(ROLE_AUTH_CACHE_KEY,map);
        }
    }

    /**
     * 更新某个角色的权限缓存
     * @param roleId
     * @param authList
     */
    public void refresh(Long roleId,List<Auth> authList){
        reload();
    }

    public Set<Auth> getUserAuthSet(Long userId){
        if(userId == null){
            throw new IllegalArgumentException("parameter userId can not be null");
        }
        String key = new StringBuffer(USER_ROLE_KEY_PREFIX).append(userId).toString();
        List<Role> roleList = (List<Role>) redisUtil.get(key);
        if(roleList == null){
            //缓存中不存在，从数据库中加载
            roleList = authLoader.loadUserRoleList(userId);
            if(roleList == null){
                roleList = new LinkedList<>();
            }
            redisUtil.set(key,roleList,USER_ROLE_CACHE_SECONDS);
        }

        Set<Auth> authSet = new HashSet<>();
        for(Role role : roleList){
            List<Auth> authList = (List<Auth>) redisUtil.getMapValue(ROLE_AUTH_CACHE_KEY,role.getId().toString());
            if(authList != null){
                authSet.addAll(authList);
            }
        }

        return authSet;
    }
}
