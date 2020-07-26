package cn.huan.community.community.controller;

import cn.huan.community.community.cache.TagCache;
import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.service.ProblemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tagCache", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tags",required = false) String tags,
                            @RequestParam(value = "id",required = false) Integer id,
                            HttpServletRequest request,
                            Model model
    ) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        model.addAttribute("tagCache", TagCache.get());
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
        String invalid = TagCache.filterInvalid(tags);
        if(!StringUtils.isBlank(invalid)){
            model.addAttribute("error","输入标签不合法:" + invalid);
            return "publish";
        }
        Account account = (Account) request.getSession().getAttribute("user");
        if(account == null){
            model.addAttribute("error","您还没有登录，去<a href='https://github.com/login/oauth/authorize?client_id=689c94ad0ea8ba8267bb&redirect_uri=http://huan.cross.echosite.cn/callback&scope=user&state=1'>登录</a>");
            return "publish";
        }
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setDescription(description);
        problem.setTags(tags);
        problem.setCreator(Integer.valueOf(account.getId()));
        problem.setId(id);
        problemService.createOrUpdate(problem);
        return "redirect:";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id")int id,Model model,
                       HttpServletRequest request){
        Account account = (Account) request.getSession().getAttribute("user");
        Problem problem = problemService.getById(id);
        String title = problem.getTitle();
        String description = problem.getDescription();
        String tags = problem.getTags();
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        model.addAttribute("id",problem.getId());
        return "publish";
    }
}
