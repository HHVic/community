package cn.huan.community.community.service;

import cn.huan.community.community.domain.User;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public int loginFromGithub(User user){
        User u = userMapper.getById(Integer.valueOf(user.getId() + ""));
        if(u == null){
            //插入
            return userMapper.insert(user);
        }else{
            //更新token
            u.setAvatarUrl(user.getAvatarUrl());
            u.setGmtModified(user.getGmtModified());
            u.setToken(user.getToken());
            u.setUserName(user.getUserName());
            return userMapper.update(u);
        }
    }

    public User getByToken(String token) {
        return userMapper.getByToken(token);
    }
}
