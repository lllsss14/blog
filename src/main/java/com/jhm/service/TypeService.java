package com.jhm.service;

import com.jhm.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    /*黑马推荐命名风格insert用save取名更好*/
    public int saveType(Type type);
    public Type getType(Long id);
    /*如果类别一多那需要分页就用这个实现分页查询，是Spring Data Core带的*/
    public List<Type> listType();
    public Type getTypeByName(String name);
    public int updateType(Type type);
    public void deleteType(Long id);

}
