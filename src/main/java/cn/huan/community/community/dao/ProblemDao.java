package cn.huan.community.community.dao;

import cn.huan.community.community.dto.ProblemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProblemDao {

    List<ProblemDTO> list();


    List<ProblemDTO> listByUser(@Param("userId")int userId);
}
