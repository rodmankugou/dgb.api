package com.verificer.biz.biz.service.common;

import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommon {

    @Autowired
    UserMapper userMapper;

    public String getNickName(String userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null)
            throw new RuntimeException("User [id="+userId+"] not exist");
        return user.getNickname();
    }
}
