package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.dto.PagenationDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private ProblemService problemService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "page",defaultValue = "1")int page,
                        @RequestParam(value = "size",defaultValue = "3")int size){
        Account user = (Account) request.getSession().getAttribute("user");
        //获取文章列表
        //List<ProblemDTO> problems = problemService.list();
        PagenationDTO<ProblemDTO> pages = problemService.listPage(page,size);
        model.addAttribute("pages",pages);
        return "index";
    }
}
