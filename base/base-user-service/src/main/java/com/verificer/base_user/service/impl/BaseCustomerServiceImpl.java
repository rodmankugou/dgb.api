package com.verificer.base_user.service.impl;

import com.verificer.base_user.service.*;
import com.verificer.beans.*;
import com.verificer.beans.bankcard.BackCardAddReqVo;
import com.verificer.beans.bankcard.BankcardVo;
import com.verificer.beans.customer.AdmWebUserVo;
import com.verificer.beans.customer.req.CustomerPageVo;
import com.verificer.dubbo.BaseDubboService;
import com.verificer.enums.CustomerVerifiedStatus;
import com.verificer.enums.SendCodeType;
import com.verificer.utils.StaffVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 对外提供的接口
 * Created by liujinhua on 2020/12/26.
 */
@Service("baseCustomerService")
public class BaseCustomerServiceImpl extends BaseDubboService implements BaseCustomerService {

    @Autowired
    CustomerService customerService;

    @Autowired
    LoginService loginService;

    @Autowired
    NationalService nationalService;

    @Autowired
    BankCardService bankCardService;

    @Autowired
    CustomerVerifiedService customerVerifiedService;

    @Autowired
    WhiteListService whiteListService;

    @Autowired
    CustomerExtendService customerExtendService;

    @Autowired
    EmailCustomerActivationServiceImpl emailCustomerActivationService;

    @Override
    public CustomerVo registerUser(Boolean isMobileClient, RegisterVo registerVo) {
        return customerService.registerUser(isMobileClient,registerVo);
    }


    /**
     * 检查用户账号是否存在（用户账号可以为 用户名\邮箱\手机号码）
     * @param account 用户名/邮箱/手机号码
     * @return true-已被注册 false-未被注册
     */
    public boolean checkAccountExist(String account){
        return customerService.checkAccountExist(account);
    }

    /**
     * 根据id获取customerVo
     * @param customerId
     * @return
     */
    public CustomerVo getCustomerVo(Long customerId){
        return customerService.getCustomerVo(customerId);
    }

    @Override
    public CustomerVo getCustomerVo(String account) {
        return customerService.getCustomerByAccount(account);
    }

    @Override
    public LoginRespVo loginByPassword(String client,String ip, Integer userType, String account, String password, String code, String imageId, String imageCode) {
        return loginService.loginByPassword(client,ip,userType,account,password,code,imageId,imageCode);
    }


    @Override
    public LoginRespVo loginByAddress(String client, String ip, String address, String publicKey, Long timestamp, String signature) {
        return loginService.loginByAddress(client,ip,address,publicKey,timestamp,signature);
    }

    @Override
    public LoginRespVo loginByCheckCode(String client,String ip, Integer userType, String account, String code, String imageId, String imageCode) {
        return loginService.loginByCheckCode(client,ip,userType,account,code,imageId,imageCode);
    }

    @Override
    public LoginRespVo loginByGoogleAuth(String client,String ip, Integer userType, String googleCode, String sign) {
        return loginService.loginByGoogleAuth(client,ip,userType,googleCode,sign);
    }

    @Override
    public void logout(LogoutReqVo logoutReqVo) {
        loginService.logout(logoutReqVo);
    }

    @Override
    public void sendRegCode(String language,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds) {
        loginService.sendRegCode(language,account,imageCode,imageId,codeExpireSeconds,retryLimitSeconds);
    }

    @Override
    public void sendCode(String language,String secnes,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds) {
        loginService.sendCode(language,secnes,account,imageCode,imageId,codeExpireSeconds,retryLimitSeconds);
    }

    @Override
    public void sendForgetPwdCode(String language,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds) {
        loginService.sendForgetPwdCode(language,account,imageCode,imageId,codeExpireSeconds,retryLimitSeconds);
    }




    @Override
    public void sendCodeIgnoreImageCode(String language,String scenes,String account, Long codeExpireSeconds, Long retryLimitSeconds) {
        loginService.sendCodeIgnoreImageCode(language,scenes,account,codeExpireSeconds,retryLimitSeconds);
    }

    @Override
    public void sendCodeIgnoreImageCode(String language,String scenes,Long customerId, Integer sendType, Long codeExpireSeconds, Long retryLimitSeconds) {
        loginService.sendCodeIgnoreImageCode(language,scenes,customerId,sendType,codeExpireSeconds,retryLimitSeconds);
    }

