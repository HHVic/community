package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.dto.AccessToken;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.provider.GithubProvider;
import cn.huan.community.community.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private AccountService accountService;
    @Value("${Github.client.id}")
    private String client_id;
    @Value("${Github.client.secret}")
    private String client_secret;
    @Value("${Github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response
    ) {
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirect_uri);
        accessToken.setState(state);
        accessToken.setClient_id(client_id);
        accessToken.setClient_secret(client_secret);
        String token = githubProvider.getAccessToken(accessToken);
        System.out.println(token);
        GithubUser user = githubProvider.getUser(token);

        if (user != null) {
            //登录成功
//            Account account = new Account();
//            account.setAccountId(user.getId().toString());
//            account.setUserName(user.getLogin());
//            String token1 = UUID.randomUUID().toString();
//            account.setToken(token1);
//            account.setGmtCreate(System.currentTimeMillis());
//            account.setGmtModified(System.currentTimeMillis());
//            account.setAvatarUrl(user.getAvatarUrl());
            if(accountService.loginFromGithub(user, token) == 1){
                //写cookie
                response.addCookie(new Cookie("token", token));
                //request.getSession().setAttribute("user",user);
            }
        }
        return "redirect:";

    }
}
