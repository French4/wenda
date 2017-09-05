package com.nowcoder.toutiao.Service;

import com.nowcoder.toutiao.Dao.MessageDAO;
import com.nowcoder.toutiao.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/9/3.
 * 消息中心
 */
@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;
    @Autowired
    SensitiveService sensitiveService;

    //添加一条站内信
    public int addMessage(Message message){
        message.setContent(sensitiveService.filter(message.getContent()));
        return messageDAO.addMessage(message) > 0 ? message.getId() : 0;
    }

    //得到和某人的全部信息
    public List<Message> getConversationnDetail(String conversationId, int offset, int limit){
        return  messageDAO.getConversationDetail(conversationId,offset,limit);
    }

    //当前用户的全部站内信
    public List<Message> getConversationList(int userId, int offset, int limit){
       return messageDAO.getConversationList(userId,offset,limit);
   }

    //得到当前用户未读信数
    public int getConversationUnreadCount(int userId, String conversationId){
        return messageDAO.getConversationUnreadCount(userId, conversationId);
    }
}
