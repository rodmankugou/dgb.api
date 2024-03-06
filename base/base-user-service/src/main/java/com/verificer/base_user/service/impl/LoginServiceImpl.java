package com.verificer.base_user.service.impl;

import com.google.common.base.Strings;
import com.verificer.ErrCode;
import com.verificer.base_user.config.UsrSrvConfig;
import com.verificer.base_user.constatns.UserVeriCodeScenes;
import com.verificer.base_user.constatns.UserConstants;
import com.verificer.base_user.entity.CustomerLoginRecord;
import com.verificer.base_user.mapper.CustomerLoginRecordMapper;
import com.verificer.beans.*;
import com.verificer.dubbo.BaseDubboService;
import com.verificer.utils.web.PasswordUtil;
import com.verificer.base_user.cache.CacheService;
import com.verificer.base_user.config.SecurityConf;
import com.verificer.base_user.entity.Customer;
import com.verificer.base_user.mapper.CustomerMapper;
import com.verificer.base_user.service.CustomerService;
import com.verificer.base_user.service.LoginService;
import com.verificer.common.exception.BaseException;
import com.verificer.enums.RegisterType;
import com.verificer.message.utils.ActiveCodeUtil;
import com.verificer.utils.*;
import com.verificer.utils.check.CheckUtil;
import com.verificer.utils.googalauth.GoogleAuthenticator;
import com.verificer.utils.match.FormatUtil;
import org.apache.http.cookie.SM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 35336 on 2020/12/24.
 */
@Service("loginService")
public class LoginServiceImpl extends BaseDubboService implements LoginService {
    @Autowired
    UsrSrvConfig usrSrvConfig;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ActiveCodeUtil activeCodeUtil;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CustomerService customerService;

    @Autowired
    CacheService cacheService;

    @Autowired
    CustomerLoginRecordMapper customerLoginRecordMapper;

    @Autowired
    LimitHelper limitHelper;

    /**
     * @param client 客户端，如app或web
     * @param ip        用户ip
     * @param account   用户账号（邮箱/手机）
     * @param password  用户密码
     * @param code      手机/邮件验证码，如果该参数为空，表示不需要短信验证码校验
     * @param imageCode 图片验证码，如果该参数为空，表示不需要图片验证码校验
     * @param imageId   图片验证码的图片id
     */
    @Override
    public LoginRespVo loginByPassword(String client,String ip, Integer userType, String account, String password, String code, String imageId, String imageCode){
        if (SStringUtils.isEmpty(account) || SStringUtils.isEmpty(password)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(imageCode) && !ImageCodeUtils.checkCode(redisUtil, imageId, imageCode)) {
            throw new BaseException(ErrCode.IMAGE_CODE_CHECK_FAILED);
        }

        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(code) && !activeCodeUtil.checkCode(UserVeriCodeScenes.LOGIN,account, code)) {
            throw new BaseException(ErrCode.VERIFY_CODE_FAILED);
        }

        if(userType == null)
            throw new BaseException(ErrCode.PARAMS_ERR);

