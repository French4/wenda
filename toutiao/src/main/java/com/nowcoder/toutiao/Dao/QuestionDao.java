package com.nowcoder.toutiao.Dao;

import com.nowcoder.toutiao.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/10/5.
 */
@Mapper
@Repository
public interface QuestionDao {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title, content, created_date, user_id, comment_count ";
    String SELECT_FIELDS = " id, "+ INSERT_FIELDS;

    //增加一个问题
    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values (#{title}, #{content}, #{createdDate}, #{userId}, #{commentCount})"})
    int addQuestion(Question question);

    @Select({"Select ",SELECT_FIELDS," from ",TABLE_NAME," where id = #{qid}"})
    Question getById(int qid);

    @Update({"update ",TABLE_NAME," set comment_count = #{count} where id = #{questionId} "})
    void setCommentCount(@Param("questionId") int questionId, @Param("count") int count);


    List<Question> selectLatestQuestions( @Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);
}
