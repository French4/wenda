package com.nowcoder.toutiao.model;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/8/29.
 */
@Component
//直接将其实例化
public class HostHolder {
    //为了线程安全
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser(){
        return users.get(); //从线程池中取出
    }
    public void setUser(User user){
        users.set(user); //放到线程池
    }

    public void clear(){
        users.remove();
    }
}
