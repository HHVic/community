package cn.huan.community.community.dto;

import cn.huan.community.community.domain.Account;
import cn.huan.community.community.domain.Comment;
import lombok.Data;

@Data
public class CommentDTO extends Comment {
    private Account account;
}
