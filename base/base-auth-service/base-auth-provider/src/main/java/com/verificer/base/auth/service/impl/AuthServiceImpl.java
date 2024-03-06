package com.verificer.base.auth.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.auth.entity.Auth;
import com.verificer.base.auth.entity.RRoleAuth;
import com.verificer.base.auth.entity.Role;
import com.verificer.base.auth.mapper.AuthMapper;
import com.verificer.base.auth.mapper.RRoleAuthMapper;
import com.verificer.base.auth.mapper.RoleMapper;
import com.verificer.base.auth.service.AuthService;
import com.verificer.base.auth.service.RoleService;
import com.verificer.base.auth.service.impl.auth.AuthCache;
import com.verificer.base.auth.service.impl.auth.AuthLoader;
import com.verificer.beans.AuthVo;
import com.verificer.beans.TreeNodeVo;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.RedisUtil;
import com.verificer.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 权限服务
 * Created by 35336 on 2021/1/22.
 */
@Service("authService")
public class AuthServiceImpl implements AuthService,AuthLoader {
    @Autowired
    private RedisUtil redisUtil;

    private AuthCache authCache;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    RRoleAuthMapper rRoleAuthMapper;

    @PostConstruct
    public void init(){
        authCache = new AuthCache(this,redisUtil);
        authCache.init();
    }

    @Override
    public Set<String> getUserAuthSet(Long userId) {
        Set<String> rst = new HashSet<>();
        Set<Auth> authSet = authCache.getUserAuthSet(userId);
        for(Auth auth : authSet){
            rst.add(auth.getCode());
        }
        return rst;
    }

    @Override
    public void refreshRoleAuthCache(Long roleId) {
        List<Auth> authList = loadRoleAuthList(roleId);
        authCache.refresh(roleId,authList);
    }

    /**
     * 设置某个角色的权限
     * @param roleId 角色id
     * @param authCodeList 权限code列表
     */
    @Override
    public void setupAuth(Long roleId, List<String> authCodeList) {
        if(roleId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        rRoleAuthMapper.deleteByRoleId(roleId);
        if(authCodeList != null){
            for(String authCode : authCodeList){
                RRoleAuth rRoleAuth = new RRoleAuth();
                rRoleAuth.setAuthCode(authCode);
                rRoleAuth.setRoleId(roleId);
                rRoleAuthMapper.insertSelective(rRoleAuth);
            }
        }


    }

    /**
     * 获取某个角色的权限树，如果auth的权限被当前role拥有，则authVo的permission属性值为true，否则为false
     * @param roleId
     */
    @Override
    public List<TreeNodeVo<AuthVo>> getRoleAuthTree(Long roleId) {
        if(roleId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        List<Auth> authList = authMapper.selectAll();
        List<RRoleAuth> rRoleAuthList = rRoleAuthMapper.selectByRoleId(roleId);
        List<AuthVo> voList = new LinkedList<>();
        for(Auth auth : authList){
            AuthVo authVo = new AuthVo();
            authVo.setCode(auth.getCode());
            authVo.setName(auth.getName());
            authVo.setRemark(auth.getRemark());
            authVo.setParentCode(auth.getParentCode());
            authVo.setPermission(false);
            for(RRoleAuth rra : rRoleAuthList){
                if(auth.getCode().equals(rra.getAuthCode())){
                    authVo.setPermission(true);
                    break;
                }
            }
            voList.add(authVo);
        }

        return TreeUtils.buildTreeDatas(voList,"code","parentCode",null);
    }

    @Override
    public Map<Long, List<Auth>> loadRoleAuthMap() {
        Map roleAuthMap = new HashMap();
        List<Role> roleList = roleMapper.selectAll();
        for(Role role : roleList ){
            if(role.getEnable()){
                List<Auth> authList = loadRoleAuthList(role.getId());
                authList = authList == null ? new LinkedList() : authList;
                roleAuthMap.put(role.getId(),authList);
            }
        }
        return roleAuthMap;
    }

    @Override
    public List<Auth> loadRoleAuthList(Long roleId) {
        if(roleId == null){
            throw new IllegalArgumentException("parameter roleId can not be null");
        }
        return authMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Role> loadUserRoleList(Long userId) {
        return roleService.getEnableUserRoleList(userId);
    }
}
