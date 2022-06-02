package com.jhm.dao;


import com.jhm.pojo.Tag;
import com.jhm.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TagDao {
    @Insert("insert into t_tag values(#{id},#{name})")
    public int save(Tag tag);
    @Select("select * from t_tag where id=#{id}")
    public Tag findOne(Long id);
    @Select("select * from t_tag")
    public List<Tag> findAll();
    @Select("select * from t_tag where name=#{name}")
    public Tag findTagByName(String name);
    @Update("update t_tag set name=#{name} where id=#{id}")
    public int updateTag(Tag tag);
    @Delete("delete from t_tag where id=#{id}")
    public void delete(Long id);


}
