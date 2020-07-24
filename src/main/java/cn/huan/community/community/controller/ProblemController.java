package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.exception.CustomizeException;
import cn.huan.community.community.service.ProblemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/{id}")
    public String problem(@PathVariable("id")int id, Model model, HttpServletRequest request){
        ProblemDTO problemDTO = new ProblemDTO();
        Problem problem = problemService.getById(id);
        problemService.incrView(problem);
        if(problem == null){
            throw new CustomizeException(CustomizeErrorCode.PROBLEM_NOT_FOUND);
        }
        BeanUtils.copyProperties(problem,problemDTO);
        Account account = (Account) request.getSession().getAttribute("user");
        problemDTO.setAccount(account);
        model.addAttribute("problem",problemDTO);
        return "problem";
    }
}
