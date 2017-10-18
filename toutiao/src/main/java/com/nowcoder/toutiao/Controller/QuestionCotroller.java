package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Service.CommentService;
import com.nowcoder.toutiao.Service.LikeService;
import com.nowcoder.toutiao.Service.QuestionService;
import com.nowcoder.toutiao.Service.UserService;
import com.nowcoder.toutiao.model.*;
import com.nowcoder.toutiao.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/5.
 */
@Controller
public class QuestionCotroller {
    private static final Logger logger = LoggerFactory.getLogger(QuestionCotroller.class);
    @Autowired
    HostHolder hostHolder;
    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {
        try{
            Question question = new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setCreatedDate(new Date());
            if(hostHolder.getUser() == null){
                return WendaUtil.getJSONString(999);
            }else {
                question.setUserId(hostHolder.getUser().getId());
            }
            question.setCommentCount(0);
            if(questionService.addQuestion(question) > 0){
                return WendaUtil.getJSONString(0);
            }
        }catch (Exception e){
            logger.error("增加题目失败"+e.getMessage());
        }
        return WendaUtil.getJSONString(1,"添加问题失败");
    }

    @RequestMapping(value = "question/{qid}", method = {RequestMethod.GET})
    public String questionDetail(Map<String, Object> model, @PathVariable("qid") int qid){
        try{
            {
                Question question = questionService.getById(qid);
                List<ViewObject> comments = new ArrayList<>();
                List<Comment> commentList = commentService.selectByEntity(EntityType.ENTITY_QUESTION, qid);
                for (Comment comment: commentList){
                    ViewObject viewObject = new ViewObject();
                    viewObject.set("comment", comment);
                    viewObject.set("likeCount", likeService.getLikeCount(EntityType.ENTITY_COMMENT, comment.getId()));

                    if(hostHolder.getUser() == null){
                        viewObject.set("liked", 0);
                    }else{
                        viewObject.set("liked", likeService.getStatus(EntityType.ENTITY_COMMENT,comment.getId(),hostHolder.getUser().getId()));
                    }
                    User user = userService.findUserById(comment.getUserId());
                    viewObject.set("user",user);
                    comments.add(viewObject);
                }
                model.put("question", question);
                model.put("comments", comments);
            }
        }catch (Exception e){
            logger.error("获取问题详情失败"+e.getMessage());
        }
        return "detail";
    }
}
