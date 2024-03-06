package com.verificer.exchange.admin.service;

import com.verificer.exchange.admin.entity.Staff;
import com.verificer.exchange.admin.vo.StaffVo;

import java.util.List;

/**
 * Created by 35336 on 2021/1/22.
 */
public interface StaffService {
    /**
     * 登录,googleCode和mobileCode只需要验证其一
     * @param username
     * @param password
     * @param imageId
     * @param imageCode
     * @param googleCode
     * @param mobileCode
     * @return 登录失败则返回空
     */
    Staff login(String username, String password, String imageId, String imageCode, String googleCode, String mobileCode);

    /**
     * 获取员工详情
     * @param id
     * @return
     */
    StaffVo detail(Long id);

    /**
     * 分页获取员工数据
     * @param page
     * @param pageSize
     * @return
     */
    List<StaffVo> page(Integer page, Integer pageSize);

    /**
     * 获取符合查询条件的员工数量
     * @return
     */
    int count();

    /**
     * 添加员工
     * @param creatorId
     * @param username
     * @param realName
     * @param mobile
     * @param password
     * @param roleId
     * @param enable
     */
    void add(Long creatorId, String username, String realName, String mobile, String password, Long roleId, Integer enable);

    /**
     * 修改
     * @param id
     * @param roleId
     * @param enable
     */
    void update(Long id, String realName, Long roleId, Integer enable);

    /**
     * 修改可用状态
     * @param id
     * @param enable
     */
    void updateEnableStatus(Long id, Integer enable);

    /**
     * 修改锁定状态
     * @param id
     * @param lockStatus
     */
    void updateLockStatus(Long id, int lockStatus);

    /**
     * 根据id获取Staff
     * @param id
     * @return
     */
    Staff getById(Long id);

    /**
     * 根据id获取realName
     * @param id
     * @return
     */
    String getRealName(Long id);

    /**
     * 充值密码
     * @param id
     * @param defaultResetPassword
     */
    void resetPassword(Long id, String defaultResetPassword);

    /**
     * 修改密码
     * @param staffId
     * @param originPassword
     * @param password
     */
    void updatePassword(Long staffId, String originPassword, String password);

    Staff getByUserName(String username);
}
