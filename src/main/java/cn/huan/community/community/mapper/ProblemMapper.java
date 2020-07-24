package cn.huan.community.community.mapper;

import cn.huan.community.community.domain.Problem;
import cn.huan.community.community.domain.ProblemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProblemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    long countByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int deleteByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int insert(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int insertSelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    List<Problem> selectByExample(ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    Problem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int updateByExampleSelective(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int updateByExample(@Param("record") Problem record, @Param("example") ProblemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int updateByPrimaryKeySelective(Problem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table problem
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    int updateByPrimaryKey(Problem record);
}