    @Override
    public boolean isGoogleCodeRight(Long customerid, String googleCode) {
        return loginService.isGoogleCodeRight(customerid,googleCode);
    }

    @Override
    public List<NationalVo> queryEnableNational(){
        return nationalService.queryEnableNational();
    }

    /**
     * 绑定手机
     * @param customerId 用户id
     * @param mobile 手机号码（含地区号）
     * @param mobileCode 手机验证码
     * @param emailCode 邮件验证码（如果为空，则不进行校验）
     */
    public void bindMobile(Long customerId,String mobile,String mobileCode,String emailCode){
        customerService.bindMobile(customerId,mobile,mobileCode,emailCode);
    }

    /**
     * 绑定邮箱
     * @param customerId 用户id
     * @param email 邮箱
     * @param mobileCode 手机验证码 （如果为空，则不进行校验）
     * @param emailCode 邮件验证码
     */
    public void bindEmail(Long customerId,String email,String mobileCode,String emailCode){
        customerService.bindEmail(customerId,email,mobileCode,emailCode);
    }

    /**
     * 绑定新手机号码
     * @param customerId 用户id
     * @param newMobile 新手机号码
     * @param newMobileCode 新手机号的验证码
     * @param oldMobileCode 原手机号的验证码，为空泽不检验，oldMobileCode和emailCode不可都为空
     * @param emailCode 邮件验证码，为空则不校验，oldMobileCode和emailCode不可都为空
     */
    public void modifyMobile(Long customerId,String newMobile,String newMobileCode,String oldMobileCode,String emailCode){
        customerService.modifyMobile(customerId,newMobile,newMobileCode,oldMobileCode,emailCode);
    }

    /**
     * 绑定新手机号码
     * @param customerId 用户id
     * @param newEmail 新手机号码
     * @param newEmailCode 新手机号的验证码
     * @param mobileCode 原手机号的验证码，为空泽不检验，mobileCode和oldEmailCode不可都为空
     * @param oldEmailCode 邮件验证码，为空则不校验，mobileCode和oldEmailCode不可都为空
     */
    public void modifyEmail(Long customerId,String newEmail,String newEmailCode,String mobileCode,String oldEmailCode){
        customerService.modifyEmail(customerId,newEmail,newEmailCode,mobileCode,oldEmailCode);
    }

    /**
     * @param customerId 用户id
     * @param password   交易密码
     * @param mobileCode 原手机号的验证码，为空泽不检验
     * @param emailCode  邮件验证码，为空则不校验
     */
    public void setUpPayPassword(Long customerId, String password, String mobileCode, String emailCode){
        customerService.setUpPayPassword(customerId,password,mobileCode,emailCode);
    }


    /**
     *
     * @param customerId 用户id
     * @param password 交易密码
     * @param rePassword 新交易密码
     * @param mobileCode 原手机号的验证码，为空泽不检验
     * @param emailCode 邮件验证码，为空则不校验
     */
    public void modifyPayPassword(Long customerId,String password,String rePassword,String mobileCode,String emailCode){
        customerService.modifyPayPassword(customerId,password,rePassword,mobileCode,emailCode);
    }

    /**
     * 获取用户绑定谷歌身份验证器的二维码
     * @param customerId
     */
    public GoogleQrcodeVo generateQrcode(Long customerId){
        return customerService.generateQrcode(customerId);
    }

    /**
     * 绑定谷歌身份验证器
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode 邮件验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void bindGoogleAuth(Long customerId,Long googleCode,String mobileCode,String emailCode){
        customerService.bindGoogleAuth(customerId,googleCode,mobileCode,emailCode);
    }

    /**
     * 解绑谷歌身份验证器
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode 邮件验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void closeGoogleAuth(Long customerId,Long googleCode,String mobileCode,String emailCode){
        customerService.closeGoogleAuth(customerId,googleCode,mobileCode,emailCode);
    }


    public void updateGoogleOpenStat(Long customerId,Boolean open,Integer type,String googleCode){
        customerService.updateGoogleOpenStat(customerId,open,type,googleCode);
    }

    /**
     * 修改登录密码
     * @param customerId 用户id
     * @param origPwd 原来的登录密码
     * @param newPwd 新登录密码
     * @param reNewPwd 确认信登录密码
     * @param mobileCode 手机验证码，为空则不校验
     * @param emailCode 邮件验证码,为空则不校验
     */
    public void modifyLoginPwd(Long customerId,String origPwd,String newPwd,String reNewPwd,String mobileCode, String emailCode){
        customerService.modifyLoginPwd(customerId,origPwd,newPwd,reNewPwd,mobileCode,emailCode);
    }

