package com.nowcoder.toutiao.interceptor;

import com.nowcoder.toutiao.Dao.LoginTicketDao;
import com.nowcoder.toutiao.Dao.UserDao;
import com.nowcoder.toutiao.model.HostHolder;
import com.nowcoder.toutiao.model.LoginTicket;
import com.nowcoder.toutiao.model.User;
import org.omg.PortableInterceptor.IORInterceptor_3_0Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by lenovo on 2017/8/29.
 */
//注册一个拦截器
@Component
public class PassportInterceptor implements HandlerInterceptor{
    @Autowired
   private  LoginTicketDao loginTicketDao;
    //在Controller之前调用
    @Autowired
    private UserDao userDao;
    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //找到ticket
        String ticket = null;
        if(request.getCookies() != null){
            for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals("ticket")){
                    ticket = cookie.getValue(); //得到ticket的之
                }
            }
        }

        //取出用户的相关信息
        if(ticket != null){
            LoginTicket loginTicket = loginTicketDao.selectByTicket(ticket); //找到LoginTicket对象
            if(loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0){
                //判断取得对象的session时间和登录状态是否合法
                return true;  //false是结束访问
            }

            //取出这个ticket对应的User对象,放到上下文中可以让信息共享
            int id = loginTicket.getUserId();
            User user = userDao.selectById(loginTicket.getUserId());
            hostHolder.setUser(user);//放在了 ThreadLocal线程池中，所有的对象都可以进行使用
            System.out.println(loginTicket.getUserId());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){
            modelAndView.addObject("user",hostHolder.getUser());  //在渲染的页面都可以直接访问user对象
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //结束的时候进行清空
        hostHolder.clear();
    }
}
