package com.nowcoder.toutiao.Controller;

import com.nowcoder.toutiao.Dao.QuestionDao;
import com.nowcoder.toutiao.Service.QuestionService;
import com.nowcoder.toutiao.Service.UserService;
import com.nowcoder.toutiao.model.Question;
import com.nowcoder.toutiao.model.ViewObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by lenovo on 2017/8/28.
 */
@Controller
public class HomeController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);

    //定义service层的变量,并且进行自动注入
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/"}, method = {RequestMethod.GET})
    public String index(Map map){
        map.put("vos", getQuestions(0,0,10));
        return "index";
    }

    //返回一个人发的所有帖子,就是个人主页
    @RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET})
    public String userIndex(Map map, @PathVariable("userId") int userId){
        map.put("vos", getQuestions(userId,0,10)); //选取的问题有一个userId过滤
        return "index";//返回的模板名称
    }

    private List<ViewObject> getQuestions(int userId, int offset, int limit){
        List<Question> questionList =  questionService.getLastestQuestion(userId,offset,limit);//调用service层,选出问题
        List<ViewObject> vos = new ArrayList<ViewObject>();//创建一个ArrayList集合,其中存放ViewObject对象
        //ViewObject对象是内部是一个HashMap表,可以存放任何对象,因为一个板块有很多东西
        for (Question question : questionList){
            //创建一个ViewObject对象，存放问题和问问题的人
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userService.getUser(question.getUserId()));  //根据question中的UserId来查找User对象
            vos.add(vo);
        }
        return vos;
    }
}
