package cn.huan.community.community.controller;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.dto.CommentDTO;
import cn.huan.community.community.dto.CommentParamDTO;
import cn.huan.community.community.dto.ResultDTO;
import cn.huan.community.community.enums.CommentTypeEnum;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResultDTO post(@RequestBody CommentParamDTO commentDTO, HttpServletRequest request){
        Account user = (Account) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.error(CustomizeErrorCode.CUSTOM_NOT_FOUND);
        }
        if(commentDTO.getContent() == null || "".equals(commentDTO.getContent())){
            return ResultDTO.error(CustomizeErrorCode.COMMENT_COMTENT_EMPTY);
        }
        commentDTO.setCommentator(user.getId());
        commentService.comment(commentDTO);
        return ResultDTO.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO showSubComments(@PathVariable("id")Integer id, HttpServletRequest request){
        if(id == null){
            return ResultDTO.error(CustomizeErrorCode.PROBLEM_NOT_FOUND);
        }
        List<CommentDTO> commentDTOS = commentService.listByParentId(id, CommentTypeEnum.COMMENT.getType());
        return ResultDTO.ok(commentDTOS);
    }
}
