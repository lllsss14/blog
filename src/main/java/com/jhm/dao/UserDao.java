package com.jhm.dao;

import com.jhm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from t_user where username=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
