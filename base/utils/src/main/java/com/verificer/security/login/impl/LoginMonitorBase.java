package com.verificer.security.login.impl;

import com.verificer.security.login.*;
import com.verificer.security.login.exception.UnSerializableException;
import com.verificer.utils.*;
import com.verificer.utils.web.BlowfishUtils;
import com.verificer.utils.web.SecurityConf;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 35336 on 2020/12/29.
 */
public abstract class LoginMonitorBase<T> implements ILoginMonitor<T> {
    private static final Log logger = LogFactory.getLog(LoginMonitorBase.class);
    private static final String REPLACED_TOKEN_PREFIX = "OLD:";
    private static final String SUB_TOKEN_MD5_SALT = "jvaljg34#4Fjdafkla82348482=du8fdanjknfajknfauf23r8nvcsdg";
    protected LoginMode mode;
    TokenGenerator tokenGenerator = new TokenGenerator();


    ILoginCache cache;


    public void setCache(ILoginCache cache) {
        this.cache = cache;
    }

    static class SubTokenWrapper{
        String name;
        String token;
        Long keepTime;
        static String serialize(SubTokenWrapper subTokenWrapper){
            if(subTokenWrapper.name.indexOf('|') != -1
                    || subTokenWrapper.name.indexOf(':') != -1
                    || subTokenWrapper.name.indexOf(",") != -1)
                throw new IllegalStateException("subTokenName cannot not contain char '|' or ':' or ','");
            if(subTokenWrapper.token.indexOf('|') != -1
                    || subTokenWrapper.token.indexOf(':') != -1
                    || subTokenWrapper.token.indexOf(",") != -1)
                throw new IllegalStateException("subTokenValue cannot not contain char '|' or ':' or ','");
            return new StringBuffer().append(subTokenWrapper.name).append("@").
                    append(subTokenWrapper.token).append("@")
                    .append(subTokenWrapper.keepTime)
                    .toString();
        }

        static SubTokenWrapper unSerialize(String str){
            List<String> fields = SStringUtils.split(str,"@");
            SubTokenWrapper tokenWrapper = new SubTokenWrapper();
            tokenWrapper.name = fields.get(0);
            tokenWrapper.token = fields.get(1);
            tokenWrapper.keepTime = Long.parseLong(fields.get(2));
            return tokenWrapper;
        }
    }

    /**
     * 加上id字段，用于读取出来后的后续操作
     */
    class UserInfoWrapper {
        String token;
        String userId;
        Map<String,SubTokenWrapper> subTokenMap = new HashMap<>();
        T value;


        /**
         * 序列化，json方式连类型一起序列化太占用空间
         * @param serializer
         * @return
         */
        public String serialize(UserInfoSerializer<T> serializer) {
            if(token.indexOf('|') != -1){
                throw new IllegalStateException("token cannot not contain char '|'");
            }
            if(userId.indexOf('|') != -1){
                throw new IllegalStateException("userId cannot not contain char '|'");
            }


            StringBuffer subTokenMapBuf = new StringBuffer().append("");
            for(String key : subTokenMap.keySet()){
                String subToken = SubTokenWrapper.serialize(subTokenMap.get(key));
                if(subToken.indexOf('|') != -1
                        || subToken.indexOf(':') != -1
                        || subToken.indexOf(",") != -1)
                    throw new IllegalStateException("subToken cannot not contain char '|' or ':' or ','");
                subTokenMapBuf.append(subToken).append(",");
            }
            String subTokenMapStr = subTokenMapBuf.toString();
            subTokenMapStr = subTokenMapStr.endsWith(",") ? subTokenMapStr.substring(0,subTokenMapStr.length()-1) : subTokenMapStr;

            StringBuffer sb = new StringBuffer();
            sb.append(userId).append("|")
                    .append(token).append("|")
                    .append(subTokenMapStr).append("|")
                    .append(serializer.serialize(value));
            return BlowfishUtils.encrypt(sb.toString(), SecurityConf.SECRET_KEY);
        }

        /**
         * 反序列化
         * @param str
         * @param serializer
         * @throws UnSerializableException
         */
        public void unSerialize(String str,UserInfoSerializer<T> serializer) throws UnSerializableException {
            String text = BlowfishUtils.decrypt(str, SecurityConf.SECRET_KEY);
            String[] ss = text.split("\\|",4);

            Map<String,SubTokenWrapper> subTokenMap = new HashMap<>();
            List<String> subTokenList = SStringUtils.split(ss[2],",");
            if(subTokenList != null)
                for(String subTokenStr : subTokenList){
                    SubTokenWrapper subToken = SubTokenWrapper.unSerialize(subTokenStr);
                    subTokenMap.put(subToken.name,subToken);
                }

            this.userId = ss[0];
            this.token = ss[1];
            this.subTokenMap = subTokenMap;
            this.value = serializer.unSerialize(ss[3]);
        }
    }