        boolean isEmail = CheckUtil.checkEmail(account);
        Customer customer = getByAccount(account);

        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }


        //判断登录操作是否受限
        int restCount = limitHelper.assertLoginPwdErrorNoLimit(account);
        if (!customer.getPassword().equals(PasswordUtil.loginPassword(password))) {
            afterLoginFailed(account);

            //记录登录错误事件,以用于后续的错误次数限制
            limitHelper.onLoginPwdError(account);
            if(--restCount == 0){
                Object[] errParam = {usrSrvConfig.getLoginPwdErrLmtCfg().getPeriod()/60};
                throw new BaseException(ErrCode.LOGIN_PWD_ERR_LIMITED,errParam);
            }else {
                Object[] errParam = {restCount};
                throw new BaseException(ErrCode.LOGIN_PWD_ERR_TIP,errParam);
            }

        }

        //after login success
        return afterLoginSuccess(client,ip,imageId,account, customer);
    }



    @Override
    public LoginRespVo loginByAddress(String client, String ip, String address, String publicKey, Long timestamp, String signature) {
        if (SStringUtils.isEmpty(address) ) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }



        Customer customer = customerMapper.selectByWalletAddress(address);
        if(customer == null){
            RegisterVo regVo = new RegisterVo();
            regVo.setWalletAddress(address);
            customerService.registerUser(false,regVo);
            customer = customerMapper.selectByWalletAddress(address);
        }

        boolean isValidate = false;
        //TODO 以后添加娇艳时间戳逻辑
        String text = address+"-"+timestamp;
        int restCount = limitHelper.assertLoginPwdErrorNoLimit(address);
        if(!SMetaMaskUtils.validate(signature,text,address)){
            afterLoginFailed(address);
            //记录登录错误事件,以用于后续的错误次数限制
            limitHelper.onLoginPwdError(address);
            if(--restCount == 0){
                Object[] errParam = {usrSrvConfig.getLoginPwdErrLmtCfg().getPeriod()/60};
                throw new BaseException(ErrCode.LOGIN_PWD_ERR_LIMITED,errParam);
            }else {
                Object[] errParam = {restCount};
                throw new BaseException(ErrCode.LOGIN_PWD_ERR_TIP,errParam);
            }
        }

        //after login success9
        return afterLoginSuccess(client,ip,null,address, customer);
    }

    /**
     * @param client 客户端，如app或web
     * @param ip        用户ip
     * @param account   用户账号（邮箱/手机）
     * @param code      手机/邮件验证码，如果该参数为空，表示不需要短信验证码校验
     * @param imageCode 图片验证码，如果该参数为空，表示不需要图片验证码校验
     * @param imageId   图片验证码的图片id
     */
    @Override
    public LoginRespVo loginByCheckCode(String client,String ip, Integer userType, String account, String code, String imageId, String imageCode) {
        if (SStringUtils.isEmpty(account) || SStringUtils.isEmpty(code)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }

        if(userType == null)
            throw new BaseException(ErrCode.PARAMS_ERR);


        if (!usrSrvConfig.isDebug() && !SStringUtils.isEmpty(imageCode) && !ImageCodeUtils.checkCode(redisUtil, imageId, imageCode)) {
            throw new BaseException(ErrCode.IMAGE_CODE_CHECK_FAILED);
        }

        if (!usrSrvConfig.isDebug() && !activeCodeUtil.checkCode(UserVeriCodeScenes.LOGIN,account, code)) {
            afterLoginFailed(account);
        }

        if (!CheckUtil.checkEmail(account) && !CheckUtil.checkMobileNumber(account)) {
            //不支持用户名方式通过验证码登录
            throw new BaseException(ErrCode.ACCOUNT_PATTERN_ERROR);
        }

        Customer customer = getByAccount(account);

        if (customer == null) {
            throw new BaseException(ErrCode.USER_NOT_EXIST);
        }



        //after login success
        return afterLoginSuccess(client,ip,imageId,account, customer);
    }


    /**
     * 谷歌验证器方式登录
     * @param client 客户端，如app或web
     * @param ip
     * @param googleCode
     * @param sign
     * @return
     */
    @Override
    public LoginRespVo loginByGoogleAuth(String client,String ip, Integer userType,String googleCode, String sign) {
        if (Strings.isNullOrEmpty(googleCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(userType == null)
            throw new BaseException(ErrCode.PARAMS_ERR);
        Long gCode = null;
        try {
            gCode = Long.valueOf(googleCode);
        } catch (Exception e) {
            throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
        }
        Object obj = redisUtil.get(sign);
        if (null == obj) {
            throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
        }
        UserIdentity userIdentity = (UserIdentity) obj;
        boolean googleCheck = isGoogleRight(userIdentity.getId(), gCode);
        if (!googleCheck) {
            throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
        }

        CustomerVo customerVo = customerService.getCustomerVo(userIdentity.getId());
        if(userType != customerVo.getUserType())
            throw new BaseException(ErrCode.LOGIN_PASSWORD_ERROR);

        LoginRespVo resp = new LoginRespVo();
        resp.setMobile(customerVo.getMobile());
        resp.setEmail(customerVo.getEmail());
        resp.setUserId(customerVo.getId());
        resp.setGoogleAuthIsOpen(customerVo.getGoogleAuthIsOpen());
        resp.setGoogleSecretAuth(customerVo.getGoogleSecretAuth());
        resp.setNeedGoogleAuth(false);
        addLoginInfo(customerVo.getId(), ip,null,System.currentTimeMillis());
        return resp;
    }

    /**
     * 谷歌验证码校验
     * @param customerId
     * @param googleCode
     * @return
     */
    @Override
    public boolean isGoogleCodeRight(Long customerId, String googleCode){
        if (SStringUtils.isEmpty(googleCode)) {
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Long gCode = null;
        try {
            gCode = Long.valueOf(googleCode);
        } catch (Exception e) {
            throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
        }
        boolean googleCheck = isGoogleRight(customerId, gCode);
        if (!googleCheck) {
            throw new BaseException(ErrCode.GOOGLE_CHECK_FAILED);
        }

        return true;
    }

    @Override
    public void logout(LogoutReqVo logoutReqVo) {
        //Do nothing
    }

    /**
     * 校验谷歌验证码
     * @param customerId
     * @param googleCode
     * @return
     */
    public boolean isGoogleRight(Long customerId, long googleCode) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);

        if (null == customer || !customer.getGoogleSecretAuth()) {
            return false;
        }
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(customer.getGoogleSecret(), googleCode, t);
        return r;
    }

    /**
     * 通过用户名/邮箱/手机号码查找用户
     *
     * @param account
     * @return
     */
    private Customer getByAccount(String account) {
        boolean isEmail = CheckUtil.checkEmail(account);
        Customer customer = null;
        if (CheckUtil.checkEmail(account)) {
            //邮件方式登录
            customer = customerMapper.selectByEmail(account);
        } else if (CheckUtil.checkMobileNumber(account)) {
            //手机号码方式登录
            customer = customerMapper.selectByMobile(account);
        } else {
            //用户名方式登录
            customer = customerMapper.selectByUsername(account);
        }
        return customer;
    }

    /**
     * @param ip
     * @param customer
     * @return
     */
    private LoginRespVo afterLoginSuccess(String client,String ip,String imageId,String account, Customer customer) {
        Long curTime = System.currentTimeMillis();
        cacheService.removeErrorCount(account);

        CustomerVo customerVo = customerService.getCustomerVo(customer.getId());

        if(!SStringUtils.isEmpty(imageId)){
            ImageCodeUtils.removeCodeFromCache(redisUtil,imageId);
        }

        if(!customer.getEnable()){
            throw new BaseException(ErrCode.USER_ACCOUNT_FROZEN);
        }

        UserIdentity userIdentity = createToken(customerVo, account);
        String tokenKey = FormatUtil.formatPpwTokenKey(UserConstants.MOBILE_TOKEN_KEY, userIdentity.getId());
        LoginRespVo loginRespVo = new LoginRespVo();
        //判断用户是否谷歌认证
        if (customerVo.getGoogleSecretAuth() && customerVo.getGoogleAuthIsOpen()) {
            String str = String.valueOf(System.currentTimeMillis());
            String sign = MD5Util.digest(str);
            redisUtil.set(sign, userIdentity, SecurityConf.SIGN_TIMEOUT_SECOND);
            loginRespVo.setUsername("");
            loginRespVo.setMobile("");
            loginRespVo.setEmail("");
            loginRespVo.setUserId(null);
            loginRespVo.setToken(null);
            loginRespVo.setNeedGoogleAuth(true);
            loginRespVo.setSign(sign);
            loginRespVo.setGoogleAuthIsOpen(customer.getGoogleAuthIsOpen());
            loginRespVo.setGoogleSecretAuth(customer.getGoogleSecretAuth());
        } else {
            loginRespVo.setUsername(customer.getUsername());
            loginRespVo.setMobile( SStringUtils.replaceMobile(customerVo.getMobile()));
            loginRespVo.setEmail( SStringUtils.replaceEmail(customerVo.getEmail()));
            loginRespVo.setUserId(customerVo.getId());
            loginRespVo.setSign("");
            loginRespVo.setNeedGoogleAuth(false);
            loginRespVo.setGoogleAuthIsOpen( customerVo.getGoogleAuthIsOpen() == null ? false : customerVo.getGoogleAuthIsOpen());
            loginRespVo.setGoogleSecretAuth(customerVo.getGoogleSecretAuth() == null ? false : customerVo.getGoogleSecretAuth());
        }
        loginRespVo.setTokenGenerateTime(System.currentTimeMillis());
        loginRespVo.setWalletAddress(customerVo.getWalletAddress());

        //更新用户的登录信息以及追加登录记录
        Customer c = new Customer();
        c.setId(customer.getId());
        c.setLastLoginIp(ip);
        c.setLastLoginTime(curTime);
        customerMapper.updateByPrimaryKeySelective(c);
        return loginRespVo;
    }


    public UserIdentity createToken(CustomerVo customerVo, String account) {
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setId(customerVo.getId());
        return userIdentity;
    }

    /**
     * 登录失败后的处理逻辑
     *
     * @param account
     * @return
     */
    private void afterLoginFailed(String account) {
        // do nothing
    }

    /**
     * 添加登录记录
     * @param customerId
     * @param ip
     * @param token
     * @param curTime
     */
    public void addLoginInfo(Long customerId, String ip, String token,Long curTime) {
        String address = "-";
        CustomerLoginRecord customerLoginRecord = new CustomerLoginRecord();
        customerLoginRecord.setCustomerId(customerId);
        customerLoginRecord.setLoginAddress(address);
        customerLoginRecord.setLoginIp(ip);
        customerLoginRecord.setLoginTime(curTime);
        customerLoginRecordMapper.insertSelective(customerLoginRecord);
    }

    /**
     * 发送注册短信验证码
     * @param language 语言
     * @param account 手机/邮箱
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    @Override
    public void sendRegCode(String language, String account,String imageCode,String imageId,Long codeExpireSeconds, Long retryLimitSeconds){
        // 邮箱
        if (CheckUtil.checkEmail(account)) {
            //判断邮箱是否已经注册
            if (customerService.checkMailExist(account)) {
                throw new BaseException(ErrCode.REG_MAIL_IS_EXISTING);
            }
        } else if (CheckUtil.checkMobileNumber(account)) {
            //判断手机是否已经注册
            if (customerService.checkMobileExist(account)) {
                throw new BaseException(ErrCode.MOBILE_IS_EXISTING);
            }
        }else {
            throw new BaseException(ErrCode.ACCOUNT_VALUE_IS_ILLEGAL);
        }

        if(SStringUtils.isEmpty(imageCode)){
            sendCodeIgnoreImageCode(language,UserVeriCodeScenes.REGISTER,account,codeExpireSeconds,retryLimitSeconds);
        }else {
            sendCode(language,UserVeriCodeScenes.REGISTER,account,imageCode,imageId,codeExpireSeconds,retryLimitSeconds);
        }

    }

    /**
     * 找回密碼場景发送手机/邮箱验证码(需验证图片验证码)
     * @param language 语言
     * @param account 手机/邮箱
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    @Override
    public void sendForgetPwdCode(String language,String account,String imageCode,String imageId,Long codeExpireSeconds, Long retryLimitSeconds){

        //判断是否因验证码错误次数过多而受限
        limitHelper.assertForgetPwdOperationNoLimit(account);

        sendCode(language,UserVeriCodeScenes.FORGET_PWD,account,imageCode,imageId,codeExpireSeconds,retryLimitSeconds);
    }



    /**
     * 发送手机/邮箱验证码(需验证图片验证码)
     * @param language 语言
     * @param scenes 场景，如登录、注册等
     * @param account 手机/邮箱
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    @Override
    public void sendCode(String language, String scenes,String account,String imageCode,String imageId,Long codeExpireSeconds, Long retryLimitSeconds){
        if(SStringUtils.isAnyEmpty(imageCode,imageCode)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }


        if(!usrSrvConfig.isDebug() && !ImageCodeUtils.checkCode(redisUtil,imageId,imageCode)){
            throw new BaseException(ErrCode.IMAGE_CODE_CHECK_FAILED);
        }

        sendCodeIgnoreImageCode(language,scenes,account,codeExpireSeconds,retryLimitSeconds);
    }

    @Override
    public void sendCodeIgnoreImageCode(String language,String scenes,Long customerId, Integer sendType, Long codeExpireSeconds, Long retryLimitSeconds) {
        if(customerId == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        if(customer == null || sendType == null){
            throw new BaseException(ErrCode.SERVER_ERROR);
        }
        if (RegisterType.MOBILE.getValue() ==  sendType) {
            if (customer.getMobileAuth()) {
                sendCodeIgnoreImageCode(language,scenes,customer.getMobile(),codeExpireSeconds,retryLimitSeconds);
            } else {
                throw new BaseException(ErrCode.MOBILE_NOT_AUTH);
            }
        }
        if (RegisterType.EMAIL.getValue() ==  sendType) {
            if (customer.getMailAuth()) {
                sendCodeIgnoreImageCode(language,scenes,customer.getEmail(),codeExpireSeconds,retryLimitSeconds);
            } else {
                throw new BaseException(ErrCode.EMIAL_NOT_AUTH);
            }
        }
    }

    /**
     * 不校验图片验证码发送发送手机/邮箱验证码
     * @param language 语言
     * @param scenes 场景，如登录、注册等
     * @param account 手机/邮箱
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    @Override
    public void sendCodeIgnoreImageCode(String language,String scenes,String account,Long codeExpireSeconds, Long retryLimitSeconds){
        if(SStringUtils.isAnyEmpty(account)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(SStringUtils.isAnyEmpty(language)){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }
        if(codeExpireSeconds == null || retryLimitSeconds == null){
            throw new BaseException(ErrCode.PARAMS_ERR);
        }



        //发送验证码
        String code = activeCodeUtil.sendCode(language,scenes,account, codeExpireSeconds, retryLimitSeconds);

        if (Strings.isNullOrEmpty(code)) {
            throw new BaseException(ErrCode.SERVER_ERROR);
        }
    }



}
