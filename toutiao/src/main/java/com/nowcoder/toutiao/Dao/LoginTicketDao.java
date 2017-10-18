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
    String INSERT_FIFLDS = "user_id, expired, status, ticket";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIFLDS,
            ") values (#{userId},#{expired},#{status},#{ticket})"})
    int addTicket(LoginTicket loginTicket);

    @Select({"Select * from ",TABLE_NAME," where ticket = #{ticket}"})
    LoginTicket selectByTicket(String ticket);

    @Update({"Update ",TABLE_NAME," set status = #{status} where ticket = #{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}
