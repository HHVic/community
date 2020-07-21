package cn.huan.community.community.mapper;

import cn.huan.community.community.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(account_id,user_name,token,gmt_create,gmt_modified,avatar_url)" +
            " VALUES(#{accountId},#{userName},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    public int insert(User user);

    @Select("SELECT id,account_id,user_name,token,gmt_create,gmt_modified,avatar_url FROM user WHERE token=#{token}")
//    @Results(
//            value = {
//                    @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
//                    @Result(column = "account_id", property = "accountId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
//                    @Result(column = "user_name", property = "userName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
//                    @Result(column = "token", property = "token", javaType = String.class, jdbcType = JdbcType.VARCHAR),
//                    @Result(column = "gmt_create", property = "gmtCreate", javaType = Long.class, jdbcType = JdbcType.BIGINT),
//                    @Result(column = "gmt_modified", property = "gmtModified", javaType = Long.class, jdbcType = JdbcType.BIGINT),
//                    @Result(column = "avatar_url", property = "avatarUrl", javaType = String.class, jdbcType = JdbcType.VARCHAR)
//            }
//    )
    User getByToken(@Param("token") String token);

    @Select("SELECT id,account_id,user_name,token,gmt_create,gmt_modified,avatar_url FROM user WHERE account_id=#{id}")
    User getById(@Param("id") Integer id);
}
