package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Dao.MessageDAO;
import com.nowcoder.toutiao.Service.MessageService;
import com.nowcoder.toutiao.Service.UserService;
import com.nowcoder.toutiao.model.HostHolder;
import com.nowcoder.toutiao.model.Message;
import com.nowcoder.toutiao.model.User;
import com.nowcoder.toutiao.model.ViewObject;
import com.nowcoder.toutiao.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/3.
 */
@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    HostHolder hostHolder;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    //选取和所有人的站内信
   @RequestMapping(path = {"/msg/list"}, method = {RequestMethod.GET})
    public String getConversationList(Map map){
        if(hostHolder.getUser() == null){
            return "redirect:/login";
        }
        int localUserId = hostHolder.getUser().getId();  //得到当前登录用户的id
        //得到用户的全部站内信
        List<Message> conversationList = messageService.getConversationList(localUserId,0,10);
        List<ViewObject> conversations = new ArrayList<>();

        //遍历
        for(Message message : conversationList){
            ViewObject vo = new ViewObject();
            vo.set("message",message);
            //得到对面用户的id
            int targetId = message.getFromId() == localUserId? message.getToId():message.getFromId();
            vo.set("user",userService.getUser(targetId));
            vo.set("unread", messageService.getConversationUnreadCount(localUserId, message.getConversationId()));
            conversations.add(vo);
        }
        map.put("conversations", conversations);
        return "letter";
    }

    //详细信息,取出和一个人聊天的所有消息
    @RequestMapping(path = {"/msg/detail"}, method = {RequestMethod.GET})
    public String getConversationalDetail(Map map, @RequestParam("conversationId") String conversationId){
        try{
            List<Message> messageList = messageService.getConversationnDetail("conversationId",0,10);//取出详情页面
            List<ViewObject> messages = new ArrayList<>();
            for(Message message: messageList){
                ViewObject vo = new ViewObject();
                vo.set("message", message); //设置发送的信息
                vo.set("user", userService.getUser(message.getFromId()));  //设置消息当前的用户
                messages.add(vo);  //添加到集合中去
            }

            map.put("messages", messages);
        }catch (Exception e){
            logger.error("获取详情失败",e.getMessage());
        }
        return "letterDetail";
    }


    //发送消息
    @RequestMapping(path = {"/msg/addMessage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,
                             @RequestParam("content")  String content){
        try{
            if(hostHolder.getUser() == null){
                return WendaUtil.getJSONString(999, "未登录");
            }

            User user = userService.selectNyName(toName); //查找用户
            if(user == null){
                return WendaUtil.getJSONString(1,"用户不存在");
            }

            Message message = new Message();   //开启一个消息
            message.setCreatedDate(new Date());  //设置消息发送时间
            message.setFromId(hostHolder.getUser().getId());  //发送者id
            message.setToId(user.getId());  //接收者id
            message.setContent(content);    //设置发送信息
            messageService.addMessage(message);  //添加一个发送消息
            return WendaUtil.getJSONString(0);
        }catch (Exception e){
            logger.error("发送消息失败" +e.getMessage());
            System.out.print(e.getMessage());
            return WendaUtil.getJSONString(1,"发送信息失败");
        }
    }
}
