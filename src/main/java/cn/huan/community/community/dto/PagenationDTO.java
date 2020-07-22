package cn.huan.community.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class PagenationDTO<T> {
    private int pageNum;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private List<T> problems;
}
