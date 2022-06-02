package com.jhm.interceptororexhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//Advice通知,@ControllerAdvice注解是扫描所有带controller的
@ControllerAdvice
public class ExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
//    代替了xml配置handler处理器
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Reuqest URL:{},Exception:{}",request.getRequestURL(),e);
        /*如果看到响应状态不为空，那抛出异常,不处理这个,如访问不存在的页面那抛出404这个异常*/
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
