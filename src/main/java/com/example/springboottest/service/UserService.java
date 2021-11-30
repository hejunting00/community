package com.example.springboottest.service;

import com.example.springboottest.mapper.UserMapper;
import com.example.springboottest.model.User;
import com.example.springboottest.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
       if(users.size()==0)
       {
          //插入
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
       }else {
          //更新
           User dbUser = users.get(0);
           User updateUsuer = new User();
           updateUsuer.setGmtModified(System.currentTimeMillis());
           updateUsuer.setAvatarUrl(user.getAvatarUrl());
           updateUsuer.setName(user.getName());
           updateUsuer.setToken(user.getToken());
           UserExample example=new UserExample();
           example.createCriteria()
                   .andIdEqualTo(dbUser.getId());

           userMapper.updateByExampleSelective(updateUsuer,example);
       }
    }
}
