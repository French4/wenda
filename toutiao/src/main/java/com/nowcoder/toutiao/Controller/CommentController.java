package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Service.CommentService;
import com.nowcoder.toutiao.Service.QuestionService;
import com.nowcoder.toutiao.Service.SensitiveService;
import com.nowcoder.toutiao.model.Comment;
import com.nowcoder.toutiao.model.EntityType;
import com.nowcoder.toutiao.model.HostHolder;
import com.nowcoder.toutiao.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by lenovo on 2017/10/5.
 */
@Controller
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    HostHolder hostHolder;
    @Autowired
    SensitiveService sensitiveService;
    @Autowired
    CommentService commentService;
    @Autowired
    QuestionService questionService;
    @RequestMapping(path = {"/addComment"}, method = {RequestMethod.POST})
    public String addComment(@RequestParam("questionId") int questionId,
                             @RequestParam("content") String comtent){
       try{
           Comment comment = new Comment();
           comment.setContent(sensitiveService.filter(comtent));
           comment.setEntityId(questionId);
           comment.setEntityType(EntityType.ENTITY_QUESTION);
           comment.setCreatedDate(new Date());
           comment.setStatus(0);
           if (hostHolder.getUser() != null){
               comment.setUserId(hostHolder.getUser().getId());
           }else{
               WendaUtil.getJSONString(999);
           }
           commentService.addComment(comment);
           //更改评论数量
           int count = commentService.getCommentCount(EntityType.ENTITY_QUESTION, questionId);

           questionService.setCommentCount(questionId, count);
       }catch (Exception e){
           logger.error("增加评论失败", e.getMessage());
       }
       return "redirect:/question/" + String.valueOf(questionId);
    }
}
