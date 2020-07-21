package cn.huan.community.community;


import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.domain.User;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.mapper.ProblemMapper;
import cn.huan.community.community.mapper.UserMapper;
import cn.huan.community.community.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@SpringBootTest(classes={CommunityApplication.class, UserMapper.class, ProblemMapper.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProblemMapper problemMapper;
    @Test
    public void test(){
        GithubUser githubUser = new GithubUser();
        githubUser.setId(44734532L);
        githubUser.setLogin("HHVic");

        User u = new User();
        u.setAccountId(githubUser.getId().toString());
        u.setUserName(githubUser.getLogin());
        String token1 = UUID.randomUUID().toString();
        u.setToken(token1);
        u.setGmtCreate(System.currentTimeMillis());
        u.setGmtModified(System.currentTimeMillis());
        System.out.println(userService.loginFromGithub(u));
    }
    @Test
    public void testToken(){
        String token = "bbbf543a-6938-4e43-99c5-cde7b9c29cd4";
        User user = userService.getByToken(token);
        System.out.println(user);
    }
    @Test
    public void testAddProblem(){
        Problem problem = new Problem("1","1",1l,1l,1,"1");
        problemMapper.add(problem);
    }
}
