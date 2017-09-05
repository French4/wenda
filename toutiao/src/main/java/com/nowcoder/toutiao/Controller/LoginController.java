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
import java.util.Map;

/**
 * Created by lenovo on 2017/8/29.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/reg/"}, method={RequestMethod.POST})
    public String reg(Map<String, String> module,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "next", required = false) String next,
                      HttpServletResponse response){
        try{
            //调用service层的方法
            Map<String, String> map = userService.register(username,password);
            if(map.containsKey("ticket")){ //map包含ticket,说明Dao层注册成功,取出ticket下放到浏览器
                Cookie cookie = new Cookie("ticket", map.get("ticket"));//生成一个Cookie
                cookie.setPath("/");//设置资源路径
                response.addCookie(cookie);//添加到Cookie中

                if(!StringUtils.isEmpty(next)){  //next非空的话,则返回其查看的个人主页
                    return "redirect:"+next;
                }
                return "redirect:/";
            }else{
                module.put("msg", map.get("msg"));
                return "login";
            }
            //如果有错误信息,则将错误信息返回给前端页面
          /*  if(map.containsKey("msg")){
                module.put("msg", map.get("msg"));
                return "login";
            }*/
            //return "redirect:/";
        }catch (Exception e){
            logger.error("注册出错", e.getMessage());
          //  System.out.print("错误信息"+e.getMessage());
            return "login";
        }

    }

    //登录页面
    @RequestMapping(path = {"/login/"}, method={RequestMethod.POST})
    public String login(Map<String, Object> module,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                        @RequestParam(value = "next", required = false) String next,
                      @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberme,
                        HttpServletResponse response
                        ){
        try{
            //调用service层的方法,得到一个map对象
            Map<String, Object> map = userService.login(username,password);

            if(map.containsKey("ticket")){ //map包含ticket,说明Dao层注册成功,取出ticket下放到浏览器
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());//生成一个Cookie
                cookie.setPath("/");//设置资源路径
                response.addCookie(cookie);//添加到Cookie中
                if (rememberme) {
                    cookie.setMaxAge(3600*24*5);
                }
                //判断next的值,如果是非空,则跳转到对应的主页
                if(!StringUtils.isEmpty(next)){
                    return "redirect:"+next;
                }
                return "redirect:/";
            }else{
                module.put("msg", map.get("msg"));
                return "login";
            }

        }catch (Exception e){
            logger.error("登录异常", e.getMessage());
            return "login";
        }
    }
    //登出操作,修改ticket的状态值,将其修改成1
    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket){
        userService.loginOut(ticket);
        return "redirect:/";
    }
    //未登录浏览，登录后跳转到该页面
    @RequestMapping(path={"/reglogin"}, method = {RequestMethod.GET}) // http://127.0.0.1:8080/reglogin?next=/user/11
    public String reg(Map<String, Object> map, @RequestParam(value = "next", required = false) String next){
        map.put("next",next); //写入到前端页面中去
        return "login";
    }
}
