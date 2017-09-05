package com.nowcoder.toutiao.Dao;

import com.nowcoder.toutiao.model.LoginTicket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/8/29.
 */
@Mapper
@Repository
public interface LoginTicketDao {
    String TABLE_NAME = " login_ticket ";
    //INSERT INTO question(title, context, user_id, created_data, comment_count, content)
    // VALUES('sah', 'wq', 1,'1999-11-11',1,'sasa');
    String INSERT_FIELDS = " user_id, expired, status, ticket ";

    @Insert({"insert into ", TABLE_NAME," (",  INSERT_FIELDS,
            ") values(#{userId},#{expired},#{status},#{ticket})"})
    int addTIcket(LoginTicket ticket);

    @Select({"SELECT * FROM ",TABLE_NAME," where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);  //登录时候查看ticket在不在

    @Update({"UPDATE ",TABLE_NAME," SET status = #{status} where ticket = #{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status); //这是登出改变状态时候使用
}
