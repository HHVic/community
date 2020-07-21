package cn.huan.community.community.dao;

import cn.huan.community.community.dto.ProblemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProblemDao {
    @Select("select * from problem")
    @Results({
                    @Result(property="user",column="creator",one=@One(select="cn.huan.community.community.mapper.UserMapper.getById"))
    })
    List<ProblemDTO> list();
}
