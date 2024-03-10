package com.verificer.biz.biz.service;

import com.verificer.biz.biz.entity.User;

public interface UserService {

    User selectByPosMemberId(Long customerUid);
}
