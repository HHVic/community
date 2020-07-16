package cn.huan.community.community.controller;

import cn.huan.community.community.dto.AccessToken;
import cn.huan.community.community.dto.GithubUser;
import cn.huan.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessToken accessToken = new AccessToken();
        String client_id = "689c94ad0ea8ba8267bb";
        String client_secret = "4079a093ad86a54ceca9e6dd0820a620bfad5641";
        accessToken.setCode(code);
        String redirect_uri = "http://huan.cross.echosite.cn/callback";
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
