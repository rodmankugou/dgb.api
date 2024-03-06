package com.verificer.base_user.service;

import com.verificer.beans.*;
import com.verificer.beans.bankcard.BackCardAddReqVo;
import com.verificer.beans.bankcard.BankcardVo;
import com.verificer.beans.customer.AdmWebUserVo;
import com.verificer.beans.customer.req.CustomerPageVo;
import com.verificer.enums.CustomerVerifiedStatus;
import com.verificer.enums.SendCodeType;
import com.verificer.utils.StaffVo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 35336 on 2020/12/26.
 */
public interface BaseCustomerService {

    /**
     * 根据id获取customerVo
     *
     * @param customerId
     * @return
     */
    public CustomerVo getCustomerVo(Long customerId);

    /**
     * 根据账户获取customerVo
     *
     * @param account
     * @return
     */
    public CustomerVo getCustomerVo(String account);

    /**
     * 用户注册
     *
     * @param isMobileClient 是否手机客户端
     * @param registerVo
     */
    public CustomerVo registerUser(Boolean isMobileClient, RegisterVo registerVo);


    /**
     * 检查用户账号是否存在（用户账号可以为 用户名\邮箱\手机号码）
     *
     * @param account 用户名/邮箱/手机号码
     * @return true-已被注册 false-未被注册
     */
    public boolean checkAccountExist(String account);

    /**
     * @param client    客户端，如app或web
     * @param ip        用户ip
     * @param userType  用户类型
     * @param account   用户账号（邮箱/手机）
     * @param password  用户密码
     * @param code      手机/邮件验证码，如果该参数为空，表示不需要短信验证码校验
     * @param imageCode 图片验证码，如果该参数为空，表示不需要图片验证码校验
     * @param imageId   图片验证码的图片id
     */
    public LoginRespVo loginByPassword(String client, String ip,Integer userType, String account, String password, String code, String imageId, String imageCode);

    /**
     * @param client    客户端，如app或web
     * @param ip        用户ip
     * @param address 钱包地址
     * @param publicKey 公钥
     * @param timestamp 时间戳，时间偏差不在user服务中校验
     * @param signature 签名，sign（address+timestamp）
     * @return
     */
    LoginRespVo loginByAddress(String client, String ip, String address, String publicKey, Long timestamp, String signature);

    /**
     * @param client    客户端，如app或web
     * @param ip        用户ip
     * @param userType  用户类型
     * @param account   用户账号（邮箱/手机）
     * @param code      手机/邮件验证码，如果该参数为空，表示不需要短信验证码校验
     * @param imageCode 图片验证码，如果该参数为空，表示不需要图片验证码校验
     * @param imageId   图片验证码的图片id
     */
    public LoginRespVo loginByCheckCode(String client, String ip, Integer userType, String account, String code, String imageId, String imageCode);


    /**
     * 谷歌验证器方式登录
     *
     * @param client     客户端，如app或web
     * @param ip
     * @param userType  用户类型
     * @param googleCode
     * @param sign
     * @return
     */
    public LoginRespVo loginByGoogleAuth(String client, String ip, Integer userType, String googleCode, String sign);

    /**
     * 退出登录
     *
     * @param logoutReqVo
     */
    public void logout(LogoutReqVo logoutReqVo);

    /**
     * 发送注册验证码
     * @param  language 语言
     * @param account
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    public void sendRegCode(String language,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds);


    /**
     * 发送注册手机/邮箱验证码,需验证图片验证码
     *
     * @param language 语言
     * @param scenes 场景，如登录、注册等
     * @param account           手机/邮箱
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    public void sendCode(String language,String scenes,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds);


    /**
     * 找回密碼場景发送手机/邮箱验证码,需校验图片验证码
     *
     * @param language 语言
     * @param account           手机/邮箱
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    public void sendForgetPwdCode(String language,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds);


    /**
     * 不校验图片验证码发送发送手机/邮箱验证码
     * @param language 语言
     * @param scenes 场景，如登录、注册等
     * @param account 手机/邮箱
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    void sendCodeIgnoreImageCode(String language, String scenes,String account,Long codeExpireSeconds, Long retryLimitSeconds);


    /**
     * 不校验图片验证码发送发送手机/邮箱验证码
     *
     * @param language 语言
     * @param scenes 场景，如登录、注册等
     * @param customerId        用户id
     * @param sendType          sendType
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    void sendCodeIgnoreImageCode(String language,String scenes,Long customerId, Integer sendType, Long codeExpireSeconds, Long retryLimitSeconds);

    /**
     * 谷歌验证码校验
     * @param customerId
     * @param googleCode
     * @return
     */
    boolean isGoogleCodeRight(Long customerId, String googleCode);