    static class LoginStatus{
        private String lastLoginToken;
        private Integer logoutType; //如果该值为空，则表示未退出登陆，否则未已退出登陆

        public String getLastLoginToken() {
            return lastLoginToken;
        }

        public void setLastLoginToken(String lastLoginToken) {
            this.lastLoginToken = lastLoginToken;
        }

        public Integer getLogoutType() {
            return logoutType;
        }

        public void setLogoutType(Integer logoutType) {
            this.logoutType = logoutType;
        }
    }


    /**
     * token生成器，该生成器带了时间戳，对于生成不就的token，不用加redis的到期时间
     */
    static class TokenGenerator {
        String generateToken(){
            StringBuffer buffer = new StringBuffer();
            buffer.append(UuidUtils.newUuid());
            buffer.append(GenerateUtil.generateStrRecaptcha(32));
            buffer.append("-").append(System.currentTimeMillis());
            return buffer.toString();
        }

        static Long getGenerateTime(String token){
            int index = token.lastIndexOf('-');
            if(index != -1){
                try {
                    return Long.parseLong(token.substring(index+1));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }
    }


    String genUserLoginStatKey(String userId){
        return getAppName()+":loginMonitor:user:stat:"+userId;
    }

    String genUserClientLoginStatKey(String userId,String client){
        return genUserLoginStatKey(userId) + ":" +client;
    }


    /**
     * 获取登录保持时间，单位为秒
     * @return
     */
    abstract long getLoginKeepTime();

    /**
     * 获取token的有效时间，单位为秒
     * @return
     */
    abstract long getTokenAliveTime();

    /**
     * 是否开启强制登录功能，如果不开启，将减少redis的读写访问次数
     * @return
     */
    abstract boolean isEnableForceLogOut();

    /**
     * 应用名称，不同应用名称之间的token是互相隔离的
     * @return
     */
    abstract String getAppName();


    @Override
    public String login(String client, String userId, T userInfo,UserInfoSerializer<T> serializer) {
        String originToken = tokenGenerator.generateToken();

        String token =  MD5Util.digest(getAppName() +originToken);

        //保存一次登录的token
        UserInfoWrapper wrapper = new UserInfoWrapper();
        wrapper.value = userInfo;
        wrapper.userId = userId;
        wrapper.token = originToken;
        cache.put(token,wrapper.serialize(serializer), getLoginKeepTime());

        afterLoginSuc(client,userId,token);
        return originToken;
    }

    @Override
    public void updateSession(String token,String client, T userInfo,UserInfoSerializer<T> serializer) {
        String originToken = token;
        token =  MD5Util.digest(getAppName() +token);

        UserInfoWrapper wrap = getWrapperByToken(originToken,serializer);
        //保存一次登录的token
        wrap.value = userInfo;

        cache.put(token,wrap.serialize(serializer), getLoginKeepTime());

        afterLoginSuc(client,wrap.userId,token);
    }

    /**
     * 更新token后需要维护的其它token
     * @param client
     * @param userId
     * @param md5Token
     */
    private void afterLoginSuc(String client, String userId, String md5Token){
        //该key用于账户用于强制退出某账户的所有登录
        if(isEnableForceLogOut() || LoginMode.ACCOUNT_SINGLE.equals(mode)){
            LoginStatus loginStatus = new LoginStatus();
            loginStatus.lastLoginToken = md5Token;
            cache.put(genUserLoginStatKey(userId),FastJson.toJson(loginStatus), getLoginKeepTime());
        }

        //如果是CLIENT_SINGLE模式，则需要写入多一个key，用于判断账号是否有在其它对等客户端登录
        if(LoginMode.CLIENT_SINGLE.equals(mode)){
            cache.put(genUserClientLoginStatKey(userId,client),md5Token, getLoginKeepTime());
        }
    }

    @Override
    public void logout(String token) {
        if(token == null){
            throw new IllegalArgumentException("parameter token can not be null!");
        }
        cache.remove(MD5Util.digest(getAppName()+token));
    }

    @Override
    public void forceAccountLogout(String userId) {
        forceAccountLogout(userId,LogoutType.SIMPLE);
    }

    @Override
    public void forceAccountLogout(String userId,int logoutType) {
        if(!isEnableForceLogOut()){
            throw new IllegalStateException("Forced logout is configured to be closed,please check the boolean isEnableForceLogout() method in sub class");
        }

        String lsStr = cache.get(genUserLoginStatKey(userId));
        if(SStringUtils.isEmpty(lsStr))
            return;


        LoginStatus loginStatus = FastJson.fromJson(lsStr,LoginStatus.class);
        loginStatus.logoutType = logoutType;

        cache.put(genUserLoginStatKey(userId),FastJson.toJson(loginStatus), getLoginKeepTime());
    }

    @Override
    public LoginStat<T> isLogin(String client, String token, UserInfoSerializer<T> serializer) {
        if(SStringUtils.isEmpty(token)){
            return new LoginStat<T>(LogoutType.SIMPLE);
        }

        String originToken = token;

        //判断token是否超时
        Long tokenCreateTime = TokenGenerator.getGenerateTime(token);
        if(tokenCreateTime == null
                || tokenCreateTime < (System.currentTimeMillis() - getTokenAliveTime() * 1000)
                ){
            return new LoginStat<T>(LogoutType.TOKEN_TIME_OUT);
        }

        UserInfoWrapper wrapper = getWrapperByToken(originToken,serializer);
        token = MD5Util.digest(getAppName()+token);
        if(wrapper == null){
            //token已经过期或者不存在
            return new LoginStat<T>(LogoutType.SIMPLE);
        }

        if(!originToken.equals(wrapper.token)){
            //出现了token不同但md5后相同的情况
            return new LoginStat<T>(LogoutType.MD5_EQ_EXCPTION);
        }

        String userId = wrapper.userId;
        String lastAccountToken = null;
        //如果关闭了forceLogout功能或非ACCOUNT_SINGLE模式，则不需要考虑该key
        if(isEnableForceLogOut() || LoginMode.ACCOUNT_SINGLE.equals(mode)){
            String lsStr =  cache.get(genUserLoginStatKey(userId));

            if(lsStr == null){
                //账户可能被强制退出
                cache.remove(token);
                return new LoginStat<T>(LogoutType.SIMPLE);
            }

            LoginStatus loginStatus = FastJson.fromJson(lsStr,LoginStatus.class);
            if(loginStatus.logoutType != null)
                return new LoginStat<T>(loginStatus.logoutType);

            lastAccountToken = loginStatus.lastLoginToken;
        }


        if(LoginMode.NO_LIMIT.equals(mode)){
            onUserActive(userId,client,token,serializer);
            return new LoginStat<T>((T) wrapper.value);
        }else {
            String lastToken = null;
            //对比上次登录的token，判断当前登录是否被其它设备上的登录挤下线
            if(LoginMode.ACCOUNT_SINGLE.equals(mode)) {
                //单账户唯一登录模式
                lastToken = lastAccountToken;
            }else if(LoginMode.CLIENT_SINGLE.equals(mode)){
                String lastClientToken = (String) cache.get(genUserClientLoginStatKey(userId,client));
                if(lastClientToken == null){
                    cache.remove(token);
                    return new LoginStat<T>(LogoutType.SIMPLE);
                }
                lastToken = lastClientToken;
            }else {
                throw new IllegalStateException("mode " + mode.getDesc()+"is no support by LoginModeBase.class");
            }

            if(token.equals(lastToken)){
                onUserActive(userId,client,token,serializer);
                return new LoginStat<T>((T) wrapper.value);
            }else {
                //该账号最后一次登录的token与该token不相符，该账号有另外的登录行为
                cache.remove(token);
                return new LoginStat<T>(LogoutType.OTHER_LOGIN);
            }
        }
    }


    private UserInfoWrapper getWrapperByToken(String token,UserInfoSerializer serializer){
        token = MD5Util.digest(getAppName()+token);

        return getWrapperByMd5Token(token,serializer);
    }

    private UserInfoWrapper getWrapperByMd5Token(String token,UserInfoSerializer serializer){
        //根据token从redis中加载数据并反序列化
        String text =  cache.get(token);
        UserInfoWrapper wrapper = null;
        if(!SStringUtils.isEmpty(text)){
            wrapper = new UserInfoWrapper();
            try {
                wrapper.unSerialize(text,serializer);
            } catch (UnSerializableException e) {
                wrapper = null;
                logger.error("用户登录后保存的信息反序列化失败："+e.getMessage(),e);
            }
        }

        return wrapper;
    }

    /**
     * 用户访问api时调用，用于保持会话状态
     * @param userId
     * @param client
     * @param token
     */
    private void onUserActive(String userId,String client,String token,UserInfoSerializer<T> serializer){
        if(token == null){
            throw new IllegalArgumentException("parameter token can not be null");
        }

        Long tokenTime = tokenGenerator.getGenerateTime(token);
        if(tokenTime != null){
            //对于生成时间不久的token，不进行makeMoreTime操作
            if(System.currentTimeMillis() - (getLoginKeepTime()*1000/2) > tokenTime){
                if(isEnableForceLogOut() || LoginMode.ACCOUNT_SINGLE.equals(mode)){
                    cache.makeMoreTime(genUserLoginStatKey(userId), getLoginKeepTime());
                }

                if(LoginMode.CLIENT_SINGLE.equals(mode)){
                    cache.makeMoreTime(genUserClientLoginStatKey(userId,client),getLoginKeepTime());
                }
                cache.makeMoreTime(MD5Util.digest(getAppName()+token), getLoginKeepTime());

                UserInfoWrapper wrapper = getWrapperByToken(token,serializer);
                for(String key : wrapper.subTokenMap.keySet()){
                    SubTokenWrapper subToken = wrapper.subTokenMap.get(key);
                    if(subToken.keepTime == 0)
                        cache.makeMoreTime(subToken.token,getLoginKeepTime());

                }
            }
        }
    }

    /**
     * 更新token,如果该oldToken有效，则返回新token，否则返回null
     * @param oldToken 旧token
     * @return 新的token
     */
    @Override
    public String replaceToken(String client,String oldToken,UserInfoSerializer<T> serializer){
        LoginStat<T> stat = isLogin(client,oldToken,serializer);
        if(stat.getUserInfo() != null){
            UserInfoWrapper wrapper = getWrapperByToken(oldToken,serializer);
            if(wrapper != null){
                String newToken = new TokenGenerator().generateToken();
                wrapper.token = newToken;
                cache.put(MD5Util.digest(getAppName()+newToken),wrapper.serialize(serializer),getLoginKeepTime());
                afterLoginSuc(client,wrapper.userId,MD5Util.digest(getAppName()+newToken));
                return newToken;
            }
        }
        cache.remove(MD5Util.digest(getAppName()+oldToken));

        return null;
    }

    /**
     * 添加子token，如果已经存在，则不添加；该token的有效时间将会宿主token一样
     * @param token
     * @param subTokenName
     * @return
     */
    public String addSubTokenIfNotExist(String client,String token, String subTokenName,UserInfoSerializer<T> serializer){
          return this.addSubTokenIfNotExist(client,token,subTokenName,Long.MAX_VALUE/1000,serializer);
    }

    /**
     * 添加子token，如果已经存在，则不添加；该token的有效时间将会宿主token一样
     * @param client
     * @param token
     * @param subTokenName
     * @param serializer
     * @param keepTime 存活时间,单位为秒
     * @return
     */
    public String addSubTokenIfNotExist(String client,String token, String subTokenName,Long keepTime,UserInfoSerializer<T> serializer){
        if(SStringUtils.isEmpty(token))
            throw new IllegalArgumentException("parameter token can not be null");
        if(SStringUtils.isEmpty(subTokenName))
            throw new IllegalArgumentException("parameter subTokenName can not be null");

        LoginStat<T> loginStatus  = isLogin(client,token,serializer);
        if(!loginStatus.isLogin())
            throw new RuntimeException("token is invalid!");

        SubTokenWrapper subToken = null;
        Long actualKeepTime = (keepTime != 0L && keepTime < getLoginKeepTime()) ? keepTime : getLoginKeepTime();
        UserInfoWrapper userInfoWrapper = getWrapperByToken(token,serializer);
        Map<String,SubTokenWrapper> subTokenWrapperMap = userInfoWrapper.subTokenMap;
        if(subTokenWrapperMap.containsKey(subTokenName)){
            subToken = subTokenWrapperMap.get(subTokenName);
            subToken.keepTime = System.currentTimeMillis() + keepTime * 1000;
            if(cache.get(subToken.token) != null){
                cache.makeMoreTime(subToken.token,actualKeepTime);
            }else {
                cache.put(subToken.token,MD5Util.digest(getAppName()+token),actualKeepTime);
            }
        }else {
            subToken = new SubTokenWrapper();
            subToken.name = subTokenName;
            subToken.token = MD5Util.digest(UuidUtils.newUuid()+ SUB_TOKEN_MD5_SALT);
            subToken.keepTime = keepTime;
            cache.put(subToken.token,MD5Util.digest(getAppName()+token),actualKeepTime);
            subTokenWrapperMap.put(subTokenName,subToken);
        }

        cache.put(MD5Util.digest(getAppName()+token),userInfoWrapper.serialize(serializer),actualKeepTime);

        return subToken.token;
    }

    public T getUserInfoBySubToken(String subToken, UserInfoSerializer<T> serializer){
        String md5Token = cache.get(subToken);
        if(md5Token == null)
            return null;

        UserInfoWrapper userInfoWrapper = getWrapperByMd5Token(md5Token,serializer);
        if(userInfoWrapper == null)
            return null;
        return userInfoWrapper.value;
    }


    public String getSubToken(String client, String token,String subTokenName,UserInfoSerializer<T> serializer){
        UserInfoWrapper userInfoWrapper = getWrapperByToken(token,serializer);
        if(userInfoWrapper == null)
            return null;

        if(userInfoWrapper.subTokenMap.containsKey(subTokenName))
            return userInfoWrapper.subTokenMap.get(subTokenName).token;

        return null;
    }

}
