package com.verificer.base.auth.service.impl;

import com.verificer.ErrCode;
import com.verificer.base.auth.mapper.RRoleAuthMapper;
import com.verificer.beans.RoleVo;
import com.verificer.base.auth.entity.RUserRole;
import com.verificer.base.auth.entity.Role;
import com.verificer.base.auth.mapper.RUserRoleMapper;
import com.verificer.base.auth.mapper.RoleMapper;
import com.verificer.base.auth.service.RoleService;
import com.verificer.common.exception.BaseException;
import com.verificer.utils.SStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 35336 on 2021/1/22.
 */
@Service("roleService")
class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RUserRoleMapper rUserRoleMapper;

    @Autowired
    RRoleAuthMapper rRoleAuthMapper;

    /**
     * 获取某用户的所有角色（含被禁用的）
     * @param userId
     * @return
     */
    @Override
    public List<Role> getUserRoleList(Long userId) {
        if(userId == null){
            throw new IllegalArgumentException("parameter userId can not be null");
        }

        return roleMapper.selectByUserId(userId);
    }

    /**
     * 获取某用户的所有角色（不含被禁用的）
     * @param userId
     * @return
     */
    @Override
    public List<Role> getEnableUserRoleList(Long userId) {
        if(userId == null){
            throw new IllegalArgumentException("parameter userId can not be null");
        }
        List<Role> roles = getUserRoleList(userId);
        List<Role> rst = new LinkedList<>();
        for(Role r : roles){
            if(r.getEnable()){
                rst.add(r);
            }
        }
        return rst;
    }

    private RoleVo toVo(Role role){
        if(role == null){
            return null;
        }
        RoleVo vo = new RoleVo();
        vo.setId(role.getId());
        vo.setName(role.getName());
        vo.setEnable(role.getEnable());
        vo.setRemark(role.getRemark());
        return vo;
    }

    private List<RoleVo> toVoList(List<Role> roles){
        List<RoleVo> result = new LinkedList<>();
        for(Role r : roles){
            result.add(toVo(r));
        }
        return result;
    }

    @Override
    public List<RoleVo> listRole(String name, String remark, Boolean enable, int from, int pageSize) {
        List<Role> roles = roleMapper.selectPage(name,remark,enable,from,pageSize);
        return toVoList(roles);
    }

    @Override
    public int countRole(String name, String remark, Boolean enable) {
        return roleMapper.countPage(name,remark,enable);
    }

    @Override
    public void add(Long creatorId,String creatorName,String name,String remark,Boolean enable) {
        if(SStringUtils.isEmpty(name)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(enable == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Role role = new Role();
        role.setCreatorId(creatorId);
        role.setCreatorName(creatorName);
        role.setName(name);
        role.setRemark(remark);
        role.setEnable(enable);
        role.setCreateTime(System.currentTimeMillis());
        roleMapper.insertSelective(role);
    }

    @Override
    public void update(Long id,String name, String remark, Boolean enable) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(name)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(enable == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Role role = roleMapper.selectByPrimaryKey(id);
        if(role == null){
            throw new BaseException(ErrCode.RECORD_NOT_EXIST);
        }
        role.setName(name);
        role.setRemark(remark);
        role.setEnable(enable);
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 修改角色的可用状态
     * @param id
     * @param enable
     */
    @Override
    public void updateRoleEnableStatus(Long id, Boolean enable) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(enable == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Role role = new Role();
        role.setId(id);
        role.setEnable(enable);
        roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 获取某用户的角色
     * @param userId 用户id
     * @return
     */
    @Override
    public RoleVo getUserRole(Long userId) {
        if(userId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        List<RUserRole> rUserRoles = rUserRoleMapper.selectByUserId(userId);
        if(rUserRoles.size() > 0){
            Role role = roleMapper.selectByPrimaryKey(rUserRoles.get(0).getRoleId());
            return toVo(role);
        }
        return null;
    }

    @Override
    public void setUserRole(Long userId, Long roleId) {
        if(userId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        //删除原有的关联关系
        rUserRoleMapper.deleteByUserId(userId);

        RUserRole rUserRole = new RUserRole();
        rUserRole.setUserId(userId);
        rUserRole.setRoleId(roleId);
        rUserRoleMapper.insertSelective(rUserRole);
    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void deleteRole(Long id) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        roleMapper.deleteByPrimaryKey(id);
        rRoleAuthMapper.deleteByRoleId(id);
    }

    @Override
    public RoleVo getRoleVo(Long roleId) {
        if(roleId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Role role = roleMapper.selectByPrimaryKey(roleId);
        return toVo(role);
    }

    @Override
    public void deleteUserRole(Long userId) {
        if(userId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        rUserRoleMapper.deleteByUserId(userId);
    }


}
