package com.jhm.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhm.pojo.BlogInfo;
import com.jhm.pojo.Tag;
import com.jhm.pojo.User;
import com.jhm.service.BlogService;
import com.jhm.service.TagService;
import com.jhm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    /*TODO 这只是一个标记，方便我回忆，input是进入博客发布页面，first是进入博客后台的博客页，
       REDIRECT_FIRST是重定向回admin*/
    private static final String INPUT="admin/admin-input";
    private static final String FIRST="admin/admin";
    private static final String REDIRECT_FIRST="redirect:/admin/admin";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;



    /*TODO 前端点击按钮，按钮href地址对应/admin，进入后台主页，然后从数据库中拿到blog信息model返回给前端，
       最后返回localhost/admin/admin页面*/
    @GetMapping("/admin")
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<BlogInfo>  blogInfos =blogService.getAllBlog();
        PageInfo pageInfo=new PageInfo(blogInfos);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.listType());

        return "admin/admin";
    }


    /*TODO 有条件的查询，按条件如标题，类别、是否推荐等，点击提交按钮。*/
    @PostMapping("/admin/search")
    public String searchBlogs(BlogInfo blogInfo,@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum,5);

        List<BlogInfo>  blogInfos =blogService.findAllBlog(blogInfo);
        PageInfo pageInfo=new PageInfo(blogInfos);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        /*拿到数据后返回admin/admin页面，即还是再原来页面*/
        return "admin/admin";
    }



    /*TODO 跳转到后端博客发布页面*/
    @GetMapping("/admin/input")
    public String gotoAddBlogPage(Model model){
        /*初始化一下，因为修改功能还是在原页面，到时候修改完重新加载这个页面，这样new 一次info对象就能正常拿到数据*/
        model.addAttribute("blogInfo",new BlogInfo());/*初始化*/
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "admin/admin-input";
    }


    @PostMapping("/admin") /*前端设置了一个type.id的input和带th:each="type : ${types}"，boot会自动把type放进blogInfo对象里，就可以去dao拿到数据*/
    public String fabuBlog(BlogInfo blogInfo, HttpSession session, RedirectAttributes attributes){
        /*设置发布博客的用户,都是初始化，拿到数据后调用服务层saveBlog方法，新增博客*/
        blogInfo.setUser((User) session.getAttribute("user"));
        /*TODO 设置博客类型，在BlogInfo里面type是Type对象,在admin-input页面由一个name="type.id"的input，
           当,前端会自动赋值到BlogInfo对象里面的Type type对象里*/
        blogInfo.setUserId((long) 1);
        blogInfo.setType(typeService.getType(blogInfo.getType().getId()));
        blogInfo.setTypeId(blogInfo.getType().getId());
        /*TODO 将获得的tag，赋值给blogInfo对象的List<Tag>，通过调用根据字符串来传，
           在tag服务实现类中定义了分割字符串的方法，然后循环遍历，调用Dao层的根据ID找标签方法，获得数据*/
        blogInfo.setTags(tagService.getTagByString(blogInfo.getTagIds()));
        int i;
        if (blogInfo.getId() == null) {   //id为空，则为新增

             i =blogService.saveBlog(blogInfo);
        } else {
           i= blogService.updateBlog(blogInfo);
        }
        if(i==0){
            /*操作结果会和重定向一起发送给前端，到时候就标签名字为message的接收，弹消息框*/
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }

        return "redirect:/admin/admin";
    }


    /*TODO 跳转到后端博客修改页面*/
    @GetMapping("/admin/{id}/input")
    public String gotoEditBlogPage(Model model, @PathVariable Long id){

        BlogInfo blogInfo=blogService.findBlogWithTag(id);
        blogInfo.init();
//        System.out.println("我来看看你有没有问题！！！！！："+blogInfo);
        model.addAttribute("blogInfo",blogInfo);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "admin/admin-input";
    }

    @GetMapping("admin/{id}/delete")
    public String deleteBlog(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/admin";
    }

}
