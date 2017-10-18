package com.nowcoder.toutiao.Dao;

import com.nowcoder.toutiao.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/8/27.
 */
@Mapper
@Repository
public interface UserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name, password, salt, head_url ";
    String SELECT_FIELDS = " id, name, password, salt, head_url";

    @Insert({"insert into", TABLE_NAME, "(",INSERT_FIELDS,
            ") values(#{name}, #{password}, #{salt}, #{headUrl})"})
    int addUser(User user);

    @Select({"SELECT * FROM ", TABLE_NAME, " where id=#{id}"})
    User selectById(@Param("id")int id);

    @Update({"update ", TABLE_NAME, " set password = #{password} where id = #{id}"})
    void updatePassword(User user);

    @Delete({"delete from ",TABLE_NAME, "where id = #{id}"})
    void deleteUserById(@Param("id") int id);

    @Select({"SELECT ",SELECT_FIELDS ," FROM ",TABLE_NAME," WHERE name = #{name}"})
    User selectByName(@Param("name") String name);

    @Select({"select ",SELECT_FIELDS," FROM ",TABLE_NAME," where id = #{fromId}"})
    User findUserById(@Param("fromId") int fromId);
}

