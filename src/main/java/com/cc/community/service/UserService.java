package com.cc.community.service;

import com.cc.community.dto.GithubUser;
import com.cc.community.mapper.UserMapper;
import com.cc.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by xiaomi on 2019/12/4.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(GithubUser githubUser, String token) {
        User user=userMapper.findByAccountId(githubUser.getId());
        if (user==null){
            //注册
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setGmtModified(user.getGmtCreate());
            userMapper.update(user);
        }
    }
}
