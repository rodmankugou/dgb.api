package com.verificer.base_user.service;

import com.verificer.base_user.entity.Customer;
import com.verificer.beans.*;
import com.verificer.enums.SendCodeType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 35336 on 2020/12/25.
 */
public interface CustomerService{

    public CustomerVo getCustomerVo(Long id);

    /**
     * 根据登陆地址获取用户
     * @param receiveAddr
     * @return
     */
    CustomerVo getCustomerVoByAddr(String receiveAddr);

    /**
     * 根据地址获取用户，如果当前用户未注册，则先注册
     * @param walletAddress
     * @return
     */
    CustomerVo getOrRegCustomerByAddr(String walletAddress);

    public CustomerVo registerUser(Boolean isMobileClient, RegisterVo registerVo);

    /**
     * 检查指定的邮箱是否已经被注册
     * @param email
     * @return true-已被注册 false-未被注册
     */
    public boolean checkMailExist(String email);

    /**
     * 检查指定的手机号码是否已经被注册
     * @param mobile
     * @return true-已被注册 false-未被注册
     */
    public boolean checkMobileExist(String mobile);

    /**
     * 检查用户账号是否存在（用户账号可以为用户名\邮箱\手机号码）
     * @param account 用户名/邮箱/手机号码
     * @return true-已被注册 false-未被注册
     */
    public boolean checkAccountExist(String account);

    /**
     * 根据账户获取用户（用户账号可以为用户名\邮箱\手机号码）
     * @param account 用户名/邮箱/手机号码
     * @return
     */
    public CustomerVo getCustomerByAccount(String account);


    boolean checkWalletAddressExist(String walletAddress);

    /**
     * 绑定手机
     * @param customerId 用户id
     * @param mobile 手机号码（含地区号）
     * @param mobileCode 手机验证码
     * @param emailCode 邮件验证码（如果为空，则不进行校验）
     */
    public void bindMobile(Long customerId,String mobile,String mobileCode,String emailCode);

    /**
     * 绑定邮箱
     * @param customerId 用户id
     * @param email 邮箱
     * @param mobileCode 手机验证码 （如果为空，则不进行校验）
     * @param emailCode 邮件验证码
     */
    public void bindEmail(Long customerId,String email,String mobileCode,String emailCode);

    /**
     * 检查指定的用户名是否已经被注册
     *
     * @param username
     * @return true-已被注册 false-未被注册
     */
    public boolean checkUsernameExist(String username);

    /**
     * 绑定新手机号码
     * @param customerId 用户id
     * @param newMobile 新手机号码
     * @param newMobileCode 新手机号的验证码
     * @param oldMobileCode 原手机号的验证码，为空泽不检验，oldMobileCode和emailCode不可都为空
     * @param emailCode 邮件验证码，为空则不校验，oldMobileCode和emailCode不可都为空
     */
    public void modifyMobile(Long customerId,String newMobile,String newMobileCode,String oldMobileCode,String emailCode);



    /**
     * 绑定新手机号码
     * @param customerId 用户id
     * @param newEmail 新手机号码
     * @param newEmailCode 新手机号的验证码
     * @param mobileCode 原手机号的验证码，为空泽不检验，mobileCode和oldEmailCode不可都为空
     * @param oldEmailCode 邮件验证码，为空则不校验，mobileCode和oldEmailCode不可都为空
     */
    public void modifyEmail(Long customerId,String newEmail,String newEmailCode,String mobileCode,String oldEmailCode);

    /**
     * @param customerId 用户id
     * @param password   交易密码
     * @param mobileCode 原手机号的验证码，为空泽不检验
     * @param emailCode  邮件验证码，为空则不校验
     */
    public void setUpPayPassword(Long customerId, String password, String mobileCode, String emailCode);

    /**
     *
     * @param customerId 用户id
     * @param password 交易密码
     * @param rePassword 新交易密码
     * @param mobileCode 原手机号的验证码，为空泽不检验
     * @param emailCode 邮件验证码，为空则不校验
     */
    public void modifyPayPassword(Long customerId,String password,String rePassword,String mobileCode,String emailCode);


