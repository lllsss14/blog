package com.jhm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhm.pojo.BlogInfo;
import com.jhm.pojo.Type;
import com.jhm.service.BlogService;
import com.jhm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        Model model){

        List<Type> types=typeService.getBlogType();/*获取博客类型t.id=b.type_id一对一查询*/
        //-1从导航点过来的
        if (id == -1){
            id = types.get(0).getId();
        }
        PageHelper.startPage(pageNum, 5);  //开启分页
        List<BlogInfo> blogInfos = blogService.getByTypeId(id);
        PageInfo<BlogInfo>pageInfo=new PageInfo<>(blogInfos);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
