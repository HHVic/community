package cn.huan.community.community.service;

import cn.huan.community.community.dao.CommentDao;
import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.Comment;
import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.CommentDTO;
import cn.huan.community.community.dto.CommentParamDTO;
import cn.huan.community.community.enums.CommentTypeEnum;
import cn.huan.community.community.enums.NotificationTypeEnum;
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

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public void comment(CommentParamDTO commentParamDTO, Account account) {
        Comment comment = new Comment();
        comment.setContent(commentParamDTO.getContent());
        comment.setCommentator(commentParamDTO.getCommentator());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        comment.setType(commentParamDTO.getType());
        if (commentParamDTO.getParentId() == null) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARENT_NOT_FOUND);
        }
        comment.setParentId(commentParamDTO.getParentId());
        if (commentParamDTO.getType() == null || !CommentTypeEnum.isExist(commentParamDTO.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (commentParamDTO.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment commentSource = commentMapper.selectByPrimaryKey(commentParamDTO.getParentId());
            if (commentSource == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            //回复问题
            Problem problem = problemService.getById(commentSource.getParentId());
            if (problem == null) {
                throw new CustomizeException(CustomizeErrorCode.PROBLEM_NOT_FOUND);
            }
            commentMapper.insert(comment);
            commentDao.incrComment(commentSource.getId(), commentSource.getCommentCount());
            //通知
            notificationService.insert(comment, NotificationTypeEnum.REPLY_COMMENT.getType(),
                    commentSource.getCommentator(),account.getUserName(),problem.getTitle(), problem.getId());
        } else if (commentParamDTO.getType() == CommentTypeEnum.PROBLEM.getType()) {
            //回复问题
            Problem problem = problemService.getById(commentParamDTO.getParentId());
            if (problem == null) {
                throw new CustomizeException(CustomizeErrorCode.PROBLEM_NOT_FOUND);
            }
            commentMapper.insert(comment);
            problemService.incrComment(problem);
            //通知
            notificationService.insert(comment, NotificationTypeEnum.REPLY_PROBLEM.getType(),
                    problem.getCreator(), account.getUserName(), problem.getTitle(), problem.getId());
        }
    }

    public List<CommentDTO> listByParentId(int id, int type) {
        return commentDao.listByParentId(id, type);
    }
}
