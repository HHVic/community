package cn.huan.community.community.service;

import cn.huan.community.community.dao.CommentDao;
import cn.huan.community.community.domain.Comment;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.CommentDTO;
import cn.huan.community.community.dto.CommentParamDTO;
import cn.huan.community.community.enums.CommentTypeEnum;
import cn.huan.community.community.exception.CustomizeErrorCode;
import cn.huan.community.community.exception.CustomizeException;
import cn.huan.community.community.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private CommentDao commentDao;

    @Transactional
    public void comment(CommentParamDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(commentDTO.getCommentator());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        comment.setType(commentDTO.getType());
        if(commentDTO.getParentId() == null){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARENT_NOT_FOUND);
        }
        comment.setParentId(commentDTO.getParentId());
        if(commentDTO.getType() == null || !CommentTypeEnum.isExist(commentDTO.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if(commentDTO.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment commentSource = commentMapper.selectByPrimaryKey(commentDTO.getParentId());
            if(commentSource == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            commentDao.incrComment(commentSource.getId(),commentSource.getCommentCount());
        }

        else if(commentDTO.getType() == CommentTypeEnum.PROBLEM.getType()){
            //回复问题
            Problem problem = problemService.getById(commentDTO.getParentId());
            if(problem == null){
                throw new CustomizeException(CustomizeErrorCode.PROBLEM_NOT_FOUND);
            }
            commentMapper.insert(comment);
            problemService.incrComment(problem);
        }
    }

    public List<CommentDTO> listByParentId(int id,int type) {
        return commentDao.listByParentId(id,type);
    }
}
