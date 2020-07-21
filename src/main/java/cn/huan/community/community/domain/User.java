package cn.huan.community.community.domain;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String accountId;
    private String userName;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
