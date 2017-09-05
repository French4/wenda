package com.nowcoder.toutiao.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/26.
 */
//@Controller//注解生成类
public class IndexController {
    @RequestMapping(path={"/","/index"})
    @ResponseBody
    public String index(HttpSession session){
        return "hello newCoder" + session.getAttribute("msg");
    }

    @RequestMapping(value={"profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile2(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1")int type,
                           @RequestParam(value = "key", defaultValue = "nowcoder") String key){
        return String.format("GID{%s}, UID{%d}, type{%d}, key{%s}", groupId, userId, type, key);
    }

    @RequestMapping("/vm")
    public String news(Map<String,Object> map){
        map.put("sd", "一定可哟");
        return "news";
    }

    @RequestMapping("/redirect/{code}")
    public String redirect(@PathVariable("code") int code, HttpSession session){
        //向session中写入数据
        session.setAttribute("msg","sd");
      return "redirect:/";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(@RequestParam(value="key", required = false) String key){
        if("admin".equals(key)){
            return "hello admin";
        }
        throw new IllegalArgumentException("key 错误");
    }
    @ExceptionHandler()
    @ResponseBody
    public String error(Exception e){
        return "error:" + e.getMessage();
    }
}
