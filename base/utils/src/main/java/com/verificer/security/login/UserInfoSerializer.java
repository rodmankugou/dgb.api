package com.verificer.security.login;

import com.verificer.security.login.exception.UnSerializableException;

import java.io.Serializable;

/**
 * 为了节省存储空间，要求保存到缓存的数据实现自定义的序列化接口
 * Created by 35336 on 2020/12/29.
 */
public interface UserInfoSerializer<T> extends Serializable{
    public String serialize(T obj);
    public T unSerialize(String str) throws UnSerializableException;
}