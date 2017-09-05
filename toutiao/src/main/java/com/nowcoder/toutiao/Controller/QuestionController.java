package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Dao.QuestionDao;
import com.nowcoder.toutiao.Service.CommentService;
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
 * Created by lenovo on 2017/8/31.
 */
@Controller
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    HostHolder hostHolder;  //这个对象用来得到用户信息,可以在任何时候使用
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title, @RequestParam("content") String content){
        try{
            Question question = new Question();
            question.setContent(content);
            question.setTitle(title);
            question.setCreatedDate(new Date());
            question.setCommentCount(0);

            //判断当前用户是否登录
            if (hostHolder.getUser() == null){
                return WendaUtil.getJSONString(999);
            }else {
                question.setUserId(hostHolder.getUser().getId()); //设置用户id信息
            }
            if(questionService.addQuestion(question) > 0){   //调用业务层进行处理
                return WendaUtil.getJSONString(0);  //添加成功,返回code == 0
            }
        }catch(Exception e){
            logger.error("增加题目失败", e.getMessage());
        }

        return WendaUtil.getJSONString(1,"失败"); //添加失败,返回
    }
    @RequestMapping(value = "/question/{qid}")
    public String questionDetail(Map<String,Object> map,
                                 @PathVariable("qid") int qid){
        Question question = questionService.selectById(qid);

        List<Comment> commentList = commentService.getCommentsByEntity(qid, EntityType.ENTITY_QUESTION);       //获得这个问题下的所有评论
        List<ViewObject> comments = new ArrayList<>();
        for(Comment comment : commentList){  //取出每一个comment，从其中得到用户Id
            ViewObject vo = new ViewObject();  //得到一个评论的方框
            vo.set("user", userService.getUser(comment.getUserId()));   //放入用户
            vo.set("comment", comment);         //放入评论
            comments.add(vo);           //将一个评论放入集合中,用于在前端页面获取数据
        }
        User user = userService.getUser(question.getUserId());
        map.put("question", question);
        map.put("user",user);
        map.put("comments", comments);
        return "detail";
    }
}
