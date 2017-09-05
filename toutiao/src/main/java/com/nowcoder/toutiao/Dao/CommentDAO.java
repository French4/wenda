package com.nowcoder.toutiao.Dao;

import com.nowcoder.toutiao.model.Comment;
import com.nowcoder.toutiao.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/8/27.
 */
@Mapper
@Repository
public interface CommentDAO {
    String TABLE_NAME = " comment ";
    //INSERT INTO question(title, context, user_id, created_data, comment_count, content)
    // VALUES('sah', 'wq', 1,'1999-11-11',1,'sasa');
    String INSERT_FIELDS = " user_id, content, created_date, entity_id, entity_type, status ";
    String SELECT_FIELDS = "id, " + INSERT_FIELDS;

    //插入一个评论
    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{content},#{createdDate},#{entityId},#{entityType}, #{status})"})
    int addComment(Comment comment);

    //选出一个实体内中的全部评论 @Param表示的是一个将entityId映射到Select语句中的entityId的名字中
    @Select({"SELECT ", SELECT_FIELDS ," from ", TABLE_NAME ," where entity_id = #{entityId} and entity_type = #{entityType} order by " +
            "created_date desc"})
    List<Comment> selectCommentByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    //选择有多少个评论
    @Select({"SELECT count(*) FROM ", TABLE_NAME," WHERE entity_id = #{entityId} AND entity_type = #{entityType}"})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);

    //删除一个评论
    @Update({"UPDATE ",TABLE_NAME," SET status = #{status} where id = #{Id}"})
    int deleteComment(@Param("id") int id, @Param("status") int status);

}

