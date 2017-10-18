package com.nowcoder.toutiao.Dao;

import com.nowcoder.toutiao.model.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2017/10/5.
 */
@Mapper
@Repository
public interface MessageDao {
    String TABLE_NAME = " message ";
    String INSERT_FIELDS = " from_id, to_id, content, has_read, conversation_id, created_date ";
    String SELECT_FIELDS = " id, "+ INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{fromId},#{toId},#{content},#{hasRead},#{conversationId},#{createdDate})"})
    int addMessage(Message message);

    //选出聊天记录
    @Select({"select ",INSERT_FIELDS," from ",TABLE_NAME," where conversation_id = #{conversationId}  order by id desc limit " +
            "#{offset}, #{limit}"})
    List<Message> getMessageDetail(@Param("conversationId") String conversationId,
                                   @Param("offset") int offset,
                                   @Param("limit") int limit);

    //选出没有读取的站内信
    @Select({"SELECT count(id) from ",TABLE_NAME," where has_read = 0 and to_id = #{userId} and conversation_id = #{conversationId}"})
    int getUnReadMessageCount(@Param("conversationId") String conversationId,
                                   @Param("userId") int userId);

    //选取站内信列表
    @Select({"select ",INSERT_FIELDS,", count(id) as id from (select * from ",TABLE_NAME," where from_id = #{userId} or to_id = #{userId} order by id desc)" +
            " tt group by conversation_id order by created_date desc limit #{offset}, #{limit}"})
    List<Message> getConversationList(@Param("userId") int userId,
                                      @Param("offset") int offset,
                                      @Param("limit") int limit);

   @Update({"UPDATE ",TABLE_NAME," set has_read = 1 where to_id = #{id} and conversation_id = #{conversationId}"})
    void updateReadStatus(@Param("id") int id, @Param("conversationId") String conversationId);
}
