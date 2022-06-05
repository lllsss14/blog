package com.jhm.dao;


import com.jhm.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentDao {
    public List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId") Long blogId);

    //查询父级对象
    Comment findByParentCommentId(@Param("parentCommentId")Long parentCommentId);

    public int saveComment(Comment comment);

}
