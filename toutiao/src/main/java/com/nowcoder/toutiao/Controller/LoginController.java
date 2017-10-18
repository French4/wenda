package com.nowcoder.toutiao.Controller;


import com.nowcoder.toutiao.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/29.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/reg/"}, method = {RequestMethod.POST})
    public String reg(Map<String, String> module,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam("next") String next,
                      @RequestParam(value = "remember", defaultValue = "false") boolean remember,
                      HttpServletResponse response){
       try {
            Map<String, String> map = userService.register(username, password);
            if (map.containsKey("ticket")) {  //证明注册成功，将Cookie下放到浏览器
                Cookie cook = new Cookie("ticket", map.get("ticket"));
                cook.setPath("/");  //设置资源路径
                if(remember){
                    cook.setMaxAge(3600*24*5);
                }
                response.addCookie(cook);  //添加到Cookie中

                if(!StringUtils.isEmpty(next)){ //跳转
                    return "redirect:"+next;
                }
                return "redirect:/";  //返回首页
            } else {
                module.put("msg", map.get("msg"));
                return "login";
            }
        }catch (Exception e){
           logger.error("注册出错"+e.getMessage());
            return "login";
       }
    }

    @RequestMapping(path = {"/login/"}, method = {RequestMethod.POST})
    public String login(Map<String, Object> module,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("next") String next,
                        @RequestParam(value = "remember", defaultValue = "false") boolean remember,
                        HttpServletResponse response){
        try{
            Map<String, Object> map = new HashMap<>();
            map = userService.login(username, password);
            if(map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if(remember){
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);

                if(!StringUtils.isEmpty(next)){
                    return "redirect:"+next;
                }
                return "redirect:/";
            }else{
                module.put("msg", map.get("msg"));
                return "login";
            }
        }catch (Exception e){
            logger.error("登录异常",e.getMessage());
            return "login";
        }
    }
    //未登录录跳转
    @RequestMapping(path = {"/reglogin"}, method = {RequestMethod.GET})
    public String reglogin(Map<String, Object> map, @RequestParam(value = "next", required = false)String next){
        map.put("next", next);
        return "login";//返回登录页面，让游客进行登录
    }

    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/";
    }

}
