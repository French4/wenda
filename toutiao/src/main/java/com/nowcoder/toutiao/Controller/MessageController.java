package com.nowcoder.toutiao.Controller;

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
 * Created by lenovo on 2017/10/5.
 */
@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private UserService userService;

    /**
     * 增加一个站内信的步骤:
     *  1.首先判断当前用户是否登录
     *  2.登录的话，根据toName查出User对象，判断toName是否存在，不存在返回一个值
     *  3.创建一个Message对象，存储froomId，toId，content, createdDate,conversationId
     *  4.调用Dao层方法
     */
    @RequestMapping(value = {"/msg/addMessage"},method = {RequestMethod.POST})
    @ResponseBody
    public String addMeaasge(@RequestParam("toName") String toName, @RequestParam("content") String content){
        try{
            if(hostHolder.getUser() == null){
                return WendaUtil.getJSONString(999);
            }
            User toUser = userService.findUserByName(toName);
            if(toUser == null){
                return WendaUtil.getJSONString(1, "用户不存在");
            }

            Message message = new Message();
            message.setContent(content);
            message.setToId(toUser.getId());
            message.setFromId(hostHolder.getUser().getId());
            message.setCreatedDate(new Date());
            messageService.addMeaasge(message);
            return WendaUtil.getJSONString(0);
        }catch (Exception e){
            logger.error("增加站内信失败"+e.getMessage());
            return WendaUtil.getJSONString(1,"插入站内信失败");
        }
    }

    /**
     *获取站内信的详细信息的步骤:
     * 1.根据conversationId选出相关的站内信
     * 2.取出每一条站内信，和发这个站内信的用户的头像和id关联起，放入一个ViewObject中
     */
    @RequestMapping(path = {"/msg/detail"}, method = {RequestMethod.GET})
    public String conversationDetail(Map<String, Object> module, @RequestParam("conversationId")String conversationId){
        try {
            List<Message> conversationDetail = messageService.getMessageDetail(conversationId,0,10);
            //更新数据库中has_read的值为1， 表示已经读过
            messageService.updateReadStatus(hostHolder.getUser().getId(),conversationId);
            List<ViewObject> messages = new ArrayList<>();
            for(Message message : conversationDetail){
                ViewObject vo = new ViewObject();
                vo.set("message", message);
                User user = userService.findUserById(message.getFromId());
                if(user == null){
                    continue;
                }
                vo.set("user", user);
                messages.add(vo);
            }
            module.put("messages", messages);
        }catch (Exception e){
            logger.error("获取评论详细信息出错"+e.getMessage());
        }
        return "letterDetail";
    }

    /**
     * 获取站内信列表
     * 1.查出当前用户的id
     * 2.根据当前用户id，得到站内信列表
     * 3.取出每个站内信，将其和站内信发送者关联起来，并且确定为读数。
     */
    @RequestMapping(path = {"/msg/list"}, method = {RequestMethod.GET})
    public String conversationList(Map<String, Object> map){
        try {
            int localId = hostHolder.getUser().getId();
            List<Message> conversationList = messageService.getConversationList(localId,0,10);
            List<ViewObject> conversations  = new ArrayList<>();
            for (Message message : conversationList){
                ViewObject vo  = new ViewObject();
                vo.set("message", message);
                int targetId = localId == message.getFromId()?message.getToId() : message.getFromId();
                User user = userService.findUserById(targetId);
                vo.set("user", user);
                vo.set("unread", messageService.getUnReadMessageCount(message.getConversationId(), localId));
                conversations.add(vo);
            }
            map.put("conversations", conversations);
        }catch (Exception e){
            logger.error("获取站内信列表失败"+e.getMessage());
        }
        return "letter";
    }
}
