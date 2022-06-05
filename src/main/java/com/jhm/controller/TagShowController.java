package com.jhm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhm.pojo.BlogInfo;
import com.jhm.pojo.Tag;
import com.jhm.pojo.Type;
import com.jhm.service.BlogService;
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
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        Model model){

        List<Tag> tags=tagService.getBlogTag();/*获取博客类型t.id=b.tag_id一对一查询*/
        //-1从导航点过来的
        if (id == -1){
            id = tags.get(0).getId();
        }

        PageHelper.startPage(pageNum, 5);  //开启分页
       List<BlogInfo> blogInfos=blogService.getByTagId(id);
        PageInfo<BlogInfo>pageInfo=new PageInfo<>(blogInfos);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
