package com.jhm.service;

import com.jhm.pojo.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
    public User checkUser(String username,String password);


}