    /**
     * 获取可用的地区列表
     *
     * @return
     */
    List<NationalVo> queryEnableNational();

    /**
     * 绑定手机
     *
     * @param customerId 用户id
     * @param mobile     手机号码（含地区号）
     * @param mobileCode 手机验证码
     * @param emailCode  邮件验证码（如果为空，则不进行校验）
     */
    public void bindMobile(Long customerId, String mobile, String mobileCode, String emailCode);

    /**
     * 绑定邮箱
     *
     * @param customerId 用户id
     * @param email      邮箱
     * @param mobileCode 手机验证码 （如果为空，则不进行校验）
     * @param emailCode  邮件验证码
     */
    public void bindEmail(Long customerId, String email, String mobileCode, String emailCode);

    /**
     * 绑定新手机号码
     *
     * @param customerId    用户id
     * @param newMobile     新手机号码
     * @param newMobileCode 新手机号的验证码
     * @param oldMobileCode 原手机号的验证码，为空泽不检验，oldMobileCode和emailCode不可都为空
     * @param emailCode     邮件验证码，为空则不校验，oldMobileCode和emailCode不可都为空
     */
    public void modifyMobile(Long customerId, String newMobile, String newMobileCode, String oldMobileCode, String emailCode);

    /**
     * 绑定新手机号码
     *
     * @param customerId   用户id
     * @param newEmail     新手机号码
     * @param newEmailCode 新手机号的验证码
     * @param mobileCode   原手机号的验证码，为空泽不检验，mobileCode和oldEmailCode不可都为空
     * @param oldEmailCode 邮件验证码，为空则不校验，mobileCode和oldEmailCode不可都为空
     */
    public void modifyEmail(Long customerId, String newEmail, String newEmailCode, String mobileCode, String oldEmailCode);

    /**
     * @param customerId 用户id
     * @param password   交易密码
     * @param mobileCode 原手机号的验证码，为空泽不检验
     * @param emailCode  邮件验证码，为空则不校验
     */
    public void setUpPayPassword(Long customerId, String password, String mobileCode, String emailCode);

    /**
     * @param customerId   用户id
     * @param password     交易密码
     * @param rePassword   新交易密码
     * @param mobileCode   原手机号的验证码，为空泽不检验，mobileCode和emailCode不可都为空
     * @param emailCode 邮件验证码，为空则不校验，mobileCode和emailCode不可都为空
     */
    public void modifyPayPassword(Long customerId, String password, String rePassword, String mobileCode, String emailCode);

    /**
     * 获取用户绑定谷歌身份验证器的二维码
     *
     * @param customerId
     */
    public GoogleQrcodeVo generateQrcode(Long customerId);

    /**
     * 绑定谷歌身份验证器
     *
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode  邮件验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void bindGoogleAuth(Long customerId, Long googleCode, String mobileCode, String emailCode);

    /**
     * 解绑谷歌身份验证器
     *
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode  邮件验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void closeGoogleAuth(Long customerId, Long googleCode, String mobileCode, String emailCode);


    /**
     * 登录/提现操作的谷歌安全验证的开关
     *
     * @param customerId 用户id
     * @param open       开关操作，true-开，false-关
     * @param type       操作的对象，1-登录验证 2-提现验证
     */
    public void updateGoogleOpenStat(Long customerId, Boolean open, Integer type,String googleCode);

    /**
     * 修改登录密码
     *
     * @param customerId 用户id
     * @param origPwd    原来的登录密码
     * @param newPwd     新登录密码
     * @param reNewPwd   确认信登录密码
     * @param mobileCode 手机验证码，为空则不校验
     * @param emailCode  邮件验证码,为空则不校验
     */
    public void modifyLoginPwd(Long customerId, String origPwd, String newPwd, String reNewPwd, String mobileCode, String emailCode);


    /**
     * 校验忘记密码邮箱验证码
     * @param email
     * @param code
     */
    void verifyForgetPwdEmailCode(String email, String code);

    /**
     * 校验忘记密码邮箱验证码
     * @param mobile
     * @param code
     */
    void verifyForgetPwdMobileCode(String mobile, String code);


    /**
     * 忘记密码
     *
     * @param account    手机号码/邮箱
     * @param code       手机/邮箱验证码
     * @param password   新密码
     * @param rePassword 确认新密码
     * @param imageId    图片验证码id
     * @param imageCode  图片验证码code
     */
    public void forgetLoginPwd(String account, String code, String password, String rePassword, String imageId, String imageCode);


    /**
     * 获取用户的银行卡列表
     *
     * @param customerId 用户id
     * @return
     */
    List<BankcardVo> listCustomerBankcards(Long customerId);

