package com.verificer.base_user.service;

import com.verificer.beans.AdminCustomerVo;
import com.verificer.beans.CustomerListQueryVo;
import com.verificer.beans.CustomerVo;
import com.verificer.beans.LoginRespVo;
import com.verificer.beans.customer.AdmWebUserVo;
import com.verificer.beans.customer.req.CustomerPageVo;
import com.verificer.utils.StaffVo;

import java.util.List;

/**
 * 扩展接口
 * Created by 35336 on 2021/3/1.
 */
public interface CustomerExtendService {
    /**
     * 新增用户
     * @param username
     * @param nickname
     * @param password
     * @param roleId
     * @param roleName
     */
    CustomerVo addCustomer(String username, String nickname, String password, Long roleId, String roleName, String crmAccount);

    /**
     * 修改用户
     * @param id
     * @param roleId
     * @param roleName
     */
    void updateCustomer(Long id,String nickname, Long roleId, String roleName,String crmAccount);


    /**
     * 充值密码
     * @param id
     * @param password
     */
    void resetPassword(Long id, String password);


    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(Long id,String oldPassword,String newPassword);


    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);


    /**
     * 当角色修改时的处理逻辑
     * @param roleId
     * @param roleName
     */
    void onUpdateRole(Long roleId, String roleName);


    /**
     * 删除角色时的处理逻辑
     * @param roleId
     */
    void onDeleteRole(Long roleId);

    /**
     * 获取员工列表
     * @param nickname
     * @param limit
     * @return
     */
    List<StaffVo> getStaffList(String nickname, Integer limit);

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    AdminCustomerVo detailCustomer(Long id);




    /**
     * 注册用户
     * @param actiUrl 激活链接
     * @param ip
     * @param username
     * @param password
     * @param language
     * @return
     **/
    CustomerVo  registerCustomer(String actiUrl,String ip,Integer userType, String username, String password,String language);

    /**
     * web端用户列表
     * @param queryVo
     * @param language
     * @return
     */
    List<AdmWebUserVo> pageWebUser(CustomerPageVo queryVo, String language);


    /**
     * 统计符合条件的web端用户数
     * @param queryVo
     * @return
     */
    int countUser(CustomerPageVo queryVo);
}
