package com.nowcoder.toutiao.Service;

/**
 * Created by lenovo on 2017/8/28.
 */

import com.nowcoder.toutiao.Dao.LoginTicketDao;
import com.nowcoder.toutiao.Dao.UserDao;
import com.nowcoder.toutiao.model.LoginTicket;
import com.nowcoder.toutiao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginTicketDao loginTicketDao;

    public User getUser(int id){
        return userDao.selectById(id);
    }

    public Map<String, String> register(String username, String password){
        Map<String,String> map = new HashMap<>();
        //判断用户名是否是空
        if(StringUtils.isEmpty(username)){
            map.put("msg","用户名不能是空");
            return map;
        }

        //密码判断
        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能是空");
            return map;
        }

        //查询用户名是否存在
        User user= userDao.selectByName(username);
        if (user != null){
            map.put("msg", "用户已经存在");
            return map;
        }

        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png",
                new Random().nextInt(1000)));
        user.setPassword(password + user.getSalt());
        userDao.addUser(user);

        //得到ticket值
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);//放入map集合中返回到COntroller层

        return map;
    }

    public Map<String, Object> login(String username, String password){
        Map<String,Object> map = new HashMap<>();
        //判断用户名是否是空
        if(StringUtils.isEmpty(username)){
            map.put("msg","用户名不能是空");
            return map;
        }

        //密码判断
        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能是空");
            return map;
        }

        //查询用户名是否存在
        User user= userDao.selectByName(username);
        if (user == null){
            map.put("msg", "用户不存在");
            return map;
        }
        //查询用户的密码是否正确
        if((password + user.getSalt()).equals(user.getPassword())){
            map.put("msg", "用户密码不正确");
            return map;
        }

        //得到ticket值
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);//放入map集合中返回到COntroller层
        map.put("userId", user.getId());
        return map;
    }
    //添加一个Ticket
    public String addLoginTicket(int userId){
        //创建一个LoginTicket
        LoginTicket loginTicket = new LoginTicket();

        //添加用户id
        loginTicket.setUserId(userId);
        //创建时间
        Date date = new Date();//现在时间
        date.setTime(3600*24*1000 + date.getTime()); //加上延迟时间
        loginTicket.setExpired(date);//设置成新的时间
        //设置登录状态是有效的
        loginTicket.setStatus(0); //状态0表示有效的
        loginTicket.setTicket(UUID.randomUUID().toString().replace("-","")); //生成一个ticket
        loginTicketDao.addTIcket(loginTicket);

        //返回一个ticket值
        return loginTicket.getTicket();
    }

    //修改ticket的状态值
    public void loginOut(String ticket) {
        loginTicketDao.updateStatus(ticket,1);
    }

    public User selectNyName(String toName) {
        return  userDao.selectByName(toName);
    }
}
