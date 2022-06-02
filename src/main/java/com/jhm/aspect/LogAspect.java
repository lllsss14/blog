package com.jhm.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.jhm.controller..*.*(..))")
    public void logPointcut(){}

    @Before("logPointcut()")
    public void before(JoinPoint joinPoint){

        ServletRequestAttributes attributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        /*获取类名和方法名*/
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        /*请求参数*/
        List<Object> args= Arrays.asList(joinPoint.getArgs());
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}",requestLog);
    }
//    @After("logPointcut()")
//    public void after(){
//        logger.info("----after----");
//    }
    @AfterReturning(returning = "result",pointcut = "logPointcut()")
    public void afterReturn(Object result){
        logger.info("Result:{}",result);
    }

    public class RequestLog{
        private String  url;
        private String ip;
        private String classMethod;
        private List<Object> args;

        public RequestLog(String url, String ip, String classMethod, List<Object> args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + args +
                    '}';
        }
    }
}
