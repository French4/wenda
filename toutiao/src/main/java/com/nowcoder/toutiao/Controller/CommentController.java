package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Service.CommentService;
import com.nowcoder.toutiao.Service.QuestionService;
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
 * Created by lenovo on 2017/9/2.
 */
@Controller
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    HostHolder hostHolder;
    @Autowired
    CommentService commentService;
    @Autowired
    QuestionService questionService;

    //增加一个评论
    @RequestMapping(path = {"/addComment"}, method = {RequestMethod.POST})
    public String addComment(@RequestParam("questionId") int questionId,
                             @RequestParam("content")  String content){
        try{
            Comment comment = new Comment();
            comment.setContent(content);

            if(hostHolder.getUser() != null){           //用户已登录
                comment.setUserId(hostHolder.getUser().getId());
            }else{                                      //用户未登录
                comment.setUserId(WendaUtil.ANOYMOUS_USERID);//匿名用户登录
            }
            comment.setCreateDate(new Date());
            comment.setEntityId(questionId); //设置问题的id
            comment.setEntityType(EntityType.ENTITY_QUESTION); //用数字表述评论类型
            commentService.addComment(comment);

            //更新评论总数
            int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());//得到评论总数
            questionService.updateCommentCount(comment.getEntityId(), count);
        }catch (Exception e){
            //记下错误
            logger.error("增加评论错误",e.getMessage());
            System.out.print(e.getMessage());
        }
        return "redirect:/question/"+questionId;  //返回当前的页面
    }

}
