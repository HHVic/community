package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.dto.NotificationDTO;
import cn.huan.community.community.dto.PagenationDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.service.NotificationService;
import cn.huan.community.community.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private NotificationService notificationService;
    @GetMapping("/{action}")
    public String profile(@PathVariable("action")String action, Model model, HttpServletRequest request,
                          @RequestParam(value = "page",defaultValue = "1")int page,
                          @RequestParam(value = "size",defaultValue = "5")int size){
        Account account = (Account) request.getSession().getAttribute("user");
        if(account == null){
            //model.addAttribute("error","您还没有登录，去<a href='https://github.com/login/oauth/authorize?client_id=689c94ad0ea8ba8267bb&redirect_uri=http://huan.cross.echosite.cn/callback&scope=user&state=1'>登录</a>");
            return "redirect:";
        }

        if("problems".equals(action)){
            PagenationDTO<ProblemDTO> pages = problemService.listPageByUser(page,size,account.getId());
            model.addAttribute("pages",pages);
            model.addAttribute("section","problems");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            PagenationDTO<NotificationDTO> pages = notificationService.listPageByUser(page,size,account.getId());
            model.addAttribute("pages",pages);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
