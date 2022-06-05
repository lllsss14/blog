package com.jhm.service;

import com.jhm.pojo.BlogInfo;


import java.util.List;
import java.util.Map;

public interface BlogService {
    public BlogInfo getBlog(Long id);

    public List<BlogInfo> getAllBlog(); /*无条件查所有博客*/
    public List<BlogInfo> findAllBlog(BlogInfo blogInfo);/*有条件的查找*/

    public Integer saveBlog(BlogInfo blogInfo);

    public BlogInfo getAndConvert(Long id);/*将博客详情里面的内容转换成HTML，用到了插件*/

    /*前台推荐博客展示*/
    public List<BlogInfo> getAllRecommendBlog();
    public Integer updateBlog(BlogInfo blogInfo);

    public void deleteBlog(Long id);

    /*和dao方法名一样，查询首页博客*/
    public List<BlogInfo> getIndexBlog();

    /*后台进入编辑博客页面，根据博客表、标签表多对多查询*/
    public BlogInfo findBlogWithTag(Long id);


    /*前台根据type来查询博客*/
    public List<BlogInfo> getByTypeId(Long typeId);

    /*文档，根据年份展示*/
    public Map<String,List<BlogInfo>> archiveBlog();

    /*文档页面总博客计数*/
    public int countBlog();




}
