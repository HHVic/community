package cn.huan.community.community;


import cn.huan.community.community.domain.Account;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.mapper.AccountMapper;
import cn.huan.community.community.mapper.ProblemMapper;
import cn.huan.community.community.service.AccountService;
import cn.huan.community.community.service.ProblemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@SpringBootTest(classes={CommunityApplication.class, AccountMapper.class, ProblemMapper.class, ProblemService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {

    @Autowired
    private AccountService userService;
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private AccountMapper userMapper;
    @Autowired
    private ProblemService problemService;
    @Test
    public void test(){
//        GithubUser githubUser = new GithubUser();
//        githubUser.setId(44734532L);
//        githubUser.setLogin("HHVic");
//
//        Account u = new Account();
//        u.setAccountId(githubUser.getId().toString());
//        u.setUserName(githubUser.getLogin());
//        String token1 = UUID.randomUUID().toString();
//        u.setToken(token1);
//        u.setGmtCreate(System.currentTimeMillis());
//        u.setGmtModified(System.currentTimeMillis());
//        System.out.println(userService.loginFromGithub(u));
    }
    @Test
    public void testToken(){
//        String token = "5ee3e5a5-f3c3-443f-ae5e-9baa949c18a1";
//        Account user = userService.getByToken(token);
//        System.out.println(user);
    }

    @Test
    public void testAccount(){
//        int accountId = 44734532;
//        Account user = userMapper.selectByPrimaryKey(accountId);
//        System.out.println(user);
    }
    @Test
    public void listProblem(){
//        List<ProblemDTO> list = problemService.list();
//        System.out.println(list);
    }
    @Test
    public void testAddProblem(){
//        Problem problem = new Problem("1","1",1l,1l,1,"1");
//        problemMapper.add(problem);
    }
}
