package com.nowcoder.toutiao.Dao;

import com.nowcoder.toutiao.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/10/5.
 */
@Mapper
@Repository
public interface CommentDao {
    String TABLE_NAME = " comment ";
    String INSERT_FIELDS = " user_id, content, created_date, entity_type, entity_id, status ";
    String SELECT_FIELDS = " id, "+ INSERT_FIELDS;

    //添加一个评论
    @Insert({"insert into ",TABLE_NAME," ( ",INSERT_FIELDS,") values (#{userId}, #{content}, #{createdDate}, #{entityType}, #{entityId}," +
            "#{status})"})
    void addComment(Comment comment);

    //删除一个评论
    @Update({"update ",TABLE_NAME," SET status = #{status} where entity_type = #{entityType} and entity_id = #{entityId}"})
    void updateStatus(@Param("entityType") int entityType, @Param("entityId") int entityId, @Param("status") int status);

    //选出所有的评论
    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where entity_type = #{entityType} and entity_id = #{entityId}"})
    List<Comment> selectByEntity(@Param("entityType") int entityType,  @Param("entityId") int entityId);

    //得到评论总数
    @Select({"select count(id) from ",TABLE_NAME," where entity_type = #{entityType} and entity_id = #{entityId} "})
    int getCommentCount(@Param("entityType") int entityType,  @Param("entityId") int entityId);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where id = #{commentId}"})
    Comment getCommentById(@Param("commentId") int commentId);
}
