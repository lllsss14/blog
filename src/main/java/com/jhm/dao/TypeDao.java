package com.jhm.dao;

import com.jhm.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface TypeDao  {
    @Insert("insert into t_type values(#{id},#{name})")
    public int save(Type type);
    @Select("select * from t_type where id=#{id}")
    public Type findOne(Long id);
    @Select("select * from t_type")
    public List<Type> findAll();
    @Select("select * from t_type where name=#{name}")
    public Type findTypeByName(String name);

    @Update("update t_type set name=#{name} where id=#{id}")
    public int updateType(Type type);

    @Delete("delete from t_type where id=#{id}")
    public void delete(Long id);

    /*根据t_type.id=t_blog.type_id联合查询，就将主页博客有的类型列在首页右侧，type和博客是多对一的关系*/
    public List<Type> getBlogType();


}
