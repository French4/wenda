package com.nowcoder.toutiao.interceptor;

import com.nowcoder.toutiao.Dao.LoginTicketDao;
import com.nowcoder.toutiao.Dao.UserDao;
import com.nowcoder.toutiao.model.HostHolder;
import com.nowcoder.toutiao.model.LoginTicket;
import com.nowcoder.toutiao.model.User;
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
         for(Cookie cookie : request.getCookies()){
             if(cookie.getName().equals("ticket")){
                ticket = cookie.getValue();
             }
         }
      }
      //取出用户的相关信息
     if(ticket != null){
         LoginTicket loginTicket = loginTicketDao.selectByTicket(ticket);
         if(loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0){
           return true;
         }
         int usetId = loginTicket.getUserId();
         User user = userDao.selectById(usetId);
         hostHolder.setUsers(user);
     }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
          if(modelAndView != null){
            modelAndView.addObject("user", hostHolder.getUser());
          }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         hostHolder.remove();
    }
}
