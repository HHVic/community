package cn.huan.community.community.controller;

import cn.huan.community.community.dto.AccessToken;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${Github.client.id}")
    private String client_id;
    @Value("${Github.client.secret}")
    private String client_secret;
    @Value("${Github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirect_uri);
        accessToken.setState(state);
        accessToken.setClient_id(client_id);
        accessToken.setClient_secret(client_secret);
        String token = githubProvider.getAccessToken(accessToken);
        System.out.println(token);
        GithubUser user = githubProvider.getUser(token);
        System.out.println(user.getLogin());
        return "index";
    }
}
