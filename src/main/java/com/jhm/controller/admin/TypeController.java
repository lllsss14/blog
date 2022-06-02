package com.jhm.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jhm.pojo.Type;
import com.jhm.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    /*TODO 在后端主页导航点击分类，th:href="@{/admin/types}"模板会通过mapping到这里*/
    @GetMapping("/types")
    /*TODO PageNum是前端传过来的页数，默认初始值为1。可以没有给值，如从导航过来,然后默认给他第一页*/
    public String types( @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        Model model){
        /*开启分页,每页十个*/
        PageHelper.startPage(pageNum,5);
        /*TODO 然后调用service层的来调dao获取List<Type>数据*/
        List<Type> types = typeService.listType();

        /*Mybatis没有page类型，用Spring-Data不方便，JPA可以直接转换，所以用插件PageHelper*/
//       model.addAttribute("types", types);
        /*将从数据库获取出来的list的type数据用PageInfo保存*/
        PageInfo<Type>pageInfo=new PageInfo<>(types);
        /*将pageInfo送到前端名字为pageInfo的*/
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }



    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }
    @PostMapping("/types")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            /*。。。发现前端去接受后端的校验有点复杂，水平不够，还是就前端校验吧，也算学到了后端校验的方法*/
            return "admin/types-input";
        }
        Type type1=typeService.getTypeByName(type.getName());
        if(type1!=null){
            attributes.addFlashAttribute("message","不能添加重复的");
            return "redirect:/admin/types/input";
        }
        int i=typeService.saveType(type);
        if(i==0){
            /*重定向带参数*/
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }

        /*不能直接跳转到types页面，否则不会显示,因为没经过types方法*/
        return "redirect:/admin/types";
    }

    /*下面是types页面点击更新类型，处理请求*/
    @GetMapping("/types/{id}/input")
    /*编辑服务,页面点击编辑，如给编辑第一个，那给后台传入/types/1/input再转到types-input页面*/
    public String editPage(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }
    /*
    *edit当前端传过来的要修改的id，先做一次是否重复更新的判断，去改
    * */
    @PostMapping("types/{id}")
    public String edit(Type type,@PathVariable Long id,RedirectAttributes attributes){
        Type type1=typeService.getTypeByName(type.getName());
        if(type1!=null){
            attributes.addFlashAttribute("message","不能更新为已有的类型");
            return "redirect:/admin/types/input";
        }
        int i=typeService.updateType(type);
        if(i==0){
            /*重定向带参数*/
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        /*不能直接跳转到types页面，否则不会显示,因为没经过types方法*/
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    /*点击删除*/
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");

        return "redirect:/admin/types";
    }


}
