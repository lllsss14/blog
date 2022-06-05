package com.jhm.service;

import com.jhm.pojo.BlogInfo;
import com.jhm.pojo.Tag;
import com.jhm.pojo.Type;

import java.util.List;

public interface TagService {
    /*黑马推荐命名风格insert用save取名更好*/
    public int saveTag(Tag tag);
    public Tag getTag(Long id);
    /*如果类别一多那需要分页就用这个实现分页查询，是Spring Data Core带的*/
    public List<Tag> listTag();
    public Tag getTagByName(String name);
    public int updateTag(Tag tag);
    public void deleteTag(Long id);
    public List<Tag> getTagByString(String text);


    /*获得首页博客标签*/
    public List<Tag> getBlogTag();
}
