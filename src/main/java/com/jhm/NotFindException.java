package com.jhm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* 未找到设置相应状态码为未找到，这个注解会主动去找NOT_FOUND对应的404页面
* */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFindException extends RuntimeException {
    public NotFindException() {
    }

    public NotFindException(String message) {
        super(message);
    }

    public NotFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
