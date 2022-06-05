package com.jhm.service;

import com.jhm.pojo.Comment;

import java.util.List;

public interface CommentService {
    /*通过ID找评论信息*/
    List<Comment> getCommentByBlogId(Long blogId);
    /*插入评论*/
    int saveComment(Comment comment);
}
