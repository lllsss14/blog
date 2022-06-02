package com.jhm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jhm.dao")
public class JavaeecoursedesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeecoursedesignApplication.class, args);
    }

}