    @Override
    public void verifyForgetPwdEmailCode(String email, String code) {
        customerService.verifyForgetPwdCode(email,code);
    }

    @Override
    public void verifyForgetPwdMobileCode(String mobile, String code) {
        customerService.verifyForgetPwdCode(mobile,code);
    }

    /**
     * 忘记密码
     * @param account 手机号码/邮箱
     * @param code 手机/邮箱验证码
     * @param password 新密码
     * @param rePassword 确认新密码
     * @param imageId 图片验证码id
     * @param imageCode 图片验证码code
     */
    public void forgetLoginPwd(String account,String code,String password,String rePassword,String imageId, String imageCode){
        customerService.forgetLoginPwd(account,code,password,rePassword,imageId,imageCode);
    }


    /**
     * 获取用户的银行卡列表
     * @param customerId 用户id
     * @return
     */
    public List<BankcardVo> listCustomerBankcards(Long customerId){
        return bankCardService.listCustomerBankcards(customerId);
    }

    /**
     * 添加银行卡
     * @param customerId
     * @param reqVo
     */
    public void addBankcard(Long customerId,BackCardAddReqVo reqVo){
        bankCardService.addBankcard(customerId,reqVo);
    }

    /**
     * 删除银行卡
     * @param customerId 银行卡所属用户id
     * @param cardId 银行卡id
     */
    public void deleteBankcard(Long customerId,Long cardId){
        bankCardService.deleteBankcard(customerId,cardId);
    }


    public void submitVerifiedInfo(Long customerId,Long nationalId, String firstName, String lastName,IdCardType idCardType,
                                   String idCardNum, String idCardFrontPhoto, String idCardBackPhoto,
                                   String idCardRealPhoto){
        customerVerifiedService.submit(customerId,nationalId,firstName,lastName,idCardType,idCardNum,idCardFrontPhoto,idCardBackPhoto,idCardRealPhoto);
    }

    /**
     * 获取用户的实名认证信息
     * @param customerId
     * @param language 语言
     * @return
     */
    @Nullable
    public CustomerVerifiedVo getVerifiedInfoByCustomerId(Long customerId ,String language){
        return customerVerifiedService.getByCustomerId(customerId,language);
    }

    @Override
    public boolean isIdCardVerified(Long customerId) {
        return customerService.isIdCardVerified(customerId);
    }

    @Override
    public boolean hadPayPwd(Long customerId) {
        return customerService.hadPayPwd(customerId);
    }

    @Override
    public boolean isPayPwdRight(Long customerId, String password) {
        return customerService.isPayPwdRight(customerId,password);
    }

    @Override
    public void checkUserCode(Long customerId, String scene, SendCodeType sendType, String code) {
        customerService.checkUserCode(customerId,scene,sendType,code);
    }

    @Override
    public List<AdminCustomerVo> listCustomer(String language, CustomerListQueryVo customerListQueryVo) {
        return customerService.list(language,customerListQueryVo);
    }

    @Override
    public int countCustomer(CustomerListQueryVo customerListQueryVo) {
        return customerService.count(customerListQueryVo);
    }

    @Override
    public AdminCustomerVo getAdminCustomerVo(String language,Long id) {
        return customerService.getAdminCustomerVo(language,id);
    }

    @Override
    public void updateEnableStatus(Long id, Boolean enable) {
        customerService.updateEnableStatus(id,enable);
    }

    @Override
    public void updateInsiderFlag(Long id, Boolean isInsider) {
        customerService.updateInsiderFlag(id,isInsider);
    }

    @Override
    public void verifiedReview(Long customerId, CustomerVerifiedStatus status, String note) {
        customerVerifiedService.verifiedReview(customerId,status,note);
    }

