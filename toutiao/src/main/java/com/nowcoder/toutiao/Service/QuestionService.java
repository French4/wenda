package com.nowcoder.toutiao.Service;

import com.nowcoder.toutiao.Dao.QuestionDao;
import com.nowcoder.toutiao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/8/28.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    @Autowired
    SensitiveService sensitiveService;
    //调用Dao方法,根据用户id选择出最近limi条信息,id = 0的话,就是index主页的内容,否则是个人主页
    public List<Question> getLastestQuestion(int userId, int offset, int limit){
        return questionDao.selectLatestQuestions(userId,offset,limit);
    }

    //调用Dao层的方法添加一个问题,返回问题id
    public int addQuestion(Question question){
        //敏感词过滤
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));   //防止JavaScript代码的出现

        question.setContent(sensitiveService.filter(question.getContent()));//调用自己的代码过滤敏感词
        question.setTitle(sensitiveService.filter(question.getTitle()));
        return questionDao.addQuestion(question) > 0? question.getId() : 0;
    }

    //通过id查找问题
    public Question selectById(int id){
        return questionDao.selectById(id);
    }

    //根据id更新评论数
    public void updateCommentCount(int entityId, int count) {
        questionDao.updateCountById(entityId,count);
    }
}
