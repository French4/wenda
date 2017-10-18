package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Service.QuestionService;
import com.nowcoder.toutiao.Service.UserService;
import com.nowcoder.toutiao.model.Question;
import com.nowcoder.toutiao.model.ViewObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/10/6.
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/"} , method = {RequestMethod.GET})
    public String index(Map map){
        map.put("vos", getQuestions(0,0,10));
        return "index";
    }

    @RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET})
    public String userIndex(Map map, @PathVariable("userId") int userId){
        map.put("vos", getQuestions(userId,0,10));
        return "index";
    }
    private List<ViewObject> getQuestions(int userId, int offset, int limit){
        List<Question> questionList = questionService.getLastestQuestion(userId, offset, limit);
        List<ViewObject> vos = new ArrayList<>();
        for(Question question : questionList){
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userService.findUserById(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
}
