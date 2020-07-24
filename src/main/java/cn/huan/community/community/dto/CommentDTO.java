package cn.huan.community.community.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Integer parentId;
    private String content;
    private Integer type;
    private Integer commentator;
}
