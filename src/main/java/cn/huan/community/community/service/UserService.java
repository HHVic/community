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
    public int loginFromGithub(GithubUser githubUser){
        User u = new User();
        u.setAccountId(githubUser.getId().toString());
        u.setUserName(githubUser.getLogin());
        u.setToken(UUID.randomUUID().toString());
        u.setGmtCreate(System.currentTimeMillis());
        u.setGmtModified(System.currentTimeMillis());
        return userMapper.insert(u);
    }
}
