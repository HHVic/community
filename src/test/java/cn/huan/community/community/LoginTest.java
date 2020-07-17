package cn.huan.community.community;


import cn.huan.community.community.domain.User;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.mapper.UserMapper;
import cn.huan.community.community.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes={CommunityApplication.class, UserMapper.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {

    @Autowired
    private UserService userService;
    @Test
    public void test(){
        GithubUser githubUser = new GithubUser();
        githubUser.setId(44734532L);
        githubUser.setLogin("HHVic");
        System.out.println(userService.loginFromGithub(githubUser));
    }
}
