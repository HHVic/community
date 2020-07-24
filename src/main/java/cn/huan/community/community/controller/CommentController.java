package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.dto.CommentDTO;
import cn.huan.community.community.dto.ResultDTO;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        Account user = (Account) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.error(CustomizeErrorCode.CUSTOM_NOT_FOUND);
        }
        commentDTO.setCommentator(user.getId());
        commentService.comment(commentDTO);
        return ResultDTO.SUCCESS;
    }
}
