package cn.huan.community.community.mapper;

import cn.huan.community.community.domain.Problem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProblemMapper {

    @Insert("insert into problem(title,description,gmt_create,gmt_modified,creator,tags)" +
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tags})")
    void add(Problem problem);
}
