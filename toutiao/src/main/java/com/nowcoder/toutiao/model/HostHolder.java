package com.nowcoder.toutiao.model;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/8/29.
 */
@Component
//直接将其实例化
public class HostHolder {
   private static ThreadLocal<User> users = new ThreadLocal<>();

    public void setUsers(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void remove(){
        users.remove();
    }
}