    /**
     * 添加银行卡
     *
     * @param customerId
     * @param reqVo
     */
    void addBankcard(Long customerId, BackCardAddReqVo reqVo);

    /**
     * 删除银行卡
     *
     * @param customerId 银行卡所属用户id
     * @param cardId     银行卡id
     */
    void deleteBankcard(Long customerId, Long cardId);


    /**
     * 提交实名认证资料
     *
     * @param customerId       用户id
     * @param nationalId       国家/地区id
     * @param firstName        名字
     * @param lastName         姓氏
     * @param idCardType       证件类型
     * @param idCardNum        证件号码
     * @param idCardFrontPhoto 证件正面照url
     * @param idCardBackPhoto  证件背面照url
     * @param idCardRealPhoto  证件与真人合照url
     */
    void submitVerifiedInfo(Long customerId, Long nationalId, String firstName, String lastName,IdCardType idCardType,
                            String idCardNum, String idCardFrontPhoto, String idCardBackPhoto,
                            String idCardRealPhoto);

    /**
     * 获取用户的实名认证信息
     * @param customerId
     * @param language 语言
     * @return
     */
    @Nullable
    CustomerVerifiedVo getVerifiedInfoByCustomerId(Long customerId ,String language);

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
    List<AdminCustomerVo>  listCustomer(String language,CustomerListQueryVo customerListQueryVo);

    /**
     * 统计符合条件的用户数
     * @param customerListQueryVo
     * @return
     */
    int countCustomer(CustomerListQueryVo customerListQueryVo);

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
     *
     * @param customerId 用户id
     * @param status 审核状态
     * @param note 备注
     */
    void verifiedReview(Long customerId, CustomerVerifiedStatus status, String note);

    /**
     * 白名单申请
     * @param customerId 用户id
     * @param exchangeId 交易对id
     * @param applyType 白名单类型
     * @param note 备注
     * @param applyStaffId 提交申请的员工id
     * @param applyStaffName 提交申请的员工姓名
     */
    public void whiteListApply(Long customerId, Integer exchangeId, WhiteListType applyType, String note, Long applyStaffId, String applyStaffName);


    /**
     * 分页查询白名单列表
     * @param language
     * @param whiteListQueryVo
     * @return
     */
    List<AdminWhiteListVo> listWhiteList(String language, WhiteListQueryVo whiteListQueryVo);

    /**
     * 统计分页数量
     * @param whiteListQueryVo
     * @return
     */
    int countWhiteList(WhiteListQueryVo whiteListQueryVo);

    /**
     * 审核通过/驳回白名单
     * @param whiteListId 白名单id
     * @param status 状态
     * @param auditStaffId 审核员id
     * @param auditStaffName 审核员姓名
     */
    void whiteListReview(Long whiteListId, WhiteListStatus status, Long auditStaffId, String auditStaffName);

    /**
     * 实名认证列表
     * @param language
     * @param queryVo
     * @return
     */
    List<AdminCustVeriVo> listCustomerVerified(String language, CustVeriQueryVo queryVo);

    /**
     * 符合条件的实名认证数
     * @param queryVo
     * @return
     */
    int countCustomerVerified(CustVeriQueryVo queryVo);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    CustomerVo getByUsername(String username);

    /**
     * 扩展接口
     * @param username
     * @param nickname
     * @param password
     * @param roleId
     * @param roleName
     * @return
     */
    CustomerVo addCustomer(String username, String nickname, String password, Long roleId, String roleName,String crmAccount);

    /**
     * 扩展接口
     * @param id
     * @param roleId
     * @param roleName
     */
    void updateCustomer(Long id,String nickname, Long roleId, String roleName,String crmAccount);

    /**
     * 扩展接口,充值密码
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
    CustomerVo registerCustomer(String actiUrl,String ip,Integer userType,  String username, String password,String language);

    /**
     * 激活
     * @param code 激活码
     */
    void activeCustomerByMail(String code);

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

    /**
     * 获取用户的激活码
     * @param email
     * @return
     */
    String getActivationCode(String email);


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
     * 根据登陆地址获取用户
     * @param walletAddress
     * @return
     */
    CustomerVo getCustomerVoByAddr(String walletAddress);

    /**
     * 根据地址获取用户，如果当前用户未注册，则先注册
     * @param walletAddress
     * @return
     */
    CustomerVo getOrRegCustomerByAddr(String walletAddress);

    /**
     * 获取多个用户
     * @param customerIds
     * @return
     */
    Map<Long, CustomerVo> getCustomerVoMap(Set<Long> customerIds);
}
