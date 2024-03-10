package com.verificer.biz.biz.service.impl;

import com.verificer.biz.biz.entity.User;
import com.verificer.biz.biz.mapper.UserMapper;
import com.verificer.biz.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根跟Pos机端的会员id查询用户
     * @param posMemberId
     * @return
     */
    @Override
    public User selectByPosMemberId(Long posMemberId) {
        User user = userMapper.selectByPosMemberId(posMemberId);
        return user;
    }
}
