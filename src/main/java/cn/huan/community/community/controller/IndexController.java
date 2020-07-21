package cn.huan.community.community.controller;

import cn.huan.community.community.domain.User;
import cn.huan.community.community.dto.PageProblemDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.service.ProblemService;
import cn.huan.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProblemService problemService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "page",defaultValue = "1")int page,
                        @RequestParam(value = "size",defaultValue = "3")int size){
        //判断用户是否存在
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user = userService.getByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        //获取文章列表
        //List<ProblemDTO> problems = problemService.list();
        PageProblemDTO pages = problemService.listPage(page,size);
        model.addAttribute("pages",pages);
        return "index";
    }
}
