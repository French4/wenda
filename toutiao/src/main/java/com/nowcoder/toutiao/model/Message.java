package com.nowcoder.toutiao.model;

import java.util.Date;

/**
 * Created by lenovo on 2017/9/3.
 * 评论中心
 */
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String content;
    private Date createdDate;
    private int hasRead;
    private String conversationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getHsaRead() {
        return hasRead;
    }

    public void setHsaRead(int hsaRead) {
        this.hasRead = hasRead;
    }

    public String getConversationId() {  //永远一致,用于选出两者之间的对话
       if(fromId < toId){
           return String.format("%d_%d", fromId, toId);
       }else{
           return String.format("%d_%d", toId, fromId);
       }
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
