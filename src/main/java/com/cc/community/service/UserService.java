package com.cc.community.service;

import com.cc.community.dto.GithubUser;
import com.cc.community.mapper.UserMapper;
import com.cc.community.model.User;
import com.cc.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by xiaomi on 2019/12/4.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(GithubUser githubUser, String token) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(String.valueOf(githubUser.getId()));
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            //注册
            User user=new User();
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
            User updateUser=new User();
            updateUser.setBio(githubUser.getBio());
            updateUser.setAvatarUrl(githubUser.getAvatar_url());
            updateUser.setToken(token);
            updateUser.setName(githubUser.getName());
            updateUser.setGmtModified(System.currentTimeMillis());
            UserExample example=new UserExample();
            example.createCriteria().andIdEqualTo(users.get(0).getId());
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }
}
