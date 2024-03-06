package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.UtilConstants;
import com.verificer.base_user.config.UsrSrvConfig;
import com.verificer.base_user.constatns.UserVeriCodeScenes;
import com.verificer.base_user.entity.CustomerVerified;
import com.verificer.base_user.entity.InviteRecord;
import com.verificer.base_user.mapper.CustomerVerifiedMapper;
import com.verificer.base_user.mapper.InviteRecordMapper;
import com.verificer.base_user.service.*;
import com.verificer.beans.*;
import com.verificer.dubbo.BaseDubboService;
import com.verificer.enums.SendCodeType;
import com.verificer.utils.*;
import com.verificer.utils.web.PasswordUtil;
import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.entity.CustomerAttach;
import com.verificer.base_user.mapper.CustomerAttachMapper;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.IdInfoStatus;
import com.verificer.enums.RegisterType;
import com.verificer.message.utils.ActiveCodeUtil;
import com.verificer.utils.check.CheckUtil;
import com.verificer.utils.googalauth.GoogleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 35336 on 2020/12/25.
 */
@Service(value = "customerService")
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl extends BaseDubboService implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerAttachMapper customerAttachMapper;


    @Autowired
    NationalService nationalService;

    @Autowired
    InviteRecordMapper inviteRecordMapper;

    @Autowired
    ActiveCodeUtil activeCodeUtil;

    @Autowired
    UsrSrvConfig usrSrvConfig;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LimitHelper limitHelper;

    @Autowired
    LoginService loginService;

    @Autowired
    CustomerVerifiedMapper customerVerifiedMapper;


    @Autowired
    EmailCustomerActivationService emailCustomerActivationService;

    @Autowired
    AvatarService avatarService;



    /**
     * 根据id获取customerVo
     *
     * @param   id
     * @return
     */
    public CustomerVo getCustomerVo(Long id) {
        if(id ==  null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(customer == null){
            return null;
        }

        return entityToVo(customer);
    }

    @Override
    public CustomerVo getCustomerVoByAddr(String walletAddress) {
        if(SStringUtils.isEmpty(walletAddress))
            throw new BaseException(ErrCode.PARAMS_ERR);
        Customer customer = customerMapper.selectByWalletAddress(walletAddress);
        if(customer == null)
            return null;
        return entityToVo(customer);
    }

    @Override
    public CustomerVo getOrRegCustomerByAddr(String walletAddress) {
        if(SStringUtils.isEmpty(walletAddress))
            throw new BaseException(ErrCode.PARAMS_ERR);
        Customer customer = customerMapper.selectByWalletAddress(walletAddress);
        if(customer == null){
            RegisterVo vo = new RegisterVo();
            vo.setWalletAddress(walletAddress);
            try {
                registerUser(false,vo);
            } catch (Exception e) {
                //考虑并发时，导致同地址多次创建，从而导致的报错
            }
        }
        customer = customerMapper.selectByWalletAddress(walletAddress);
        if(customer == null)
            throw new RuntimeException("Create customer failed");
        return entityToVo(customer);
    }

    private CustomerVo entityToVo(Customer customer){
        if(customer == null)
            return null;


        CustomerVo customerVo = new CustomerVo();
        SBeanUtils.copyProperties2( customer,customerVo);
        if(customer.getRegType() != null){
            customerVo.setRegType(RegisterType.getByValue(customer.getRegType()));
        }

        if(customer.getIdInfoStatus() != null)
            customerVo.setIdInfoStatus(SEnumUtils.getByValue(IdInfoStatus.class,customer.getIdInfoStatus()));
        return customerVo;
    }

    /**
     * 检查用户账号是否存在（用户账号可以为 用户名\邮箱\手机号码）
     * @param account 用户名/邮箱/手机号码
     * @return true-已被注册 false-未被注册
     */
    public boolean checkAccountExist(String account){
        if(SStringUtils.isEmpty(account)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        return (checkMailExist(account) || checkMobileExist(account) || checkUsernameExist(account));
    }

    @Override
    public CustomerVo getCustomerByAccount(String account) {
        if(SStringUtils.isEmpty(account)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Customer customer = customerMapper.selectByEmail(account);
        if (customer != null){
            return entityToVo(customer);
        }

        customer = customerMapper.selectByMobile(account);
        if (customer != null){
            return entityToVo(customer);
        }

        throw new BaseException(ErrCode.USER_NOT_EXIST);
    }

    @Override
    public CustomerVo registerUser(Boolean isMobileClient, RegisterVo registerVo) {

        if (SStringUtils.isEmpty(registerVo.getPasswd()) && !SStringUtils.isEmpty(registerVo.getRePasswd()) && !registerVo.getPasswd().equals(registerVo.getRePasswd())) {
            throw new BaseException(ErrCode.REPWD_NOT_MATCH);
        }
        //检验手机/邮箱验证码
        if (!SStringUtils.isEmpty(registerVo.getCode()) && !usrSrvConfig.isDebug() && !activeCodeUtil.checkCode(UserVeriCodeScenes.REGISTER,registerVo.getAccount(), registerVo.getCode())) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        if (!SStringUtils.isEmpty(registerVo.getPasswd()) && !CheckUtil.checkLoginPassword(registerVo.getPasswd())) {
            throw new BaseException(ErrCode.LOGIN_PASSWORD_NOT_MATCH);
        }
        if (!SStringUtils.isEmpty(registerVo.getPayPasswd()) && !CheckUtil.checkPayPassword(registerVo.getPayPasswd())) {
            throw new BaseException(ErrCode.PAY_PWD_NOT_MATCH);
        }

        boolean isEmail = CheckUtil.checkEmail(registerVo.getAccount());

        if(SStringUtils.isEmpty(registerVo.getWalletAddress())){
            if (isEmail) {
                if (checkMailExist(registerVo.getAccount())) {
                    throw new BaseException(ErrCode.REG_MAIL_IS_EXISTING);
                }
            } else {
                String phoneCode = getPhoneCode(registerVo.getAccount());
                if (SStringUtils.isEmpty(phoneCode)) {
                    throw new BaseException(ErrCode.MOBILE_FORMAT_ERROR);
                }
                String phone = registerVo.getAccount().substring(phoneCode.length());

                String pCode = SStringUtils.isEmpty(phoneCode) ? "" : phoneCode;
                String mobile = pCode + phone;
                if(!CheckUtil.checkMobileNumber(mobile)){
                    throw new BaseException(ErrCode.MOBILE_FORMAT_ERROR);
                }
                if (checkMobileExist(mobile)) {
                    throw new BaseException(ErrCode.MOBILE_IS_EXISTING);
                }

            }
        }else {
            if(checkWalletAddressExist(registerVo.getWalletAddress()))
                throw new BaseException(ErrCode.WALLET_ADDRESS_IS_EXISTING);
        }


        //查询邀请人
        Customer inviter = null;
        if (!SStringUtils.isEmpty(registerVo.getInviteCode())) {
            inviter = queryByInviteCode(registerVo.getInviteCode());
            if (null == inviter) {
                throw new BaseException(ErrCode.INVITER_IS_ERROE);
            }
        }
        Customer customer = new Customer();
        customer.setUsername(UuidUtils.newUuid());
        if(!SStringUtils.isEmpty(registerVo.getPayPasswd()))
            customer.setPassword(PasswordUtil.loginPassword(registerVo.getPasswd()));
        if (!SStringUtils.isEmpty(registerVo.getPayPasswd())) {//有支付密码
            customer.setPayPassword(PasswordUtil.payPassword(registerVo.getPayPasswd()));
            customer.setHasPayPassword(true);
        } else {//无支付密码
            customer.setPayPassword(null);
            customer.setHasPayPassword(false);
        }

        Long now = System.currentTimeMillis();
        customer.setRegTime(now);
        customer.setCreateTime(now);
        customer.setUpdateTime(now);
        customer.setEnable(true);
        customer.setIsActivation(true);
        customer.setWalletAddress(registerVo.getWalletAddress());

        // 注册结果
        if(SStringUtils.isEmpty(registerVo.getWalletAddress())){
            if (isEmail) {
                customer.setMailAuth(true);
                customer.setEmail(registerVo.getAccount());
                customer.setRegType(RegisterType.EMAIL.getValue());
                customer.setMobileAuth(false);
            } else {
                String phoneCode = getPhoneCode(registerVo.getAccount());
                if (SStringUtils.isEmpty(phoneCode)) {
                    throw new BaseException(ErrCode.PARAMS_ERR);
                }
                String phone = registerVo.getAccount().substring(phoneCode.length());


                Long nationalId = getNationalIdByPhoneCode(phoneCode);
                if (nationalId != null) {
                    customer.setNationalId(nationalId);
                }

                customer.setMobile(registerVo.getAccount());
                customer.setMobileAuth(true);
                customer.setRegType(RegisterType.MOBILE.getValue());

                customer.setMailAuth(false);
            }
        }

        customer.setChannelId(registerVo.getChannelId());
        customer.setGoogleSecret(GoogleAuthenticator.generateSecretKey());
        customer.setGoogleSecretAuth(false);
        customer.setIdInfoStatus(IdInfoStatus.PASS.getValue());
        customer.setEnable(true);
        customer.setGoogleAuthIsOpen(false);
        customer.setWithdrawGoogleAuthIsOpen(false);
        if (inviter != null) {
            customer.setInviter(inviter.getId());
            customer.setRelation(inviter.getRelation() + customer.getId() + "-");
            String inviteCode = getInviteCode();
            customer.setInviteCode(inviteCode);

            if (isMobileClient) {
                customer.setInviteLink(InviteSetting.INVITE_MOBILE_LINK + inviteCode);
            } else {
                customer.setInviteLink(InviteSetting.INVITE_LINK + inviteCode);
            }
        } else {
            customer.setRelation(customer.getId() + "-");
            String code = getInviteCode();
            customer.setInviteCode(code);
            if (isMobileClient) {
                customer.setInviteLink(InviteSetting.INVITE_MOBILE_LINK + code);
            } else {
                customer.setInviteLink(InviteSetting.INVITE_LINK + code);
            }
        }

        if(SStringUtils.isEmpty(customer.getAvatar())){
            String avatar = avatarService.getRandomAvatar();
            customer.setAvatar(avatar);
        }

        if(SStringUtils.isEmpty(customer.getNickName())){
            if(!SStringUtils.isEmpty(customer.getWalletAddress()))
                customer.setNickName(customer.getWalletAddress().substring(customer.getWalletAddress().length()-8));
        }

        customerMapper.insertSelective(customer);


        if (null != inviter) {
            InviteRecord inviteRecord = new InviteRecord();
            inviteRecord.setCustomerId(inviter.getId());
            inviteRecord.setEnable(true);
            inviteRecord.setHasTrade(false);
            inviteRecord.setRegTime(now);
            inviteRecord.setInviteeId(customer.getId());
            inviteRecord.setInviteeAccount(registerVo.getAccount());
            inviteRecordMapper.insertSelective(inviteRecord);
        }

        //考虑到有些字段采用数据库是默认值
        return getCustomerVo(customer.getId());
    }

    private Customer queryByInviteCode(String inviteCode) {
        return customerMapper.selectByInviteCode(inviteCode);
    }


    private String getInviteCode() {
        String code = "";
        Customer customer = null;
        do {
            code = GenerateUtil.generateStrRecaptcha(6);
            customer = queryByInviteCode(code);
        } while (customer != null);
        return code;
    }

    /**
     * 获取到手机所在地区代码
     *
     * @param account
     * @return
     */
    public String getPhoneCode(String account) {
        List<NationalVo> nationalVos = nationalService.queryEnableNational();

        for (NationalVo vo : nationalVos) {
//            if(account.startsWith("+" + vo.getShortName())){
            if (account.startsWith("+" + vo.getCountryCode())) {
                return "+" + vo.getCountryCode();
            }
        }
        return null;
    }


    private Long getNationalIdByPhoneCode(String phoneCode) {
        List<NationalVo> nationalVos = nationalService.queryEnableNational();
        if (nationalVos != null && !nationalVos.isEmpty()) {
            for (NationalVo nvo : nationalVos) {
                if (phoneCode.equals("+" + nvo.getCountryCode())) {
                    return nvo.getId();
                }
            }
        }
        return null;
    }

    /**
     * 检查指定的邮箱是否已经被注册
     *
     * @param email
     * @return true-已被注册 false-未被注册
     */
    @Override
    public boolean checkMailExist(String email) {
        return customerMapper.selectByEmail(email) != null;
    }

    /**
     * 检查指定的用户名是否已经被注册
     *
     * @param username
     * @return true-已被注册 false-未被注册
     */
    @Override
    public boolean checkUsernameExist(String username) {
        return customerMapper.selectByUsername(username) != null;
    }

    /**
     * 检查指定的手机号码是否已经被注册
     *
     * @param mobile
     * @return true-已被注册 false-未被注册
     */
    @Override
    public boolean checkMobileExist(String mobile) {
        return customerMapper.selectByMobile(mobile) != null;
    }

    /**
     * 检查钱包地址是否已经被注册
     * @param walletAddress
     * @return
     */
    @Override
    public boolean checkWalletAddressExist(String walletAddress) {
        return customerMapper.selectByWalletAddress(walletAddress) != null;
    }

    /**
     * 绑定手机
     *
     * @param customerId 用户id
     * @param mobile     手机号码（含地区号）
     * @param mobileCode 手机验证码
     * @param emailCode  邮件验证码（如果为空，则不进行校验）
     */
    @Override
    public void bindMobile(Long customerId, String mobile, String mobileCode, String emailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (SStringUtils.isAnyEmpty(mobile, mobileCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!CheckUtil.checkMobileNumber(mobile)){
            throw new BaseException(ErrCode.MOBILE_FORMAT_ERROR);
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        if (customer.getMobileAuth() != null && customer.getMobileAuth()) {
            throw new BaseException(ErrCode.USER_HAD_MOBILE_AUTH);
        }

        //检验短信和邮件验证码
        if (!usrSrvConfig.isDebug() && !emailCode.isEmpty()
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.BIND_MOBILE,customer.getEmail(), emailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug()
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.BIND_MOBILE,mobile, mobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        //判断该手机号码是否已被使用
        if (checkMobileExist(mobile)) {
            throw new BaseException(ErrCode.MOBILE_IS_EXISTING);
        }

        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setMobile(mobile);
        tmp.setMobileAuth(true);
        tmp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    /**
     * 绑定邮箱
     *
     * @param customerId 用户id
     * @param email      邮箱
     * @param mobileCode 手机验证码 （如果为空，则不进行校验）
     * @param emailCode  邮件验证码
     */
    @Override
    public void bindEmail(Long customerId, String email, String mobileCode, String emailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (SStringUtils.isAnyEmpty(email, emailCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        if (customer.getMailAuth() != null && customer.getMailAuth()) {
            throw new BaseException(ErrCode.USER_HAD_MAIL_AUTH);
        }

        if(!CheckUtil.checkEmail(email)){
            throw new BaseException(ErrCode.EMAIL_FORMAT_ERROR);
        }

        //检验短信和邮件验证码
        if (!usrSrvConfig.isDebug()
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.BIND_EMAIL,email, emailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(mobileCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.BIND_EMAIL,customer.getMobile(), mobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        //判断该手机号码是否已被使用
        if (checkMailExist(email)) {
            throw new BaseException(ErrCode.MAIL_IS_EXISTING);
        }

        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setEmail(email);
        tmp.setMailAuth(true);
        tmp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    /**
     * 绑定新手机号码
     *
     * @param customerId    用户id
     * @param newMobile     新手机号码
     * @param newMobileCode 新手机号的验证码
     * @param oldMobileCode 原手机号的验证码，为空泽不检验，oldMobileCode和emailCode不可都为空
     * @param emailCode     邮件验证码，为空则不校验，oldMobileCode和emailCode不可都为空
     */
    @Override
    public void modifyMobile(Long customerId, String newMobile, String newMobileCode, String oldMobileCode, String emailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (SStringUtils.isAnyEmpty(newMobile, newMobileCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        //旧手机验证码h和邮件验证码不能全为空
        if (SStringUtils.isEmpty(oldMobileCode) && SStringUtils.isEmpty(emailCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!CheckUtil.checkMobileNumber(newMobile)){
            throw new BaseException(ErrCode.MOBILE_FORMAT_ERROR);
        }


        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }
        if (customer.getMobileAuth() != null && !customer.getMobileAuth()) {
            throw new BaseException(ErrCode.MOBILE_NOT_AUTH);
        }

        //校验验证码
        if (!usrSrvConfig.isDebug() &&
                !activeCodeUtil.checkCode(UserVeriCodeScenes.MODIFY_MOBILE,newMobile, newMobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(oldMobileCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.MODIFY_MOBILE,customer.getMobile(), oldMobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(emailCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.MODIFY_MOBILE,customer.getEmail(), emailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (checkMobileExist(newMobile)) {
            throw new BaseException(ErrCode.MOBILE_IS_EXISTING);
        }

        //更新数据
        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setMobile(newMobile);
        tmp.setMobileAuth(true);
        tmp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    /**
     * 绑定新邮箱
     *
     * @param customerId   用户id
     * @param newEmail     新手机号码
     * @param newEmailCode 新手机号的验证码
     * @param mobileCode   原手机号的验证码，为空泽不检验，oldMobileCode和emailCode不可都为空
     * @param oldEmailCode 邮件验证码，为空则不校验，oldMobileCode和emailCode不可都为空
     */
    public void modifyEmail(Long customerId, String newEmail, String newEmailCode, String mobileCode, String oldEmailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (SStringUtils.isAnyEmpty(newEmail, newEmailCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!CheckUtil.checkEmail(newEmail)){
            throw new BaseException(ErrCode.EMAIL_FORMAT_ERROR);
        }
        //手机验证码h和旧邮件验证码不能全为空
        if (SStringUtils.isEmpty(mobileCode) && SStringUtils.isEmpty(oldEmailCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }


        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }
        if (customer.getMailAuth() != null && !customer.getMailAuth()) {
            throw new BaseException(ErrCode.EMIAL_NOT_AUTH);
        }

        //校验验证码
        if (!usrSrvConfig.isDebug() &&
                !activeCodeUtil.checkCode(UserVeriCodeScenes.MODIFY_EMAIL,newEmail, newEmailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(mobileCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.MODIFY_EMAIL,customer.getMobile(), mobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(oldEmailCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.MODIFY_EMAIL,customer.getEmail(), oldEmailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (checkMailExist(newEmail)) {
            throw new BaseException(ErrCode.MAIL_IS_EXISTING);
        }

        //更新数据
        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setEmail(newEmail);
        tmp.setMailAuth(true);
        tmp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    /**
     * @param customerId 用户id
     * @param password   交易密码
     * @param mobileCode 原手机号的验证码，为空泽不检验
     * @param emailCode  邮件验证码，为空则不校验
     */
    public void setUpPayPassword(Long customerId, String password, String mobileCode, String emailCode) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);

        if(customer.getHasPayPassword() != null && customer.getHasPayPassword() == true)
            throw new BaseException(ErrCode.PAY_PWD_HAD_SET);

        this.modifyPayPassword(customerId,password,null,mobileCode,emailCode);
    }


    /**
     * @param customerId 用户id
     * @param password   交易密码
     * @param oldPassword 原支付密码,为空则不校验
     * @param mobileCode 原手机号的验证码，为空泽不检验
     * @param emailCode  邮件验证码，为空则不校验
     */
    public void modifyPayPassword(Long customerId, String password, String oldPassword, String mobileCode, String emailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (SStringUtils.isAnyEmpty(password)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }


        if (!CheckUtil.checkPayPassword2(password)) {
            throw new BaseException(ErrCode.PAY_PWD_NOT_MATCH);
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        if(!SStringUtils.isEmpty(oldPassword)){
            if(!PasswordUtil.payPassword(oldPassword).equals(customer.getPayPassword())){
                throw new BaseException(ErrCode.PAY_PWD_ERROR);
            }
        }

        //校验短信/手机验证码
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(mobileCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.UPDATE_PAY_PWD,customer.getMobile(), mobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(emailCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.UPDATE_PAY_PWD,customer.getEmail(), emailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        //TODO，添加验证失败时的限制逻辑

        //修改数据库
        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setPayPassword(PasswordUtil.payPassword(password));
        tmp.setHasPayPassword(true);
        tmp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(tmp);

    }

    /**
     * 获取用户绑定谷歌身份验证器的二维码
     *
     * @param customerId
     */
    public GoogleQrcodeVo generateQrcode(Long customerId) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        //原来的代码在这里进行了同步控制，因为使用了forUpdate
        Customer customer = customerMapper.selectByPrimaryKeyForUpdate(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        if (customer.getGoogleSecretAuth() != null && customer.getGoogleSecretAuth()) {//已认证google 验证器
            throw new BaseException(ErrCode.GOOGLE_AUTH_EXIST);
        }
        String secret = GoogleAuthenticator.generateSecretKey();
        String user = null;
        if (customer.getMailAuth() != null && customer.getMailAuth()) {//以邮箱优先
            user = customer.getEmail();
        } else {
            user = customer.getMobile();
        }
        String qrCode = GoogleAuthenticator.getQRBarcode(user, secret);

        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setGoogleSecret(secret);
        tmp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(tmp);

        GoogleQrcodeVo vo = new GoogleQrcodeVo();
        vo.setQrCode(qrCode);
        vo.setSecret(secret);
        return vo;
    }


    /**
     * 绑定谷歌身份验证器
     *
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode  短信验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void bindGoogleAuth(Long customerId, Long googleCode, String mobileCode, String emailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (googleCode == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (SStringUtils.isEmpty(mobileCode) && SStringUtils.isEmpty(emailCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        //校验短信/手机验证码
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(mobileCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.BIND_GOOGLE_AUTH,customer.getMobile(), mobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(emailCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.BIND_GOOGLE_AUTH,customer.getEmail(), emailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        if (customer.getGoogleSecretAuth() != null && customer.getGoogleSecretAuth()) {
            //已认证google 验证器
            throw new BaseException(ErrCode.GOOGLE_AUTH_EXIST);
        }

        long cur = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        if (!ga.check_code(customer.getGoogleSecret(), googleCode, cur)) {
            throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
        } else {
            //验证成功
            //更新成已绑定
            Customer tmp = new Customer();
            tmp.setId(customerId);

            tmp.setGoogleSecretAuth(true);
            tmp.setGoogleAuthIsOpen(true);
            tmp.setWithdrawGoogleAuthIsOpen(true);
            customerMapper.updateByPrimaryKeySelective(tmp);
        }
    }


    /**
     * 解绑谷歌身份验证器
     *
     * @param customerId 用户id
     * @param googleCode 谷歌验证码
     * @param mobileCode 手机验证码，为空则不校验，手机和短信验证米必须一个不为空
     * @param emailCode  短信验证码,为空则不校验，手机和短信验证米必须一个不为空
     */
    public void closeGoogleAuth(Long customerId, Long googleCode, String mobileCode, String emailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (googleCode == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (SStringUtils.isEmpty(mobileCode) && SStringUtils.isEmpty(emailCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        //校验短信/手机验证码
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(mobileCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.UN_BIND_GOOGLE_AUTH,customer.getMobile(), mobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(emailCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.UN_BIND_GOOGLE_AUTH,customer.getEmail(), emailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        if (customer.getGoogleSecretAuth() == null || !customer.getGoogleSecretAuth()) {
            throw new BaseException(ErrCode.GOOGLE_AUTH_NOTEXIST);
        }

        long cur = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        if (!ga.check_code(customer.getGoogleSecret(), googleCode, cur)) {
            throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
        } else {
            //验证成功
            //更新成已解绑
            Customer tmp = new Customer();
            tmp.setId(customerId);

            tmp.setGoogleSecretAuth(false);
            tmp.setGoogleAuthIsOpen(false);
            tmp.setWithdrawGoogleAuthIsOpen(false);
            customerMapper.updateByPrimaryKeySelective(tmp);
        }
    }

    /**
     * 登录/提现操作的谷歌安全验证的开关
     *
     * @param customerId 用户id
     * @param open       开关操作，true-开，false-关
     * @param type       操作的对象，1-登录验证 2-提现验证
     */
    public void updateGoogleOpenStat(Long customerId, Boolean open, Integer type,String googleCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (open == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if (type == null || (type != 1 && type != 2)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(!SStringUtils.isEmpty(googleCode)){
            if(!loginService.isGoogleCodeRight(customerId,googleCode)){
                throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
            }
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }
        if (customer.getGoogleSecretAuth() == null || !customer.getGoogleSecretAuth()) {
            //如果未绑定谷歌安全认证器
            throw new BaseException(ErrCode.GOOGLE_AUTH_NOTEXIST);
        }

        Customer temp = new Customer();
        temp.setId(customerId);
        if (type == 1) {
            temp.setGoogleAuthIsOpen(open);
        } else if (type == 2) {
            temp.setWithdrawGoogleAuthIsOpen(open);
        }
        temp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(temp);
    }


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
    public void modifyLoginPwd(Long customerId, String origPwd, String newPwd, String reNewPwd, String mobileCode, String emailCode) {
        if (customerId == null) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isAnyEmpty(origPwd,newPwd)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!SStringUtils.isEmpty(reNewPwd) && !newPwd.equals(reNewPwd)){
            throw new BaseException(ErrCode.REPWD_NOT_MATCH);

        }
        if (!CheckUtil.checkLoginPassword(newPwd)) {
            throw new BaseException(ErrCode.LOGIN_PASSWORD_NOT_MATCH);
        }

        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        //校验短信/手机验证码
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(mobileCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.UPDATE_PWD,customer.getMobile(), mobileCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }
        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(emailCode)
                && !activeCodeUtil.checkCode(UserVeriCodeScenes.UPDATE_PWD,customer.getEmail(), emailCode)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        //检查原登录密码
        if (!PasswordUtil.loginPassword(origPwd).equals(customer.getPassword())) {
            throw new BaseException(ErrCode.PWD_UPDATE_FAIL);
        }

        //更新数据
        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setPassword(PasswordUtil.loginPassword(newPwd));
        tmp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    @Override
    public void verifyForgetPwdCode(String account, String code) {
        if(SStringUtils.isAnyEmpty(account,code)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        //判断是否因验证码错误次数过多而受限
        int restCount = limitHelper.assertForgetPwdOperationNoLimit(account);;
        if(!usrSrvConfig.isDebug() && !activeCodeUtil.checkCode(UserVeriCodeScenes.FORGET_PWD,account,code)){
            limitHelper.onForgetPwdCodeVerifyFailed(account);

            restCount --;
            if(restCount == 0){
                Object[] errParam = {usrSrvConfig.getForgetPwdLimitCfg().getPeriod()/60};
                throw new BaseException(ErrCode.FORGET_PWD_CODE_ERR_LIMITED,errParam);
            }
            Object[] params = {restCount};
            throw new BaseException(ErrCode.FORGET_PWD_CODE_ERR_TIP,params);
        }
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

        if(SStringUtils.isAnyEmpty(account,code,password)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(!SStringUtils.isEmpty(rePassword) && !password.equals(rePassword)){
            throw new BaseException(ErrCode.REPWD_NOT_MATCH);
        }
        if (!CheckUtil.checkLoginPassword(password)) {
            throw new BaseException(ErrCode.LOGIN_PASSWORD_NOT_MATCH);
        }

        if (!SStringUtils.isEmpty(imageCode)) {
            if(!usrSrvConfig.isDebug() && !ImageCodeUtils.checkCode(redisUtil,imageId,imageCode)){
                throw new BaseException(ErrCode.IMAGE_CODE_CHECK_FAILED);
            }
        }

        //判断是否因验证码错误次数过多而受限
        int restCount = limitHelper.assertForgetPwdOperationNoLimit(account);;

        if(!usrSrvConfig.isDebug() && !activeCodeUtil.checkCode(UserVeriCodeScenes.FORGET_PWD,account,code)){
            limitHelper.onForgetPwdCodeVerifyFailed(account);

            restCount --;
            if(restCount == 0){
                Object[] errParam = {usrSrvConfig.getForgetPwdLimitCfg().getPeriod()/60};
                throw new BaseException(ErrCode.FORGET_PWD_CODE_ERR_LIMITED,errParam);
            }
            Object[] params = {restCount};
            throw new BaseException(ErrCode.FORGET_PWD_CODE_ERR_TIP,params);
        }


        boolean isEmail = CheckUtil.checkEmail(account);
        Customer customer = null;

        if (isEmail) {
            customer = customerMapper.selectByEmail(account);
        } else {
            customer = customerMapper.selectByMobile(account);
        }
        if(customer == null){
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }

        if(customer.getPassword().equals(PasswordUtil.loginPassword(password))){
            throw new BaseException(ErrCode.NEW_PWD_EQ_OLD);
        }

        Customer temp = new Customer();
        temp.setId(customer.getId());
        temp.setPassword(PasswordUtil.loginPassword(password));
        temp.setUpdateTime(System.currentTimeMillis());
        customerMapper.updateByPrimaryKeySelective(temp);
    }

    /**
     * 用户是否已经通过实名认证
     * @param customerId
     * @return
     */
    @Override
    public boolean isIdCardVerified(Long customerId){
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer.getIdInfoStatus() != null && IdInfoStatus.PASS.getValue() == customer.getIdInfoStatus();
    }


    /**
     * 判断用户是否已经设置了交易密码
     * @param customerId
     * @return
     */
    @Override
    public boolean hadPayPwd(Long customerId){
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer.getHasPayPassword() != null && customer.getHasPayPassword();
    }


    /**
     * 支付密码是否正确
     * @param customerId 用户id
     * @param password 支付密码
     * @return
     */
    public boolean isPayPwdRight(Long customerId, String password){
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(password)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerId);

        if(customer.getHasPayPassword() == null || !customer.getHasPayPassword()){
            return false;
        }

        return customer.getPayPassword().equals(PasswordUtil.payPassword(password));
    }


    /**
     * 检验用户验证码
     * @param customerId 用户id
     * @param scene 场景,参考UserVeriCodeScenes类中的定义
     * @param sendType 发送方式
     * @param code 验证码
     */
    @Override
    public void checkUserCode(Long customerId, String scene, SendCodeType sendType, String code) {
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(scene == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(sendType == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isEmpty(code)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Customer c = customerMapper.selectByPrimaryKey(customerId);
        if(c == null){
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }


        if(SendCodeType.EMAIL.equals(sendType)){
            if(!usrSrvConfig.isDebug() && !activeCodeUtil.checkCode(scene,c.getEmail(),code)){
                throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
            }
        }else if(SendCodeType.MOBILE.equals(sendType)){
            if(!usrSrvConfig.isDebug() &&  !activeCodeUtil.checkCode(scene,c.getMobile(),code)){
                throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
            }
        }else {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
    }

    private AdminCustomerVo toAdminVo(List<NationalVo> nationalVos,String language, Customer customer){
        if(customer == null){
            return null;
        }

        AdminCustomerVo vo = new AdminCustomerVo();
        SBeanUtils.copyProperties2(customer,vo);
        for(NationalVo nvo : nationalVos){
            if(customer.getNationalId() != null && customer.getNationalId().equals(nvo.getId())){
                if(language.equals(UtilConstants.LANG_ZH_CN)){
                    vo.setNationalName(nvo.getNationality());
                }else if(language.equals(UtilConstants.LANG_ZH_TW)){
                    vo.setNationalName(nvo.getNationalityTw());
                }else {
                    vo.setNationalName(nvo.getNationalityEn());
                }
            }


        }

        CustomerVerified customerVerified = customerVerifiedMapper.selectByCustomerId(customer.getId());
        if(customerVerified != null){
            vo.setIdCardNo(customerVerified.getIdCardNum());
            vo.setIdCardFrontPhoto(customerVerified.getIdCardFrontPhoto());
            vo.setIdCardBackPhoto(customerVerified.getIdCardBackPhoto());
            vo.setIdCardRealPhoto(customerVerified.getIdCardRealPhoto());
            vo.setRealVerifiedRemark(customerVerified.getRejectReasonMsg());
            vo.setFirstName(customerVerified.getFirstName());
            vo.setLastName(customerVerified.getLastName());
        }

        if(vo.getRegType() != null){
            vo.setRegType(RegisterType.getByValue(customer.getRegType()));
        }
        if(vo.getIdInfoStatus() != null){
            vo.setIdInfoStatus(IdInfoStatus.getByValue(customer.getIdInfoStatus()));

        }
        return vo;
    }

    @Override
    public List<AdminCustomerVo> list(String language,CustomerListQueryVo customerListQueryVo) {
        if(language == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        List<Customer> customers = customerMapper.page(customerListQueryVo);
        List<AdminCustomerVo> adminCustomerVos = new LinkedList<>();
        List<NationalVo> nationalVos = nationalService.queryEnableNational();
        for(Customer customer : customers){
            adminCustomerVos.add(toAdminVo(nationalVos,language,customer));
        }
        return adminCustomerVos;
    }

    @Override
    public int count(CustomerListQueryVo customerListQueryVo) {
        return customerMapper.count(customerListQueryVo);
    }

    /**
     * 获取用户详情（管理端接口）
     * @param id
     * @return
     */
    @Override
    public AdminCustomerVo getAdminCustomerVo(String language, Long id) {
        if(SStringUtils.isEmpty(language)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }


        Customer customer = customerMapper.selectByPrimaryKey(id);
        List<NationalVo> nationalVos = nationalService.queryEnableNational();
        return toAdminVo(nationalVos,language,customer);
    }

    /**
     * 禁用/启用用户
     * @param id
     * @param enable
     */
    @Override
    public void updateEnableStatus(Long id, Boolean enable) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(enable == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Customer tmp = new Customer();
        tmp.setId(id);
        tmp.setEnable(enable);
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    /**
     * 标记/取消项目方(内部人员)标记
     * @param id
     * @param isInsider
     */
    @Override
    public void updateInsiderFlag(Long id, Boolean isInsider) {
        if(id == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(isInsider == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }


        Customer tmp = new Customer();
        tmp.setId(id);
        tmp.setIsInsider(isInsider);
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    @Override
    public CustomerVo getByUsername(String username) {
        Customer customer = customerMapper.selectByUsername(username);
        return entityToVo(customer);
    }

    @Override
    public void activeCustomerByMail(String code) {
        if(SStringUtils.isEmpty(code)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        Long customerId = emailCustomerActivationService.verify(code);
        if(customerId == null)
            return;
        Customer tmp = new Customer();
        tmp.setId(customerId);
        tmp.setIsActivation(true);
        customerMapper.updateByPrimaryKeySelective(tmp);
    }

    @Override
    public void sendActivationMail(Long customerId,String actiUrl,String language) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer.getIsActivation())
            throw new BaseException(ErrCode.YOU_HAS_ACTIVATION);
        emailCustomerActivationService.sendActivationCode(customerId,actiUrl,language);
    }

    @Override
    public void sendActivationMail(String email, String actiUrl, String language) {
        Customer customer = customerMapper.selectByEmail(email);
        if(customer == null)
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        emailCustomerActivationService.sendActivationCode(customer.getId(),actiUrl,language);
    }

    @Override
    public void updateUserInfo(Long id, UserInfoUpdVo updVo) {
        if(id == null)
            throw new BaseException(ErrCode.PARAMS_ERR);

        if(customerMapper.selectByPrimaryKey(id) == null)
            throw new BaseException(ErrCode.USER_NOT_EXIST);

        Customer temp = new Customer();
        temp.setId(id);
        temp.setAvatar(updVo.getAvatar());
        temp.setRealName(updVo.getRealName());
        temp.setNickName(updVo.getNickName());
        temp.setEmail(updVo.getEmail());
        temp.setRemark(updVo.getRemark());
        Map<String,String> linksMap = new HashMap<>();
        linksMap.put("fbLink",updVo.getFbLink());
        linksMap.put("twiLink",updVo.getTwiLink());
        linksMap.put("insLink",updVo.getTwiLink());
        linksMap.put("webLink",updVo.getWebLink());
        temp.setLinks(FastJson.toJson(linksMap));

        customerMapper.updateByPrimaryKeySelective(temp);
    }

    @Override
    public Map<Long, CustomerVo> getCustomerVoMap(Set<Long> customerIds) {
        Map<Long,CustomerVo> rst = new HashMap<>();
        for(Long id : customerIds){
            if (id == null)
                continue;

            CustomerVo customerVo = getCustomerVo(id);
            rst.put(id,customerVo);
        }

        return rst;
    }

}
