package com.jhm.service.Impl;

import com.jhm.dao.BlogDao;
import com.jhm.dao.CommentDao;
import com.jhm.pojo.Comment;
import com.jhm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;
    @Override
    /*查询父评论,通过自身blogId和父评论id*/
    public List<Comment> getCommentByBlogId(Long blogId) {
        /*没有父评论的默认为-1*/
        List<Comment> comments = commentDao.findByBlogIdAndParentCommentNull(blogId);
        System.out.println("测试评论输出"+comments);
        return comments;
    }

    @Override
    /*接收回复的表单*/
    public int saveComment(Comment comment) {
        /*获得父评论id*/
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
                //有父级评论
                comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
                comment.setParentCommentId(parentCommentId);
        }else {
            comment.setParentComment(null);
            comment.setParentCommentId((long) -1);
        }
        comment.setCreate_time(new Timestamp(new Date().getTime()));
        return commentDao.saveComment(comment);
    }
}
