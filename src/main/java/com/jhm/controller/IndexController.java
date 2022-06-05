package com.jhm.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhm.pojo.BlogInfo;
import com.jhm.pojo.Tag;
import com.jhm.pojo.Type;
import com.jhm.service.BlogService;
import com.jhm.service.CommentService;
import com.jhm.service.TagService;
import com.jhm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",
            value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum,5);

        List<BlogInfo>  blogInfos =blogService.getIndexBlog();  /*首页博客列表*/
        List<Type> types=typeService.getBlogType();/*获取博客类型t.id=b.type_id一对一查询*/
        System.out.println("多少类型："+types);
        List<Tag> tags=tagService.getBlogTag();/*获取标签类型*/
        PageInfo pageInfo=new PageInfo(blogInfos);

        List<BlogInfo> recommendBlogs=blogService.getAllRecommendBlog();/*获取所有推荐博客*/
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("recommendBlogs",recommendBlogs);
        return "index";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        /*转换博客内容*/
        model.addAttribute("blog",blogService.getAndConvert(id));
        /*展示评论*/
        model.addAttribute("comments",commentService.getCommentByBlogId(id));
        return "blog";
    }
//    @GetMapping("/about")
//    public String about(){
//        return "about";
//    }
//    @GetMapping("/archives")
//    public String archives(){
//        return "archives";
//    }
//    @GetMapping("/tags")
//    public String tags(){
//        return "tags";
//    }
//    @GetMapping("/types")
//    public String typs(){
//        return "types";
//    }
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin/admin";
//    }
//
//    @GetMapping("/adminint")
//    public String adminint(){
//        return "admin/admin-input";
//    }


}


