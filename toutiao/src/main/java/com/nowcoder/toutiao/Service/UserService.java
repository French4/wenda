package com.nowcoder.toutiao.Service;


import com.nowcoder.toutiao.Dao.LoginTicketDao;
import com.nowcoder.toutiao.Dao.UserDao;
import com.nowcoder.toutiao.model.LoginTicket;
import com.nowcoder.toutiao.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by lenovo on 2017/10/4.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginTicketDao loginTicketDao;
    //注册功能
    public Map<String, String> register(String name, String password){
        Map<String, String> map = new HashMap<>();
        //合法性检验
        if(!StringUtils.hasText(name)){
            map.put("msg", "用户名为空");
            return map;
        }
        if(!StringUtils.hasText(password)){
            map.put("msg", "密码为空");
            return map;
        }
        User user = userDao.selectByName(name);
        if(user != null){
            map.put("msg", "用户名已经存在");
            return map;
        }

        user = new User();
        user.setName(name);  //设置姓名

        //设置密码的段研制
        String salt = UUID.randomUUID().toString().substring(0, 5);
        user.setSalt(salt);  //加密

        //设置密码
        user.setPassword(password+ salt);

        //设置头像
        user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png",
                new Random().nextInt(1000)));

        userDao.addUser(user);

        String ticket  = addTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }

    //登录功能
    public Map<String, Object> login(String username, String password){
        Map<String, Object> map = new HashMap<>();

        //合法性检验
        if(!StringUtils.hasText(username)){
            map.put("msg", "用户名为空");
            return map;
        }
        if(!StringUtils.hasText(password)){
            map.put("msg", "密码为空");
            return map;
        }

        //查询用户名是否存在
        User user = userDao.selectByName(username);
        if(user == null){
            map.put("msg", "用户名不存在");
            return map;
        }

        //检查用户密码是否正确
        if(!user.getPassword().equals(password+user.getSalt())){
            map.put("msg", "用户密码不正确");
            return map;
        }

        //得到Ticket，并且返回给上一级
        String ticket  = addTicket(user.getId());
        map.put("ticket", ticket);
        map.put("userId", user.getId());
        return map;
    }

    public  String addTicket(int userId){
        LoginTicket loginTicket = new LoginTicket();

        loginTicket.setUserId(userId);  //设置用户Id
        //设置Ticket
        loginTicket.setTicket(UUID.randomUUID().toString().replace("-",""));

        //设置登录状态
        loginTicket.setStatus(0);

        //设置有效时间
        Date date = new Date();
        date.setTime(date.getTime()+3600*24*1000);
        loginTicket.setExpired(date);

        loginTicketDao.addTicket(loginTicket);
        return loginTicket.getTicket();
    }

    public void logout(String ticket) {
        loginTicketDao.updateStatus(ticket, 1);
    }

    public User findUserByName(String toName) {
       return userDao.selectByName(toName);
    }

    public User findUserById(int fromId) {
        return userDao.findUserById(fromId);
    }
}
