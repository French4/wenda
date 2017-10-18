package com.nowcoder.toutiao.Service;

import com.nowcoder.toutiao.Dao.QuestionDao;
import com.nowcoder.toutiao.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/10/5.
 */
@Service
public class QuestionService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private SensitiveService sensitiveService;

    public int addQuestion(Question question){
        //过滤html标签
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        question.setTitle(sensitiveService.filter(question.getTitle()));
        question.setContent(sensitiveService.filter(question.getContent()));
        //调用Dao层，写入数据库
      return   questionDao.addQuestion(question);
    }

    public Question getById(int qid){
        return questionDao.getById(qid);
    }

    public void setCommentCount(int questionId, int count) {
        questionDao.setCommentCount(questionId, count);
    }

    public List<Question> getLastestQuestion(int userId, int offset, int limit) {
        return questionDao.selectLatestQuestions(userId, offset, limit);
    }
}
