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

    /*方法名字跟dao层的方法名一样，方便记，太多了记不住，
    根据t_type.id=t_blog.type_id联合查询，就将主页博客有的类型列在首页右侧，type和博客是多对一的关系*/
    public List<Type> getBlogType();

    public Type getTypeByName(String name);
    public int updateType(Type type);
    public void deleteType(Long id);

}
