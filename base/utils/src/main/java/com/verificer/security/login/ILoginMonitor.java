package com.verificer.security.login;

/**
 * Created by liujinhua on 2020/12/29.
 */


public interface ILoginMonitor<T> {
    /**
     * 登录
     * @param client 终端，如app或者web
     * @param userId 用户id
     * @param serializer userInfo的序列化器
     * @return
     */
    String login(String client,String userId,T userInfo,UserInfoSerializer<T> serializer);

    /**
     * 更新Session
     * @param token token
     * @param serializer userInfo的序列化器
     * @return
     */
    void updateSession(String token,String client,T userInfo,UserInfoSerializer<T> serializer);


    /**
     * 退出登录
     * @param token
     */
    void logout(String token);

    /**
     * 强制退出该账号的所有端的所有登录
     * @return
     */
    void forceAccountLogout(String userId);

    /**
     * 强制退出该账号的所有端的所有登录
     * @return
     */
    void forceAccountLogout(String userId,int logoutType);

    /**
     * 判断该token是否还处于有效登录状态
     * @param clientId
     * @param token
     * @return 如果未登录，则返回空
     */
    LoginStat<T> isLogin(String clientId,String token,UserInfoSerializer<T> serializer);

    /**
     * 更新token,如果该oldToken有效，则返回新token，否则返回null
     * @param oldToken 旧token
     * @return 新的token
     */
    String replaceToken(String client,String oldToken,UserInfoSerializer<T> serializer);

    /**
     * 添加子token，如果已经存在，则不添加；该subToken的有效时间将会宿主token一样
     * @param token
     * @param subTokenName
     * @return
     */
    String addSubTokenIfNotExist(String client,String token, String subTokenName,UserInfoSerializer<T> serializer);

    /**
     * 添加子token，如果已经存在，则不添加
     * @param token
     * @param subTokenName
     * @param keepTime 存活时间,0表示该subToken的有效时间将会宿主token一样
     * @return
     */
    String addSubTokenIfNotExist(String client,String token, String subTokenName,Long keepTime,UserInfoSerializer<T> serializer);

    /**
     * 根据subToken获取用户信息
     * @param subToken
     * @return
     */
    T getUserInfoBySubToken(String subToken,UserInfoSerializer<T> serializer);

    /**
     * 获取subToken
     * @param client
     * @param token
     * @param subTokenName
     * @param serializer
     * @return
     */
    String getSubToken(String client, String token,String subTokenName,UserInfoSerializer<T> serializer);

}