    @Override
    public void whiteListApply(Long customerId, Integer exchangeId, WhiteListType applyType, String note, Long applyStaffId, String applyStaffName) {
        whiteListService.whiteListApply(customerId,exchangeId,applyType,note,applyStaffId,applyStaffName);
    }

    @Override
    public List<AdminWhiteListVo> listWhiteList(String language, WhiteListQueryVo whiteListQueryVo) {
        return whiteListService.list(language,whiteListQueryVo);
    }

    @Override
    public int countWhiteList(WhiteListQueryVo whiteListQueryVo) {
        return whiteListService.count(whiteListQueryVo);
    }

    @Override
    public void whiteListReview(Long whiteListId, WhiteListStatus status, Long auditStaffId, String auditStaffName) {
        whiteListService.review(whiteListId,status,auditStaffId,auditStaffName);
    }

    @Override
    public List<AdminCustVeriVo> listCustomerVerified(String language, CustVeriQueryVo queryVo) {
        return customerVerifiedService.list(language,queryVo);
    }

    @Override
    public int countCustomerVerified(CustVeriQueryVo queryVo) {
        return customerVerifiedService.count(queryVo);
    }


    @Override
    public CustomerVo getByUsername(String username) {
        return customerService.getByUsername(username);
    }

    @Override
    public CustomerVo addCustomer(String username, String nickname, String password, Long roleId, String roleName, String crmAccount) {
        return customerExtendService.addCustomer(username,nickname,password,roleId,roleName,crmAccount);
    }

    @Override
    public void updateCustomer(Long id,String nickname, Long roleId, String roleName, String crmAccount) {
        customerExtendService.updateCustomer(id,nickname,roleId,roleName,crmAccount);
    }

    @Override
    public void resetPassword(Long id, String password) {
        customerExtendService.resetPassword(id,password);
    }

    @Override
    public void updatePassword(Long id,String oldPassword,String newPassword) {
        customerExtendService.updatePassword(id,oldPassword,newPassword);
    }

    @Override
    public void delete(Long id) {
        customerExtendService.delete(id);
    }

    @Override
    public void onUpdateRole(Long roleId, String roleName) {
        customerExtendService.onUpdateRole(roleId,roleName);
    }

    @Override
    public void onDeleteRole(Long roleId) {
        customerExtendService.onDeleteRole(roleId);
    }

    @Override
    public List<StaffVo> getStaffList(String nickname, Integer limit) {
        return customerExtendService.getStaffList(nickname,limit);
    }

    @Override
    public AdminCustomerVo detailCustomer(Long id) {
        return customerExtendService.detailCustomer(id);
    }

    @Override
    public CustomerVo registerCustomer(String actiUrl,String ip,Integer userType,  String username, String password,String language) {
        return customerExtendService.registerCustomer(actiUrl,ip,userType,username,password,language);
    }

    @Override
    public void activeCustomerByMail(String code) {
        customerService.activeCustomerByMail(code);
    }

    @Override
    public List<AdmWebUserVo> pageWebUser(CustomerPageVo queryVo, String language) {
        return customerExtendService.pageWebUser(queryVo,language);
    }

    @Override
    public int countUser(CustomerPageVo queryVo) {
        return customerExtendService.countUser(queryVo);
    }

    @Override
    public String getActivationCode(String email) {
        return emailCustomerActivationService.getActivationCode(email);
    }

    @Override
    public void sendActivationMail(Long customerId, String actiUrl, String language) {
        customerService.sendActivationMail(customerId,actiUrl,language);
    }

    @Override
    public void sendActivationMail(String email, String actiUrl, String language) {
        customerService.sendActivationMail(email,actiUrl,language);
    }

    @Override
    public void updateUserInfo(Long id, UserInfoUpdVo updVo) {
        customerService.updateUserInfo(id,updVo);
    }

    @Override
    public CustomerVo getCustomerVoByAddr(String walletAddress) {
        return customerService.getCustomerVoByAddr(walletAddress);
    }

    @Override
    public CustomerVo getOrRegCustomerByAddr(String walletAddress) {
        return customerService.getOrRegCustomerByAddr(walletAddress);
    }

    @Override
    public Map<Long, CustomerVo> getCustomerVoMap(Set<Long> customerIds) {
        return customerService.getCustomerVoMap(customerIds);
    }


}
