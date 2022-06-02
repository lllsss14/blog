package com.jhm.service.Impl;

import com.jhm.dao.UserDao;
import com.jhm.pojo.User;
import com.jhm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {

        User user=userDao.findByUsernameAndPassword(username,password);

        return user;
    }
}
