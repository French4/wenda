package com.nowcoder.toutiao.Dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.nowcoder.toutiao.model.Question;

import java.util.List;

/**
 * Created by lenovo on 2017/8/27.
 */
@Mapper
@Repository
public interface QuestionDao {
    String TABLE_NAME = " question ";
    //INSERT INTO question(title, context, user_id, created_data, comment_count, content)
    // VALUES('sah', 'wq', 1,'1999-11-11',1,'sasa');
    String INSERT_FIELDS = " title, content, created_date, user_id, comment_count ";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title},#{content},#{createdDate},#{userId},#{commentCount})"})
    int addQuestion(Question question);

    //使用配置文件查询的方法
    //注意事项,方法名称必须和xml文件中的id属性一致
    List<Question> selectLatestQuestions(@Param("userId") int userId,
                                         @Param("offset") int offset,
                                         @Param("limit") int limit);


    @Select({"SELECT * FROM ", TABLE_NAME , " WHERE id = #{id}"})
    Question selectById(int id);

    @Update({"UPDATE ",TABLE_NAME," SET comment_count = #{comment_count} WHERE id = #{id}"})
    void updateCountById(@Param("id") int entityId, @Param("comment_count") int count);
}

