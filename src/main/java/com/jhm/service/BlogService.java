package com.jhm.service;

import com.jhm.pojo.BlogInfo;


import java.util.List;

public interface BlogService {
    BlogInfo getBlog(Long id);

    public List<BlogInfo> getAllBlog(); /*无条件查所有博客*/
    public List<BlogInfo> findAllBlog(BlogInfo blogInfo);/*有条件的查找*/

    Integer saveBlog(BlogInfo blogInfo);

    Integer updateBlog(BlogInfo blogInfo);

    void deleteBlog(Long id);

}
