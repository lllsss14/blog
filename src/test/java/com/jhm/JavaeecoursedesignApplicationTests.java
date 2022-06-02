package com.jhm;

import com.jhm.dao.BlogDao;
import com.jhm.dao.TypeDao;
import com.jhm.dao.UserDao;
import com.jhm.pojo.Type;
import com.jhm.pojo.User;
import com.jhm.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JavaeecoursedesignApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private BlogDao blogDao;
    @Test
    void contextLoads() {
        System.out.println(blogDao.findById((long) 1));
    }

}
