package com.example.springboottest.mapper;

import com.example.springboottest.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator})")
    void create(Question question);

    @Select("select * from QUESTION limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from QUESTION where CREATOR=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") Integer userId, @Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question where CREATOR=#{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from QUESTION where id=#{id}")
    Question getByid(@Param("id") Integer id);

    @Update("update question set title=#{title},DESCRIPTION=#{description},GMT_MODIFIED=#{gmtModified},tag=#{tag} where id=#{id}")
    void update(Question question);
}

