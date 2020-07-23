package cn.huan.community.community.dto;

import cn.huan.community.community.domain.Account;
import lombok.Data;

@Data
public class ProblemDTO {

    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tags;
    private Account account;
}
