package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Service.CommentService;
import com.nowcoder.toutiao.Service.LikeService;
import com.nowcoder.toutiao.async.EventModel;
import com.nowcoder.toutiao.async.EventProducer;
import com.nowcoder.toutiao.async.EventType;
import com.nowcoder.toutiao.model.Comment;
import com.nowcoder.toutiao.model.EntityType;
import com.nowcoder.toutiao.model.HostHolder;
import com.nowcoder.toutiao.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lenovo on 2017/10/6.
 */
@Controller
public class LikeController {
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    LikeService likeService;

    @Autowired
    EventProducer eventProducer;

    @Autowired
    CommentService commentService;
    /*
    * 第一：判断当前用户是否登录
    * 第二：如果登录，调用servcie方法，传入调用的id，问题类型和问题id
    * */
    @RequestMapping(path = {"/like"}, method = {RequestMethod.POST})
    public String like(@RequestParam("commentId") int commentId){
        if(hostHolder.getUser() == null){
            return WendaUtil.getJSONString(999);
        }

        Comment comment = commentService.findCommentById(commentId);
        eventProducer.fireEvent(new EventModel(EventType.LIKE)
        .setActorId(hostHolder.getUser().getId()).setEntityId(commentId).setEntityOwnerId(comment.getUserId())
        .setEntityType(EntityType.ENTITY_COMMENT).setExt("questionId", String.valueOf(comment.getEntityId())));
        long likeCount = likeService.like(EntityType.ENTITY_COMMENT,commentId,hostHolder.getUser().getId());
        return WendaUtil.getJSONString(0,String.valueOf(likeCount));
    }

    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.POST})
    public String dislike(@RequestParam("commentId") int commentId){
        if(hostHolder.getUser() == null){
            return WendaUtil.getJSONString(999);
        }
        long likeCount = likeService.dislike(EntityType.ENTITY_COMMENT,commentId,hostHolder.getUser().getId());
        return WendaUtil.getJSONString(0,String.valueOf(likeCount));
    }
}
