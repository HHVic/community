package cn.huan.community.community.mapper;

import cn.huan.community.community.domain.Problem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProblemMapper {

    @Insert("insert into problem(title,description,gmt_create,gmt_modified,creator,tags)" +
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tags})")
    void add(Problem problem);

    @Select("select * from problem")
    List<Problem> list();

    @Select("select * from problem where id=#{id}")
    Problem getById(@Param("id") int id);

    @Update("update problem set title = #{title},description = #{description},gmt_modified#{gmtModified},tags = #{tags} where id={id}")
    void update(Problem problem);
}
