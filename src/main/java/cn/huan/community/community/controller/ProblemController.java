package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.CommentDTO;
import cn.huan.community.community.dto.ProblemDTO;
import cn.huan.community.community.enums.CommentTypeEnum;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.exception.CustomizeException;
import cn.huan.community.community.service.CommentService;
import cn.huan.community.community.service.ProblemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public String problem(@PathVariable("id")int id, Model model, HttpServletRequest request){
        ProblemDTO problemDTO = problemService.getByIdWithUser(id);
        List<CommentDTO> commentDTOS = commentService.listByParentId(id, CommentTypeEnum.PROBLEM.getType());
        List<Problem> relations = problemService.listByTagsRelations(problemDTO);
        model.addAttribute("problem",problemDTO);
        model.addAttribute("comments",commentDTOS);
        model.addAttribute("relations",relations);
        return "problem";
    }
}
