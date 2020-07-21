package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.domain.User;
import cn.huan.community.community.service.ProblemService;
import cn.huan.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tags") String tags,
                            HttpServletRequest request,
                            Model model
    ) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        if(title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tags == null || tags == ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        Cookie[] cookies = request.getCookies();
        User user = null;
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())){
                user = userService.getByToken(cookie.getValue());
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user == null){
            model.addAttribute("error","您还没有登录，去<a href='https://github.com/login/oauth/authorize?client_id=689c94ad0ea8ba8267bb&redirect_uri=http://huan.cross.echosite.cn/callback&scope=user&state=1'>登录</a>");
            return "publish";
        }
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setTags(tags);
        problem.setCreator(Integer.valueOf(user.getAccountId()));
        problem.setGmtCreate(System.currentTimeMillis());
        problem.setGmtModified(System.currentTimeMillis());
        problemService.add(problem);
        return "redirect:";
    }
}
