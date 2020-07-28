package cn.huan.community.community.dto;

import lombok.Data;

@Data
public class FileDTO {
    private int success;          // 0 表示上传失败，1 表示上传成功
    private String message;
    private String url;        // 上传成功时才返回
}
