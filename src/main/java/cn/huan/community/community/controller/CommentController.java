package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.dto.CommentParamDTO;
import cn.huan.community.community.dto.ResultDTO;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentParamDTO commentDTO, HttpServletRequest request){
        Account user = (Account) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.error(CustomizeErrorCode.CUSTOM_NOT_FOUND);
        }
        if(commentDTO.getContent() == null || "".equals(commentDTO.getContent())){
            return ResultDTO.error(CustomizeErrorCode.COMMENT_COMTENT_EMPTY);
        }
        commentDTO.setCommentator(user.getId());
        commentService.comment(commentDTO);
        return ResultDTO.SUCCESS;
    }
}
