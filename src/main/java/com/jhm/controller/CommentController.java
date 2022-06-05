package com.jhm.controller;

import com.jhm.pojo.Comment;
import com.jhm.service.BlogService;
import com.jhm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")  //展示留言
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.getCommentByBlogId(blogId));

        return "blog :: commentList";
    }
    /*提交评论*/
    @PostMapping("/comments")
    public String post(Comment comment,Long blogId){
        Long blogid =blogId;
        comment.setBlogInfo(blogService.getBlog(blogid));/*将博客表和评论表绑定，绑定评论到对应的博客*/
        comment.setAvatar(avatar);
        comment.setBlogId(blogid);

        System.out.println("有没有评论"+comment);
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogid;
    }
}