    /**
     * 获取用户绑定谷歌身份验证器的二维码
     * @param customerId
     */
    public GoogleQrcodeVo generateQrcode(Long customerId);


    /**
     * 绑定谷歌身份验证器
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode 邮件验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void bindGoogleAuth(Long customerId,Long googleCode,String mobileCode,String emailCode);


    /**
     * 解绑谷歌身份验证器
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode 邮件验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void closeGoogleAuth(Long customerId,Long googleCode,String mobileCode,String emailCode);

    /**
     * 登录/提现操作的谷歌安全验证的开关
     *
     * @param customerId 用户id
     * @param open       开关操作，true-开，false-关
     * @param type       操作的对象，1-登录验证 2-提现验证
     */
    public void updateGoogleOpenStat(Long customerId,Boolean open,Integer type,String googleCode);


    /**
     * 修改登录密码
     * @param customerId 用户id
     * @param origPwd 原来的登录密码
     * @param newPwd 新登录密码
     * @param reNewPwd 确认信登录密码
     * @param mobileCode 手机验证码，为空则不校验
     * @param emailCode 邮件验证码,为空则不校验
     */
    public void modifyLoginPwd(Long customerId,String origPwd,String newPwd,String reNewPwd,String mobileCode, String emailCode);

    /**
     * 校验忘记密码邮箱/手机验证码
     * @param email
     * @param code
     */
    void verifyForgetPwdCode(String email, String code);

    /**
     * 忘记密码
     * @param account 手机号码/邮箱
     * @param code 手机/邮箱验证码
     * @param password 新密码
     * @param rePassword 确认新密码
     * @param imageId 图片验证码id
     * @param imageCode 图片验证码code
     */
    public void forgetLoginPwd(String account,String code,String password,String rePassword, String imageId, String imageCode);

    /**
     * 用户是否已经通过实名认证
     * @param customerId
     * @return
     */
    boolean isIdCardVerified(Long customerId);

    /**
     * 判断用户是否已经设置了交易密码
     * @param customerId
     * @return
     */
    boolean hadPayPwd(Long customerId);

    /**
     * 支付密码是否正确
     * @param customerId 用户id
     * @param password 支付密码
     * @return
     */
    boolean isPayPwdRight(Long customerId, String password);


    /**
     * 检验用户验证码
     * @param customerId 用户id
     * @param scene 场景,参考UserVeriCodeScenes类中的定义
     * @param sendType 发送方式
     * @param code 验证码
     */
    void checkUserCode(Long customerId, String scene, SendCodeType sendType, String code);


    /**
     * 分页查询用戶列表
     * @param language
     * @param customerListQueryVo
     */
    List<AdminCustomerVo> list(String language,CustomerListQueryVo customerListQueryVo);

    /**
     * 统计符合条件的用户数
     * @param customerListQueryVo
     * @return
     */
    int count(CustomerListQueryVo customerListQueryVo);

    /**
     * 获取用户详情（管理端接口）
     * @param id
     * @return
     */
    AdminCustomerVo getAdminCustomerVo(String language,Long id);

    /**
     * 禁用/启用用户
     * @param id
     * @param enable
     */
    void updateEnableStatus(Long id, Boolean enable);


    /**
     * 标记/取消项目方(内部人员)标记
     * @param id
     * @param isInsider
     */
    void updateInsiderFlag(Long id, Boolean isInsider);


    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    CustomerVo getByUsername(String username);

    /**
     * 邮件方式激活
     * @param code 激活码
     */
    void activeCustomerByMail(String code);

    /**
     * 发送激活邮件
     * @param customerId
     * @param actiUrl
     * @param language
     */
    void sendActivationMail(Long customerId,String actiUrl,String language);

    /**
     * 发送激活邮件
     * @param email
     * @param actiUrl
     * @param language
     */
    void sendActivationMail(String email,String actiUrl,String language);

    /**
     * 修改用户信息
     * @param id
     * @param updVo
     */
    void updateUserInfo(Long id, UserInfoUpdVo updVo);

    /**
     * 获取多个用户
     * @param customerIds
     * @return
     */
    Map<Long, CustomerVo> getCustomerVoMap(Set<Long> customerIds);



}