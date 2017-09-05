package com.nowcoder.toutiao.Service;

import com.nowcoder.toutiao.Dao.CommentDAO;
import com.nowcoder.toutiao.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by lenovo on 2017/9/2.
 */
@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;
    @Autowired
    SensitiveService sensitiveService;  //敏感词过滤

    public List<Comment> getCommentsByEntity(int entityId, int entityType){     //返回一个实体类(问题)的评论列表
        return commentDAO.selectCommentByEntity(entityId, entityType);
    }

    public int addComment(Comment comment){                                       //添加一个评论
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveService.filter(comment.getContent()));
        return commentDAO.addComment(comment) > 0 ? comment.getId() : 0;
    }

    public int getCommentCount(int entityId, int entityType){                            //返回一个实体类的评论总数
        return commentDAO.getCommentCount(entityId,entityType );
    }

    public int  deleteComment(int CommentId){                           //删除一个评论
        return commentDAO.deleteComment(CommentId,1) > 0 ? commentDAO.deleteComment(CommentId, 1) : 0;
    }
}
