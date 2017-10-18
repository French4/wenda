package com.nowcoder.toutiao.Service;

import com.nowcoder.toutiao.Dao.MessageDao;
import com.nowcoder.toutiao.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/10/5.
 */
@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private SensitiveService sensitiveService;

    public int addMeaasge(Message message){  //增加站内信
        message.setContent(sensitiveService.filter(message.getContent()));
       return messageDao.addMessage(message);
    }

    public List<Message> getMessageDetail(String conversationId, int limit, int offset){  //得到和一个人的聊天记录
        return  messageDao.getMessageDetail(conversationId, limit, offset);
    }

    public int getUnReadMessageCount(String conversationId, int userId){        //得到当前用户没有读的站内信
        return messageDao.getUnReadMessageCount(conversationId, userId);
    }

    public List<Message> getConversationList(int userId, int offset, int limit){
        return messageDao.getConversationList(userId, offset, limit);
    }

    public void updateReadStatus(int id, String conversationId) {
        messageDao.updateReadStatus(id, conversationId);
    }
}
