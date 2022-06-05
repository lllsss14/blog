package com.jhm.dao;

import com.jhm.pojo.BlogInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogDao {
    /*都对应BlogDao.xml文件里面的sql*/
    public BlogInfo findById(Long id); /*后台管理查询单个博客*/
    public List<BlogInfo> findAll(BlogInfo blogInfo); /*后台根据标题、分类、推荐搜索博客*/
    public int updateBlog(BlogInfo blogInfo);/*后台更新博客*/
    public int saveBlog(BlogInfo blogInfo);/*后台新增博客*/
    public void deleteBlog(Long id);/*后台删除博客*/
    public List<BlogInfo> getAll();/*无条件查所有博客*/

    public List<BlogInfo> findAllRecommendBlog();/*查询所有推荐博客*/

    /*查首页博客展示*/
    public List<BlogInfo> getIndexBlog();

    /*后台进入编辑博客页面，根据博客表、标签表多对多查询,得到对应这篇博客的标签*/
    public BlogInfo findBlogWithTag(Long id);

    /*前台获取博客详情*/
    public  BlogInfo getDetailedBlog(Long id);

    /*根据类型查询博客*/
    public List<BlogInfo> getByTypeId(Long typeId);

    /*查询所有的博客的年份，返回年份列表*/
    @Select("select DATE_FORMAT(b.update_time, '%Y') from t_blog b order by b.update_time desc")
    public List<String> findGroupYear();
    /*根据年份查博客*/
    public List<BlogInfo> findByYear(@Param("year") String year);

    /*根据标签查询博客*/
    public List<BlogInfo> getByTagId(Long tagId);

}
