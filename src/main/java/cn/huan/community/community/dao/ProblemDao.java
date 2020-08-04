package cn.huan.community.community.dao;

import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.dto.ProblemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProblemDao {

    List<ProblemDTO> list(@Param("keyword")String keyword);

    List<ProblemDTO> listByUser(@Param("userId")int userId);

    ProblemDTO getByIdWithUser(@Param("id")int id);

    void incrView(@Param("id")int id,@Param("viewCount")int viewCount);

    void incrComment(@Param("id")Integer id, @Param("commentCount")Integer commentCount);

    List<Problem> listByTagsRelations(@Param("id") Integer id, @Param("tags") String tags);
}
