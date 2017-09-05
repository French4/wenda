package com.nowcoder.toutiao;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.nowcoder.toutiao.Dao.QuestionDao;
import com.nowcoder.toutiao.Dao.UserDao;
import com.nowcoder.toutiao.model.Question;
import com.nowcoder.toutiao.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitDatabseTests {

	@Autowired
	UserDao userDao;
	@Autowired
	QuestionDao questionDao;
	//@Test
	public void initDatanase() {
		Random random = new Random();

		for(int i = 0; i < 11; i++){
			User user = new User();
			user.setHeadUrl(String.format("http://images.newcoder.com/head/%dt.png", random.nextInt(1000)));
			user.setName(String.format("userId%d", i));
			user.setPassword("");
			user.setSalt("");
			userDao.addUser(user);

			Question question = new Question();
			question.setCommentCount(i);
			Date date = new Date();//现在的时间
			date.setTime(date.getTime()+1000*3600*i);
			question.setCreatedDate(date);
			question.setUserId(i+1);
			question.setTitle(String.format("title{%d}", i));
			question.setContent(String.format("sbabh %d", i));

			questionDao.addQuestion(question);
		}
		//测试xml编写的语句
		List<Question> list =  questionDao.selectLatestQuestions(2,2,2);
		for (Question question: list){
			System.out.println(question.getCreatedDate());
		}
		//更新密码
	//	User user = userDao.selectById(1);
	//	user.setPassword("1111");
	//	userDao.updatePassword(user);
		//删除用户
	//	userDao.deleteUserById(1);
	}

}
