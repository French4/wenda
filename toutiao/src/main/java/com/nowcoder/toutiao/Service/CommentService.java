package com.nowcoder.toutiao.Service;

import com.nowcoder.toutiao.Dao.CommentDao;
import com.nowcoder.toutiao.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/10/5.
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private SensitiveService sensitiveService;

    /**
     * 添加一个评论
     * @param comment
     */
    public void addComment(Comment comment){
        comment.setContent(sensitiveService.filter(comment.getContent()));
        commentDao.addComment(comment);
    }
    /**
     * 删除一个评论
     */
    public void deleteComment(int entityId, int entityType ){
        commentDao.updateStatus(entityId, entityType, 1);
    }
    /**
     * 等到评论总数
     */
    public int getCommentCount(int entityType, int entityId){
        return commentDao.getCommentCount(entityType, entityId);
    }
    /**
     * 得到评论的列表
     */
    public List<Comment> selectByEntity(int entityId, int entityType){
        return commentDao.selectByEntity(entityId, entityType);
    }

    public Comment findCommentById(int commentId) {
        return commentDao.getCommentById(commentId);
    }
}
