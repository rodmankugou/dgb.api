package com.verificer.base_user.service;

import com.verificer.beans.LoginRespVo;
import com.verificer.beans.LogoutReqVo;
import com.verificer.beans.UserIdentity ;
import com.verificer.beans.rpc.RpcResponse;

/**
 * Created by 35336 on 2020/12/24.
 */
public interface LoginService   {
    /**
     * @param client 客户端，如app或web
     * @param ip        用户ip
     * @param userType  用户类型
     * @param account   用户账号（邮箱/手机）
     * @param password  用户密码
     * @param code      手机/邮件验证码，如果该参数为空，表示不需要短信验证码校验
     * @param imageCode 图片验证码，如果该参数为空，表示不需要图片验证码校验
     * @param imageId   图片验证码的图片id
     */
    public LoginRespVo loginByPassword(String client,String ip, Integer userType, String account, String password, String code, String imageId, String imageCode);

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
     * @param client 客户端，如app或web
     * @param ip        用户ip
     * @param userType  用户类型
     * @param account   用户账号（邮箱/手机）
     * @param code      手机/邮件验证码，如果该参数为空，表示不需要短信验证码校验
     * @param imageCode 图片验证码，如果该参数为空，表示不需要图片验证码校验
     * @param imageId   图片验证码的图片id
     */
    public LoginRespVo loginByCheckCode(String client,String ip, Integer userType, String account, String code, String imageId, String imageCode);


    /**
     * 谷歌验证器方式登录
     * @param client 客户端，如app或web
     * @param ip
     * @param userType  用户类型
     * @param googleCode
     * @param sign
     * @return
     */
    public LoginRespVo loginByGoogleAuth(String client,String ip, Integer userType, String googleCode, String sign);

    /**
     * 退出登录
     *
     * @param logoutReqVo
     */
    public void logout(LogoutReqVo logoutReqVo);

    /**
     * 发送注册验证码
     * @param language
     * @param account
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    public void sendRegCode(String language,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds);


    /**
     * 发送发送手机/邮箱验证码,需校验图片验证码
     * @param language 语言
     * @param scenes 场景，如登录、注册等
     * @param account 手机/邮箱
     * @param imageCode
     * @param imageId
     * @param codeExpireSeconds 验证码有效时间
     * @param retryLimitSeconds 验证码重发时间限制
     */
    public void sendCode(String language,String scenes,String account, String imageCode, String imageId, Long codeExpireSeconds, Long retryLimitSeconds);

    /**
     * 找回密碼場景发送手机/邮箱验证码,需校验图片验证码
     * @param language 语言
     * @param account  手机/邮箱
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
    void sendCodeIgnoreImageCode(String language,String scenes,String account,Long codeExpireSeconds, Long retryLimitSeconds);

    /**
     * 不校验图片验证码发送发送手机/邮箱验证码
     * @param language 语言
     * @param scenes 场景，如登录、注册等
     * @param customerId 用户id
     * @param sendType sendType
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

}
