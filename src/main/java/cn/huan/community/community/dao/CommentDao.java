package cn.huan.community.community.dao;

import cn.huan.community.community.dto.CommentDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
    List<CommentDTO> listByParentId(@Param("parentId")int parentId,@Param("type")int type);

    void incrComment(@Param("id")int id,@Param("commentCount")int commentCount);
}
