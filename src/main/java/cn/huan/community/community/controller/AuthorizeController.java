package cn.huan.community.community.controller;

import cn.huan.community.community.domain.User;
import cn.huan.community.community.dto.AccessToken;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.provider.GithubProvider;
import cn.huan.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;
    @Value("${Github.client.id}")
    private String client_id;
    @Value("${Github.client.secret}")
    private String client_secret;
    @Value("${Github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirect_uri);
        accessToken.setState(state);
        accessToken.setClient_id(client_id);
        accessToken.setClient_secret(client_secret);
        String token = githubProvider.getAccessToken(accessToken);
        System.out.println(token);
        GithubUser user = githubProvider.getUser(token);

        if(user != null){
            //登录成功
            /*User u = new User();
            u.setAccountId(user.getId().toString());
            u.setUserName(user.getLogin());
            u.setToken(UUID.randomUUID().toString());
            u.setGmtCreate(System.currentTimeMillis());
            u.setGmtModified(System.currentTimeMillis());*/
            if(userService.loginFromGithub(user) == 1){
                request.getSession().setAttribute("user",user);
            }
            return "redirect:";
        }else{
            return "redirect:";
        }
    }
}
