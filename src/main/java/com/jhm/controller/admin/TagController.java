package com.jhm.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhm.pojo.Tag;
import com.jhm.pojo.Type;
import com.jhm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tags")
    public String tags(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum, 5);
        List<Tag> allTag = tagService.getAllTag();
        //得到分页结果对象
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());   //返回一个tag对象给前端th:object
        return "admin/tags-input";
    }
    /*下面是types页面点击更新类型，处理请求*/
    @GetMapping("/tags/{id}/input")
    /*编辑服务,页面点击编辑，如给编辑第一个，那给后台传入/tags/1/input再转到types-input页面*/
    public String editPage(@PathVariable Long id, Model model){
        model.addAttribute("type",tagService.getTag(id));
        return "admin/tags-input";
    }
    @PostMapping("/tags")
    public String addType(Tag tag, RedirectAttributes attributes){

        Tag tag1=tagService.getTagByName(tag.getName());
        if(tag1!=null){
            attributes.addFlashAttribute("message","不能添加重复的");
            return "redirect:/admin/tags/input";
        }
        int i=tagService.saveTag(tag);
        if(i==0){
            /*重定向带参数*/
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }

        /*不能直接跳转到types页面，否则不会显示,因为没经过types方法*/
        return "redirect:/admin/tags";
    }
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id, Tag tag, RedirectAttributes attributes){  //修改
        Tag t = tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        tagService.updateTag(tag);
        return "redirect:/admin/tags";   //不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
