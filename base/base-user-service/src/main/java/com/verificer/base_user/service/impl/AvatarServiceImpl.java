package com.verificer.base_user.service.impl;

import com.verificer.ErrCode;
import com.verificer.base_user.entity.Avatar;
import com.verificer.base_user.mapper.AvatarMapper;
import com.verificer.base_user.service.AvatarService;
import com.verificer.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AvatarServiceImpl implements AvatarService
{
    @Autowired
    AvatarMapper avatarMapper;


    @Override
    public String getRandomAvatar() {
        List<Avatar> avatars = avatarMapper.selectAll();
        if(avatars == null || avatars.size() == 0)
            throw new BaseException(ErrCode.SERVER_ERROR);

        Random random = new Random();

        int ran = random.nextInt(avatars.size());
        return avatars.get(ran).getUrl();
    }
}
