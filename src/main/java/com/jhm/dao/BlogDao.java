package com.jhm.dao;

import com.jhm.pojo.BlogInfo;

import java.util.List;

public interface BlogDao {
    /*都对应BlogDao.xml文件里面的sql*/
    public BlogInfo findById(Long id); /*后台管理查询单个博客*/
    public List<BlogInfo> findAll(BlogInfo blogInfo); /*后台根据标题、分类、推荐搜索博客*/
    public int updateBlog(BlogInfo blogInfo);/*后台更新博客*/
    public int saveBlog(BlogInfo blogInfo);/*后台新增博客*/
    public void deleteBlog(Long id);/*后台删除博客*/
    public List<BlogInfo> getAll();/*无条件查所有博客*/
}
