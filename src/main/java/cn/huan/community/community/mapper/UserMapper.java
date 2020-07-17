package cn.huan.community.community.mapper;

import cn.huan.community.community.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(account_id,user_name,token,gmt_create,gmt_modified)" +
            " VALUES(#{accountId},#{userName},#{token},#{gmtCreate},#{gmtModified})")
    public int insert(User user);
}
